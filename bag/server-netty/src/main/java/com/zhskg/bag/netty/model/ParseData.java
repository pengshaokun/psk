package com.zhskg.bag.netty.model;

import com.alibaba.fastjson.JSONObject;
import com.zhskg.bag.netty.util.CRC8;
import com.zhskg.bag.netty.util.StringsUtil;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwb on 2018/5/15.
 * 数据解析
 */

public class ParseData
{
    private byte[] dataPackage;
    private int lenght;
    private BagData bagData =null;

    /**
     * 通信包标识：
     * 01是注册，注册成功后回复,回复标识:81
     * 02是数据上传，数据上传回复：82
     *
     * 0a是心跳包
     */
    private String cmdFlag;

    public ParseData(byte[] data,TableEnum table){
        this.dataPackage = data;
        this.lenght = dataPackage.length;
        this.bagData = new BagData(table);
    }


    public String getClientId()
    {
        String prex =StringUtils.leftPad(String.valueOf(dataPackage[11]),2,"0")+StringUtils.leftPad(String.valueOf(dataPackage[10]),2,"0"); // "0031"; //是否需要写死

        String clientId = prex +
                StringsUtil.PadLeft(String.valueOf(Integer.parseInt(
                        StringsUtil.PadLeft(Integer.toHexString(dataPackage[9]), 2, '0') +
                                StringsUtil.PadLeft(Integer.toHexString(dataPackage[8]), 2, '0') +
                                StringsUtil.PadLeft(Integer.toHexString(dataPackage[7]), 2, '0') +
                                StringsUtil.PadLeft(Integer.toHexString(dataPackage[6]), 2, '0'), 16)), 10, '0');
        return clientId;
    }
    /**
     * mac地址应该从第15个字符开始
     * @return
     */
    private String getMac()
    {
        StringBuffer buffer=new StringBuffer();
        for (int i=14;i<20;i++){
            buffer.append(dataPackage[i]);
        }
        return buffer.toString();
    }

    /**
     * 经纬度
     */
    public BagData getData()
    {
        int start = 12 +14;
        boolean flag = true;
        List<Integer> list = new ArrayList<Integer>();
        JSONObject jsonObject = new JSONObject();
        while (flag)
        {
            int paramFlag = dataPackage[start];
            if (start+2==lenght)//判断是不是取到校验码
            {
                break;
            }
            switch (paramFlag)
            {
                case 31:
                    bagData.setAlarmFlag(1);
                    int field = dataPackage[start + 5];
                    switch (field){
                        case 29:  //1D 电流报警报警
                            list.add(FieldShort.UpperAlarm_ElectricCurrent);
                            break;
                        case 30: //1E 电压报警报警
                            list.add(FieldShort.LowerAlarm_Voltage);
                            break;
                        case 36: //24 温度值报警
                            list.add(FieldShort.UpperAlarm_Temperature);
                            break;
                        case 54: //电池电量
                            list.add(FieldShort.LowerAlarm_Querty);
                            break;
                    }
                    start +=7;
                    break;
                case 63:  //3F 地图坐标
                    int quarty = dataPackage[start+2];
                    if (quarty!=0) {  //78是N 维度
                        //经度
                        int isLat = dataPackage[start+3];
                        BigDecimal lat =null;
                        BigDecimal lon =null;
                        if (isLat == 78)
                        {
                            String dataLat =Integer.toHexString(dataPackage[start+7] & 0xFF)+Integer.toHexString(dataPackage[start+6] & 0xFF)
                                    +Integer.toHexString(dataPackage[start+5] & 0xFF)+Integer.toHexString(dataPackage[start+4] & 0xFF);

                            String dataLon =Integer.toHexString(dataPackage[start+12] & 0xFF)+Integer.toHexString(dataPackage[start+11] & 0xFF)
                                    +Integer.toHexString(dataPackage[start+10] & 0xFF)+Integer.toHexString(dataPackage[start+9] & 0xFF);
                            //String dataLat =primaryData[start+7]+primaryData[start+6]+primaryData[start+5]+primaryData[start+4];
                            //String dataLon =primaryData[start+12]+primaryData[start+11]+primaryData[start+10]+primaryData[start+9];
                            lat = cal(dataLat);//维度
                            lon =  cal(dataLon);//经度
                        }else {
                            String dataLat =Integer.toHexString(dataPackage[start+12] & 0xFF)+Integer.toHexString(dataPackage[start+11] & 0xFF)
                                    +Integer.toHexString(dataPackage[start+10] & 0xFF)+Integer.toHexString(dataPackage[start+9] & 0xFF);
                            String dataLon =Integer.toHexString(dataPackage[start+7] & 0xFF)+Integer.toHexString(dataPackage[start+6] & 0xFF)
                                    +Integer.toHexString(dataPackage[start+5] & 0xFF)+Integer.toHexString(dataPackage[start+4] & 0xFF);
                            //String dataLon  =primaryData[start+7]+primaryData[start+6]+primaryData[start+5]+primaryData[start+4];
                            //String dataLat  =primaryData[start+12]+primaryData[start+11]+primaryData[start+10]+primaryData[start+9];
                            lat = cal(dataLat);//维度
                            lon =  cal(dataLon);//经度
                        }
                        bagData.setLat(lat);
                        bagData.setLon(lon);
                    }
                    start += 13;
                    break;
                case 0x1D: //电流值
                    String hex = Integer.toHexString(dataPackage[start+5] & 0xFF) +Integer.toHexString(dataPackage[start+4] & 0xFF)+
                            Integer.toHexString(dataPackage[start+3] & 0xFF)+Integer.toHexString(dataPackage[start+2] & 0xFF);
                    double valEl = Integer.parseInt(hex,16)/100.0;
                    jsonObject.put("el", tranc(valEl,2));
                    start +=6;
                    break;
                case 0x1E://电压值
                    String hexv = Integer.toHexString(dataPackage[start+3] & 0xFF) +Integer.toHexString(dataPackage[start+2] & 0xFF);
                    double valVa = Integer.parseInt(hexv,16)/100.0;
                    jsonObject.put("va", tranc(valVa,2));
                    start +=4;
                    break;
                case 0x36://电量值
                    String hexe = Integer.toHexString(dataPackage[start+5] & 0xFF) +Integer.toHexString(dataPackage[start+4] & 0xFF)+
                            Integer.toHexString(dataPackage[start+3] & 0xFF)+Integer.toHexString(dataPackage[start+2] & 0xFF);
                    double valEv = Integer.parseInt(hexe,16)/100.0;
                    jsonObject.put("ev", tranc(valEv,2));
                    start+=6;
                    break;
                case 0x24://温度值
                    String hext = Integer.toHexString(dataPackage[start+3] & 0xFF) +Integer.toHexString(dataPackage[start+2] & 0xFF);
                    double valTe = Integer.parseInt(hext,16)/100.0;
                    jsonObject.put("te", tranc(valTe,2));
                    start +=4;
                    break;
                case 0x73://移动流量卡
                    start +=13;
                    break;
                default:
                    flag = false;
                    break;
            }

        }
        bagData.setAf(list);
        bagData.setData(jsonObject.toJSONString());
        bagData.setClientId(getClientId());
        return bagData;
    }

    public double tranc(double val) {
        BigDecimal bg = new BigDecimal(val);
        double f1 = bg.setScale(7, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public double tranc(double val,int scale) {
        BigDecimal bg = new BigDecimal(val);
        double f1 = bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public static BigDecimal cal(String hash)
    {
        long data =  Long.parseLong(hash,16);
        double dec = data / 10000000.0;
        BigDecimal bg = new BigDecimal(dec);
        BigDecimal f1 = bg.setScale(7, BigDecimal.ROUND_HALF_UP);
        return f1;
    }

    public int parsingHex(String hex)
    {
        int data = Integer.parseInt(hex,16);
        return data;
    }

    public static void main(String[] args) {
        String hex="0b"+"0a"+"19"+"2f";
       System.out.println(Integer.parseInt(hex,16));

        int a=22222;
        String b=Integer.toString(a,16);
        System.out.println(b);
    }
}

