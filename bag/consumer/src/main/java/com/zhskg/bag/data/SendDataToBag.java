package com.zhskg.bag.data;

import com.alibaba.fastjson.JSON;
import com.zhskg.bag.util.RedisUtil;
import org.apache.commons.lang.StringUtils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by lwb on 2018/6/8.
 */
public class SendDataToBag
{
    public static int CRC(int[] buffer)
    {
        int crc = 0;
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        for (int j = 0; j < buffer.length; j++)
        {
            crc ^= buffer[j];
            for (int i = 0; i < 8; i++)
            {
                if ((crc & 0x01) != 0)
                {
                    crc >>= 1;
                    crc ^= 0x8c;
                }
                else
                {
                    crc >>= 1;
                }
            }
        }
        return crc;
    }

    public static byte CRC8Deal(byte[] data) {
        int crc8DataLen = data.length - 4;
        int[] crc8Data = new int[crc8DataLen];
        for (int i = 0; i < crc8DataLen; i++) {
            crc8Data[i] = data[i + 2] & 0xFF;
        }
        return (byte) CRC(crc8Data);
    }

    public static void send(String clientId,String cpu)
    {
        StringBuffer buffer = new StringBuffer();

    }

    public static byte[] getClient(String client)
    {
        String prev = client.substring(0,4);
        String no = client.substring(4,client.length());
        byte[] bt = new byte[6];
        bt[4] =(byte)0x29;
        bt[5]=0;
        long bh = Long.parseLong(no);
        String ch = StringUtils.leftPad(Long.toString(bh,16),8,"0");
        System.out.println(ch);
        int j= 0;
        for (int i=3;i>=0;i--){
            bt[j] = (byte)Integer.parseInt(ch.substring(i*2,(i+1)*2),16);
            j++;
        }
        return  bt;
    }

    public static byte[] lon()
    {
        //6D 01 3F
        byte[] bt = new byte[3];
        bt[0] = (byte)0x6D;
        bt[1] = (byte)0x01;
        bt[2] = (byte)0x3F;
        return bt;
    }

    public static byte[] all(String clientId,String cpuId)
    {
        byte[] cl_byte = getClient(clientId);
        byte[] getLon = lon();
        String[] cpu_no = cpuId.split("-");
        byte[] cpu = new byte[14];
        for(int i = 0;i<14;i++){
            cpu[i] =(byte)Integer.parseInt(cpu_no[i],16);
        }
        int len = 8 + cl_byte.length + cpu.length +getLon.length;
        byte[] allData = new byte[len];
        allData[0]=(byte)0xFE;allData[1]=(byte)0xFE;
        allData[2] = (byte)(len - 3);allData[3]=0;
        allData[4] = 0x06;allData[5] = 0x01;
        int i=6;
        for (byte b : cl_byte){
            allData[i] = b;
            i++;
        }
        for (byte b : cpu){
            allData[i] = b;
            i++;
        }
        for (byte b: getLon){
            allData[i] =b;
            i++;
        }
        allData[i]= CRC8Deal(allData);
        allData[i+1] =(byte)0xED;
        return allData;
    }

    public static void main(String[] args) throws Exception
    {
        //建立udp的服务
        DatagramSocket datagramSocket = new DatagramSocket();
        //创建了一个数据包
        String json = RedisUtil.get("bag:data:00410000000001");
        BagDataInfo model = JSON.parseObject(json,BagDataInfo.class);
        byte[] data = all("00410000000001",model.getCpu());
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.0.161") , 8083);
        //调用udp的服务发送数据包
        datagramSocket.send(packet);
        //关闭资源 ---实际上就是释放占用的端口号
        datagramSocket.close();
    }
}
