package com.zhskg.bag.netty.util;

import org.apache.commons.lang.StringUtils;

/**
 * CRC8 校验码
 * Created by xiangshiquan on 2017/7/17.
 */
public final class CRC8 {





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
        CRC8 cRC8 = new CRC8();
        return (byte) cRC8.CRC(crc8Data);
    }
}
