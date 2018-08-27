package com.zhskg.bag.util;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.zhskg.bag.data.LoginInfo;

/**
 * Created by fuhaibo on 2017/6/2.
 */
@Component
public class LoginContext 
{

	@Autowired
    private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	String _key = UUID.randomUUID().toString().replace("-", "");

	private final String app_key="zhjt:app:OnlineUsers:";

	private final String app_token_key="zhjt:app:IdGetToken";
	
	private final String web_token_key="zhjt:weblog:token:";

	public void setAuthCookie(String token, String value, int maxAge,Long userId) {
		CookieHelper.addCookie(response, "token", token, maxAge);
		//用于app获取用户信息
		RedisUtil.setex(app_key + token, 86400*30, value);//存入token以及用户资料
		RedisUtil.setex(app_token_key+userId,86400*30,token);
	}
	/**
	 * web端存用户信息
	 * @param token
	 * @param value
	 * @param maxAge
	 * @param userId
	 */
	public void setAuthCookieWeb(String token, String value, int maxAge,Long userId) {
		CookieHelper.addCookie(response, "token", token, maxAge);
		//用于获取web的用户信息
		RedisUtil.setex(web_token_key+token,60*60*24,value);
	}


	public boolean isAuthorized() {
		Cookie cookie = CookieHelper.getCookieByName(request, "token");
		if (cookie != null) {
			String key = cookie.getValue();
			boolean result = RedisUtil.exists(app_key + key);
			return result;
		}
		return false;
	}

	public void signOut() {
		Cookie cookie = CookieHelper.getCookieByName(request, "token");
		if (cookie != null) {
			String key = cookie.getValue();
			RedisUtil.delete(app_key + key);
			CookieHelper.removeCookie(request, response, "token");
		}
	}

	public void setValue(String value) {
		Cookie cookie = CookieHelper.getCookieByName(request, "token");
		if (cookie != null) {
			String key = cookie.getValue();
			RedisUtil.setex(app_key + key, 86400, value);
		}
	}

	public LoginInfo getInfoApp() {
		Cookie cookie = CookieHelper.getCookieByName(request, "token");
		try {
			if (cookie != null) {
				//验证单点登录
				//现在收到的token值
				String key = cookie.getValue();
				//用户json数据
				String json = RedisUtil.get(app_key + key);
				if(StringUtils.isEmpty(json)){
					return null;
				}
				LoginInfo info = JSON.parseObject(json, LoginInfo.class);
				//获取登录用户Id
				Long userId = info.getUserId();
				//获取当前登录用户对应的token
				String token = RedisUtil.get(app_token_key + userId);
				//对比当前用户的token和现在收到的token是否一样
				if(StringUtils.endsWithIgnoreCase(token, key)){
					//一样标示是同一台设备
					return info;
				}else{
					//不一样就不是同一台设备
					return null;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
	/**
	 * web端获取登录用户
	 * @return
	 */
	public LoginInfo getInfoWeb() {
		Cookie cookie = CookieHelper.getCookieByName(request, "token");
		try {
			if (cookie != null) {
				//现在收到的token值
				String token = cookie.getValue();
				//用户json数据
				String json = RedisUtil.get(web_token_key+token);
				if(StringUtils.isEmpty(json)){
					return null;
				}
				RedisUtil.expire(web_token_key+token, 60*60*24);
				LoginInfo info = JSON.parseObject(json, LoginInfo.class);
				return info;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	
}
