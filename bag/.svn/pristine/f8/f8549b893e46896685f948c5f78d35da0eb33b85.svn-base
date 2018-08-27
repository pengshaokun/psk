package com.zhskg.bag.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateFormat 
{
	public static long getTimeSta(String dates) throws Exception
	{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(dates);
		return date.getTime();
		//return format.format(date);
	}

	public static String date2String(long dates)
	{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(dates);
		return format.format(date);
	}

	public static String bathDay(long dates)
	{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(dates);
		return format.format(date);
	}

	public static String getStringFormat(long date)
	{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date dat=new Date(date);
		return format.format(dat);
	}

	public static String getStringFormat(long date,String pattan)
	{
		SimpleDateFormat format=new SimpleDateFormat(pattan);
		Date dat=new Date(date);
		return format.format(dat);
	}

	public static long getLongFormat(String date)
	{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(date).getTime();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出现错误！！！");
			return 0L;
		}

	}

	public static String toStrings(Date date)
	{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		return format.format(date);
	}

}
