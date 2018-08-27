package com.zhskg.bag.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by fuhaibo on 2017/7/17.
 */
public class EncrypUtil {

    public static String convertByteToHexString(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int x = 0; x < bytes.length; x++) {
            String temp = Integer.toHexString(bytes[x] & 0xff);
            if (temp.length() < 2) {
                stringBuffer.append("0").append(temp);
            } else {
                stringBuffer.append(temp);
            }

        }
        return stringBuffer.toString();
    }

    public static String encrypMD5(String info) throws NoSuchAlgorithmException {
        //根据MD5算法生成MessageDigest对象
        if (info == null) return null;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = info.getBytes();
        //使用srcBytes更新摘要
        md5.update(srcBytes);
        //完成哈希计算，得到result
        byte[] resultBytes = md5.digest();
        return convertByteToHexString(resultBytes);
    }


}
