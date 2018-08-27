package com.zhskg.bag.util;

import org.apache.commons.lang.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by lwb on 2018/5/10.
 */
public class CommonUtil
{
    public static final char[] chars={'0','1','2','3','4','5','6','7','8','9'};
    public static Map setPageParam(Integer pageIndex , Integer pageSize){
        if(pageIndex == null || pageIndex <1){
            pageIndex = 1;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if(pageSize == null){
            map.put("start", null);
            map.put("end", null);
        }else {
            map.put("start", (pageIndex - 1) * pageSize);
            map.put("end", pageSize);
        }
        return map;
    }
    public static String getRandomCode(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String _code = format.format(date);
        String code = _code+ RandomStringUtils.random(5,chars);
        return code;
    }


}
