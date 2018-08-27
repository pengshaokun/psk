package com.zhskg.bag.util;

import java.util.HashMap;
import java.util.Map;

public final class ReturnMapByBack
{
	public static Map<String,Object> result(final int code,String msg)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	public static Map<String,Object> result(final int code,String msg,Object obj)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("data", obj);
		return map;
	}
	
	public static Map<String,Object> result(final int code,String msg,Object obj,int total)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("rows", obj);
		map.put("total", total);
		return map;
	}

	


}
