package com.zhskg.bag.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

public final class DateUtil 
{
	public static String getStringDate(long times)
	{
		Date dt = new Date(times);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(dt);
	}
	
	public static String timeKey()
	{
		Date dt = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(dt)+RandomStringUtils.randomNumeric(5);
	}
}
