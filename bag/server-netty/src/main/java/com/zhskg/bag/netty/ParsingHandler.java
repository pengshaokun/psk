package com.zhskg.bag.netty;

import java.util.Calendar;

import com.github.mustachejava.Code;
import com.zhskg.bag.netty.model.*;
import com.zhskg.bag.netty.util.*;
import com.zhskg.bag.util.PushUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class ParsingHandler extends  SimpleChannelInboundHandler<DatagramPacket>
{

    private static Logger log = LoggerFactory.getLogger(ParsingHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext context, DatagramPacket packet) throws Exception {
        //读取收到的数据
        ByteBuf buf = packet.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        buf.release();
        System.out.println(tran(req));
        if (req.length == 1){
            Ymodem ymodem = new Ymodem();
            ymodem.handlle(context,packet,req[0]);
        }else {
            //校验数据
            byte bt = CRC8.CRC8Deal(req);
            System.out.println(bt +"   "+ req[req.length-2]);
            if (bt == req[req.length - 2]) {
                parse(context, packet, req);
            }
        }

    }

    public String tran(byte[] data)
    {
        StringBuffer buffer = new StringBuffer();
        for(byte bt : data)
        {
            buffer.append(StringUtils.leftPad(Integer.toHexString(bt & 0xFF).toUpperCase(), 2, "0")+" ");
        }
        return buffer.toString();
    }


    /**
     * 81注册回复
     */
    private byte[] regs(byte[] data)
    {
        byte[] resp = new byte[36];
        Calendar c = Calendar.getInstance();
        String Year = String.valueOf(c.get(Calendar.YEAR));
        String Month = String.valueOf(c.get(Calendar.MONTH) + 1);
        String Day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String Hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String Minute = String.valueOf(c.get(Calendar.MINUTE));
        String Second = String.valueOf(c.get(Calendar.SECOND));
        for (int i = 0; i < 26; i++) {
            resp[i] = data[i];
        }
        resp[2]=33;
        resp[3]=0;
        resp[4]=(byte) 0x81;
        resp[26]=8;
        resp[27]=6;
        resp[28] = (byte) Integer.parseInt(Year.substring(2, 4), 16);
        resp[29] = (byte) Integer.parseInt(Month, 16);
        resp[30] = (byte) Integer.parseInt(Day, 16);
        resp[31] = (byte) Integer.parseInt(Hour, 16);
        resp[32] = (byte) Integer.parseInt(Minute, 16);
        resp[33] = (byte) Integer.parseInt(Second, 16);
        resp[34] = CRC8.CRC8Deal(resp);
        resp[35] = (byte) 0xED;
        return resp;
    }

    /**
     * 对于各种上报数据返回编号
     * @param data
     * @param upCmd
     * @return
     */
    public byte[] repay(byte[] data,String upCmd)
    {
        byte[] resp = new byte[28];
        for (int i = 0; i < 26; i++) {
            resp[i] = (byte) data[i];
        }
        resp[2]=25;
        resp[3]=0;
        if (MapUtil.getUpMap().containsKey(upCmd)){
            int rs = MapUtil.getUpMap().get(upCmd);
            resp[4]=(byte) rs;
            resp[26]=CRC8.CRC8Deal(resp);
            resp[27]=(byte)0xED;
            return resp;
        }else {
            return null;
        }
    }

    //回复函数
    private void parse(ChannelHandlerContext ctx, DatagramPacket packet,byte[] data) {
        //去前12+14字符
//        String[] head = new String[26];
//        String getLenght= StringUtils.leftPad(Integer.toString(15,16),2,"0");
        int getCmd = data[4];//第五个是命令类型码
        byte[] resp =null;
        String prex =StringUtils.leftPad(String.valueOf(data[11]),2,"0")+StringUtils.leftPad(String.valueOf(data[10]),2,"0");
        //获取设备编码id
        String clientId =prex +
                StringsUtil.PadLeft(String.valueOf(Integer.parseInt(StringsUtil.PadLeft(Integer.toHexString(data[9]), 2, '0') +
                        StringsUtil.PadLeft(Integer.toHexString(data[8]), 2, '0') +
                        StringsUtil.PadLeft(Integer.toHexString(data[7]), 2, '0') +
                        StringsUtil.PadLeft(Integer.toHexString(data[6]), 2, '0'), 16)), 10, '0');
        //
        String upcmd = StringUtils.leftPad(Integer.toString(data[4],16), 2, "0");//上报的命令类型码
        String senderIp = packet.sender().getHostString();//发送的ip
        int port = packet.sender().getPort();
        switch (getCmd)
        {
            case 1:
                resp =regs(data);
                break;
            case 2://上报地理位置
                resp = repay(data,upcmd);
                ParseData parseData=new ParseData(data, TableEnum.DEVICEDATA);
                BagData bagData = parseData.getData();
                bagData.setIp(senderIp);
                bagData.setPort(port);
                bagData.setCreateTime(System.currentTimeMillis());
                System.out.println(JSON.toJSONString(bagData));
                BatchHandle.sadd(bagData);
                //ThriftClient.transport(1, JSON.toJSONString(bagData));
                break;
            case 3://报警数据
                ParseData parseData1=new ParseData(data,TableEnum.DEVICEALARM);
                BagData alarmData = parseData1.getData();
                alarmData.setIp(senderIp);
                alarmData.setPort(port);
                alarmData.setCreateTime(System.currentTimeMillis());
                System.out.println(JSON.toJSONString(alarmData));
                BatchHandle.sadd(alarmData);//入库到ES
                //ThriftClient.transport(1, JSON.toJSONString(alarmData));
                resp = repay(data,upcmd);
                String key = ConfigUtil.REDIS_KEY+clientId;
                String json = RedisUtil.get(key);
                if (json !=null){
                    CacheData item = JSON.parseObject(json, CacheData.class);
                    if (item!=null&&StringUtils.isNotEmpty(item.getPhoneNumber())){
                        ResultModel res=new ResultModel(Commons.warning,"1",alarmData);
                        PushUtil.pushCustomMesByAlias("warting",JSON.toJSONString(res),item.getPhoneNumber(),"---",true);
                    }
                }

                break;
            case 0x86: //这个是服务器主动查询数据
                ParseData parseData_t=new ParseData(data, TableEnum.DEVICEDATA);
                BagData bagData_t = parseData_t.getData();
                bagData_t.setIp(senderIp);
                bagData_t.setPort(port);
                BatchHandle.sadd(bagData_t);
                //ThriftClient.transport(1, JSON.toJSONString(bagData_t));
                break;
            default:
                //心跳包
                resp = repay(data,upcmd);
                StringBuffer buffer = new StringBuffer();
                for (int i =14;i<26;i++)
                {
                    buffer.append(Integer.toHexString(data[i] & 0xFF)+"-");
                }
                if (upcmd.toUpperCase().equals("0A")){
                    int rs =setIp(clientId,senderIp,port,buffer.toString());
                    log.info("clientId_ip:"+rs);
                }
                break;
        }
        // 回复数据给客户端
        try {
            //当发现这个这个设备处理丢失状态，则下发控制锁死命令
            if (isSendLoss(clientId)){
                byte[] locks=cmdlock(data);
                try {
                    if (locks !=null) {
                        //
                        //测试推送
                        //
                        ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(locks), packet.sender())).sync();
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (resp !=null) {
                ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(resp), packet.sender())).sync();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isSendLoss(String clientId)
    {
        String key = ConfigUtil.REDIS_KEY+clientId;
        String json = RedisUtil.get(key);
        if (json !=null){
            CacheData item = JSON.parseObject(json, CacheData.class);
            //当用户的设备处于丢失状态，则下发锁死命令
            if (item.getLossFlag()==1){
                return true;
            }
        }
        return false;
    }

    /**
     * 下发暴死命令
     */
    public byte[] cmdlock(byte[] data)
    {
        byte[] resp = new byte[31];
        for (int i = 0; i < 26; i++) {
            resp[i] =  data[i];
        }
        resp[2]=31; // 71 01 00/01
        resp[3]=0;
        //控制码
        resp[4] = (byte)0x85;
        //暴死编号
        resp[26] = 0x71;
        resp[27] = 1;
        resp[28] =1;
        resp[29] = CRC8.CRC8Deal(resp);
        resp[30] =(byte) 0xED;
        return resp;
    }

    public int setIp(String clientId,String ip,int port,String cpu)
    {
        String key = ConfigUtil.REDIS_KEY+clientId;
        String json = RedisUtil.get(key);
        if (json !=null){
            CacheData item = JSON.parseObject(json, CacheData.class);
            item.setIp(ip);
            item.setPort(port);
            RedisUtil.set(key,JSON.toJSONString(item));
            return 1;
        }else {
            CacheData item = new CacheData();
            item.setClientId(clientId);
            item.setIp(ip);
            item.setLossFlag(0);
            item.setParamFlag(0);
            item.setPort(port);
            item.setCpu(cpu);
            RedisUtil.set(key,JSON.toJSONString(item));
            return 1;
        }
    }

}
