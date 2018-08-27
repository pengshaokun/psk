package com.zhskg.bag.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证相关工具类
 */
public class VerifyUtil {

    /**
     * 判断手机号是否合法
     * @param phone 手机号
     * @return Boolean boolean
     * @author huchuan
     * @date 2018年6月25日10:54:43
     */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }

    
    /**
     * 判断字符串是否有空格
     * @param str 字符串
     * @return Boolean boolean
     * @author huchuan
     * @date 2018年6月25日10:54:43
     */
    public static boolean strIsHaveSpace(String str) {
        if (str.contains(" ")) return true;
        else return false;
    }

    /**
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     * @author huchuan
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static boolean isInteger(Long str) {
        String newStr = String.valueOf(str);
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(newStr).matches();
    }
}
