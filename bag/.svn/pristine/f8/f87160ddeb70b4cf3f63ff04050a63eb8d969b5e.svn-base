package com.zhskg.bag.netty.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhskg.bag.netty.util.CRC16;
import com.zhskg.bag.netty.util.RedisUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Ymodem 协议
 * Created by lwb on 2018/6/12.
 */
public class Ymodem
{
    // 开始
    private final byte SOH = 0x01;//传输128个字节

    private final byte STX = 0x02;//传输1024个字节，不足的以0填充
    // 结束
    private final byte EOT = 0x04;
    // 应答
    private final byte ACK = 0x06;
    // 重传
    private final byte NAK = 0x15;
    // 无条件结束
    private final byte CAN = 0x18;

    private final byte C=0x43;

    private final int buff_size = 1024;

    // 最大错误（无应答）包数
    private final int MAX_ERRORS = 10;

    public Ymodem()
    {

    }

    public byte[] getData(final String filePath,int time)
    {
        //  控制字符 + 包序号 + 包序号的反码 + 数据 + 校验和
        try{
            // 初始化数据缓冲区
            byte[] sector = new byte[buff_size];
            // 读取文件初始化
            DataInputStream inputStream = new DataInputStream(new FileInputStream(filePath));
            // 读取到缓冲区的字节数量
            int fileSize =(int) new File(filePath).length();
            int skipNum = time * buff_size;
            if (skipNum<fileSize) {
                inputStream.skip(time * buff_size);
                int nbytes = inputStream.read(sector, 0, buff_size);
                System.out.println(nbytes);
                if (nbytes != buff_size) {
                    for (int start = nbytes; start < buff_size; start++) {
                        sector[start] = 0x00;
                    }
                }
                inputStream.close();
                return sector;
            }else {
                byte[] end =new byte[1];
                end[0] = 04;
                return end;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 接收指令，进行数据返回
     * @param ctx
     * @param packet
     * @param senderIp
     * @throws Exception
     */
    public void cmd_c(ChannelHandlerContext ctx, io.netty.channel.socket.DatagramPacket packet,String senderIp) throws Exception{
        byte[] zb = new byte[buff_size + 5];
        zb[0] = STX;
        zb[1] = 0x00;
        zb[2] = ~0x00;
        byte[] data = getData("D:\\apache-maven-3.5.2\\bin\\mvnDebug.cmd", 0);//后期修改，配置文件路径地址
        //int checkSum = CRC16.calc(data) & 0x00ffff;
        int checkSum = CRC16.crc_16_CCITT_False(data,1024);
        String crc = Integer.toString(checkSum,16);
        zb[buff_size+3] = (byte)Integer.parseInt(crc.substring(0,2),16);
        zb[buff_size+4] = (byte)Integer.parseInt(crc.substring(2,4),16);
        for(int i =0;i<1024;i++){
            zb[i+3] = data[i];
        }
        //IP地址，记录IP，然后往redis存一条数据，一个是端口，一个是当前的发的包数
        int port = packet.sender().getPort();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("port",port);
        map.put("time",0);
        RedisUtil.setex("bag:ex:"+senderIp,3600,JSON.toJSONString(map));
        ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(zb), packet.sender())).sync();
    }

    public void cmd_06(ChannelHandlerContext ctx, io.netty.channel.socket.DatagramPacket packet,String senderIp) throws Exception
    {
        String jsonData = RedisUtil.get("bag:ex:"+senderIp);
        if (StringUtils.isEmpty(jsonData)){
            throw new IllegalArgumentException("该设备的IP地址已经改变或发送数据错误！");
        }
        JSONObject jsonObject = JSON.parseObject(jsonData);
        int time = jsonObject.getIntValue("time") +1;

        byte[] datat = getData("D:\\apache-maven-3.5.2\\bin\\mvnDebug.cmd", time);
        if (datat.length==1){
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(datat), packet.sender())).sync();
        }else {
            byte[] zbt = new byte[buff_size + 5];
            zbt[0] = STX;
            byte ser = (byte) time;
            zbt[1] = ser;
            zbt[2] = (byte) ~ser;
            //int checkSumt = CRC16.calc(datat) & 0x00ffff;
            int checkSumt = CRC16.crc_16_CCITT_False(datat,1024);
            String strCrc = Integer.toHexString(checkSumt).toUpperCase();
            String crtct = StringUtils.leftPad(strCrc,4,"0");
            zbt[buff_size+3] = (byte) Integer.parseInt(crtct.substring(0, 2), 16);
            zbt[buff_size + 4] = (byte) Integer.parseInt(crtct.substring(2, 4), 16);
            for (int i = 0; i < 1024; i++) {
                zbt[i + 3] = datat[i];
            }
            jsonObject.put("time", time);
            RedisUtil.setex("bag:ex:" + senderIp, 3600, JSON.toJSONString(jsonObject));
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(zbt), packet.sender())).sync();
        }
    }

    /**
     * 接收标识，做相应的回复
     * @param ctx
     * @param packet
     * @param flag
     */
    public void handlle(ChannelHandlerContext ctx, io.netty.channel.socket.DatagramPacket packet,int flag)
    {
        //ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(locks), packet.sender())).sync();
        //  控制字符 + 包序号 + 包序号的反码 + 数据 + 校验和
        try {
            String senderIp = packet.sender().getHostString();
            switch (flag) {
                case 0x0C:  //接收方发送第一指令，返回第一针数据1024
                    cmd_c(ctx,packet,senderIp);
                    break;
                case 0x06:
                    cmd_06(ctx,packet,senderIp); //
                    break;
                case 0x04:
                    RedisUtil.delete("bag:ex:"+senderIp);
                    break;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
