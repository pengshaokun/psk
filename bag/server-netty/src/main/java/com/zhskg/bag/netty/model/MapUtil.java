package com.zhskg.bag.netty.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by lwb on 2018/5/16.
 */
public class MapUtil
{
    private static Map<String,Integer> upMap;

    private static Map<String,Integer> downMap;

    static
    {
        upMap = new HashMap<String, Integer>();
        downMap = new HashMap<String, Integer>();

        upMap.put("01",0x81);//注册命令
        upMap.put("02",0x82);//数据上传命令
        upMap.put("03",0x83);//告警命令
        upMap.put("04",0x84);//告警恢复
        upMap.put("05",0x85);//控制命令 只有用户箱包丢失才会下发
        upMap.put("07",0x07);//下发参数
        upMap.put("08",0x88);
        upMap.put("09",0x89);
        upMap.put("0a",0x8A);
//
//        downMap.put("","");
//        downMap.put("","");
//        downMap.put("","");
//        downMap.put("","");
//        downMap.put("","");
//        downMap.put("","");
//        downMap.put("","");
//        downMap.put("","");
//        downMap.put("","");
    }

    public static Map<String,Integer> getUpMap()
    {
        return upMap;
    }

    //回复命令码集合
    private ArrayList<Integer> GetReplayCodes() {
        ArrayList<Integer> codes = new ArrayList<Integer>();
        codes.add(0x70);
        codes.add(0x81);
        codes.add(0x82);
        codes.add(0x83);
        codes.add(0x84);
        codes.add(0xA1);
        codes.add(0xA2);
        codes.add(0xA3);
        return codes;
    }

}
