package com.zhskg.bag.util;

import java.util.Random;

/**
 * Created by wa on 2017/6/20.
 */
public class RandomString {
    private static Random rnd = new Random();
    private static Object lockobj = new Object();
    public static String createRandomOnlyString(int length)
    {
        return  createRandomOnlyString(length,"1234567890");
    }
    public static String createRandomOnlyString(int length, String chars)
    {
        synchronized (lockobj)
        {
            StringBuffer  code = new StringBuffer();
            for (int i = 0; i < length; i++)
            {
                code.append(chars.charAt(rnd.nextInt(chars.length() - 1)));
            }
            return code.toString();
        }
    }
}
