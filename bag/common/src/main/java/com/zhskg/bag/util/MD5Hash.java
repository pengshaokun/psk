package com.zhskg.bag.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

public final class MD5Hash 
{
	private static String salt = "lwb";
	//散列次数
	private static int hashIterations = 1;
	public static String hash(String str)
	{
		SimpleHash simpleHash = new SimpleHash("md5", str, salt, hashIterations);
		return simpleHash.toHex();
	}

	public static void main(String[] args)
	{
		System.out.println(hash("123456"));
		System.out.println(DigestUtils.md5Hex("123456"));
	}
}
