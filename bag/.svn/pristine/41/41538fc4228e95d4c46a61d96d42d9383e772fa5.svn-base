package com.zhskg.bag.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证工具类
 * @author Administrator
 *
 */
public class RegexValidateUtil {
	
		
	 /**
	  * 验证邮箱
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
		  boolean flag = false;
		  try{
		    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		    Pattern regex = Pattern.compile(check);
		    Matcher matcher = regex.matcher(email);
		    flag = matcher.matches();
		   }catch(Exception e){
		    flag = false;
		   }
		  return flag;
	 }
	 
	 /**
	  * 验证手机号码
	  * @param mobileNumber
	  * @return
	  */
	 public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}");
	    Matcher matcher = regex.matcher(mobileNumber);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	
	 /**
	  * 验证是否为正整数
	  * @param Integer
	  * @return
	  */
	 public static boolean checkMobileInteger(String Integer){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("[0-9]\\d*");
	    Matcher matcher = regex.matcher(Integer);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	 
	 /**
	  * 验证不为0正整数
	  * @param Integer
	  * @return
	  */
	 public static boolean checkMobileIntegerNotOOOO(String Integer){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("[1-9]\\d*");
	    Matcher matcher = regex.matcher(Integer);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	 
	 /**
	  * 验证日期格式yyyy-MM-dd
	  * @param date
	  * @return
	  */
	 public static boolean checkDate(String date){
		  boolean flag = false;
		  try{
		    Pattern regex = Pattern.compile("(?:[0-9]{1,4}(?<!^0?0?0?0))-(?:0?[1-9]|1[0-2])-(?:0?[1-9]|1[0-9]|2[0-8]|(?:(?<=-(?:0?[13578]|1[02])-)(?:29|3[01]))|(?:(?<=-(?:0?[469]|11)-)(?:29|30))|(?:(?<=(?:(?:[0-9]{0,2}(?!0?0)(?:[02468]?(?<![13579])[048]|[13579][26]))|(?:(?:[02468]?[048]|[13579][26])00))-0?2-)(?:29)))");
		    Matcher matcher = regex.matcher(date);
		    flag = matcher.matches();
		   }catch(Exception e){
		    flag = false;
		   }
		  return flag;
		 }
	 
	 /**
	  * 验证身份证
	  * @param card
	  * @return
	  */
	 public static boolean checkIdentityCard(String card){
		  boolean flag = false;
		  try{
		    Pattern regex = Pattern.compile("(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)");
		    Matcher matcher = regex.matcher(card);
		    flag = matcher.matches();
		   }catch(Exception e){
		    flag = false;
		   }
		  return flag;
	 }

	/**
	 * 密码校验
	 * @param password
	 * @return boolean
	 */
	public static boolean checkPassword(String password) {
		boolean flag = false;
		try{
			Pattern regex = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
			Matcher matcher = regex.matcher(password);
			flag = matcher.matches();
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
}
