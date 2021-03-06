package com.zhskg.bag.intercepter;


import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMap;


public class AuthorizeInterceptor implements HandlerInterceptor
{

	@Autowired
	private LoginContext loginContext;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
		httpServletResponse.setCharacterEncoding("utf-8");
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			AllowAnonymous allowAnonymous = method.getAnnotation(AllowAnonymous.class);
			if (allowAnonymous != null) {
				return true;//true的意思是可以继续执行，false是执行中断。
			}
			else {
				LoginInfo infoApp = loginContext.getInfoApp();
				if (infoApp != null) {
					return true;
				}
				LoginInfo infoWeb = loginContext.getInfoWeb();
				if (infoWeb != null) {
					return true;
				}
				//httpServletResponse.setHeader("type","nosubmit")
				httpServletResponse.setHeader("Content-Type","application/json");
				httpServletResponse.setStatus(405);
				httpServletResponse.getWriter().write(JSON.toJSONString(ReturnMap.result(3, "用户未登录")));
				return false;


			}
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) throws Exception {

	}
}

