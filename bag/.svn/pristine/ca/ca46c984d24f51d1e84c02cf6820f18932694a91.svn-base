package com.zhskg.bag.netty.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 暂时没用到
 * Created by lwb on 2018/5/17.
 */
public class ParamSet
{
    private byte[] buffer;

    private List<Byte> list;

    private int length;

    public ParamSet()
    {
        list = new ArrayList<Byte>();
    }
    public void setHead()
    {
        list.add((byte) Integer.parseInt("FE",16));
        list.add((byte) Integer.parseInt("FE",16));
    }

    /**
     * 占位符
     */
    public void setLength()
    {
        list.add((byte)0);
        list.add((byte)0);
    }

    public void setCmdFlag(int cmd)
    {
        list.add((byte)cmd);
    }

    public void setpakgeNum(int num)
    {
        list.add((byte)num);
    }
    public void setTail()
    {
        list.add((byte) Integer.parseInt("ED",16));
    }

    public void setIp(String ip)
    {
        String[] ips = ip.split(".");
        if (ips.length != 4){
            throw new IllegalArgumentException("ip address is error");
        }
    }
}
