package com.zhskg.bag.netty.util;

/**
 * Created by xiangshiquan on 2017/7/17.
 */
public class StringsUtil {
    public static String PadLeft(String value, int length, char padChar) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str += padChar;
        }
        value = str.substring(0, length - value.length()) + value;
        return value;
    }

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param b byte[] 需要转换的字节数组
     * @return String 十六进制字符串
     */
    public static final String Byte2Hex(byte b[], String splitString) {
        if (b == null) {
            throw new IllegalArgumentException(
                    "Argument b ( byte array ) is null! ");
        }
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xff);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp + splitString;
            } else {
                hs = hs + stmp + splitString;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 十六进制串转化为byte数组
     *
     * @return the array of byte
     */
    public static final byte[] Hex2Byte(String hex)
            throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }

    public static final String Array2HexSpaceString(int[] arr) {
        StringBuffer buffer = new StringBuffer();
        for (int it : arr) {
            buffer.append(PadLeft(Integer.toHexString(it), 2, '0') + " ");
        }
        return buffer.toString();
    }
}

