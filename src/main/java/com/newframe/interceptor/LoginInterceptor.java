
package com.newframe.interceptor;

import com.alibaba.fastjson.JSON;
import com.newframe.common.anony.Anonymous;
import com.newframe.controllers.JsonResult;
import com.newframe.dto.AccessLog;
import com.newframe.dto.RequestUA;
import com.newframe.dto.RequestUser;
import com.newframe.dto.UserAgent;
import com.newframe.enums.SystemCode;
import com.newframe.utils.*;
import com.newframe.utils.log.GwsLogger;
import com.newframe.utils.log.GwsLoggerTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;


/**
 * 【登录拦截器，拦截非登录态的接口调用】
 *
 * @version 4.0.0
 * @author wangdong
 *
 */
@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Value("${spring.corsOrigins}")
	private String corsOrigins;

	private Long startTime = 0L;

	@Autowired
	private CookieUtil cookieUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// TODO: 25/04/2017

		if(handler instanceof HandlerMethod){
			HandlerMethod c = (HandlerMethod) handler;
			Anonymous anonymous =c.getMethodAnnotation(Anonymous.class);

			/**允许匿名访问**/
			if (null != anonymous && anonymous.value()) {
			    return true;
			}
			//打印accessLog
			AccessLog accessLog = new AccessLog();

			String ip = IPUtil.getIpAddr(request);
			accessLog.setIp(ip);

			String url=	request.getRequestURI();
			accessLog.setUrl(url);

			String sid = cookieUtil.getValue(request, CookieUtil.SID);
			accessLog.setSid(sid);

			UserAgent ua = RequestUA.getUserAgent();
			if (null != ua) {
				accessLog.setTerminalType(null != ua.getOsType() ? ua.getOsType().toString() : "");
				accessLog.setTerminalName(ua.getDt());
				accessLog.setDevicesId(ua.getDeviceId());
			}

			accessLog.setUa(request.getHeader("User-Agent"));
			accessLog.setChannelId(request.getParameter("channelId"));
			accessLog.setSecure(request.isSecure());
			accessLog.setPort(String.valueOf(request.getServerPort()));
			//兼容安卓sid,建议和安卓统一
			if (null == accessLog.getSid()){
				accessLog.setSid(request.getHeader("sid"));
			}

			String action = "";
			//获取action
			if(handler instanceof HandlerMethod){
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				action = handlerMethod.getMethod().getName();
			}
			accessLog.setAction(action);


			Long uid = RequestUser.getCurrentUid();
			accessLog.setUserId((null != uid) ? uid.toString() : "");

			/**访问时间**/
			Long durationTime = System.currentTimeMillis()-startTime;
			accessLog.setAccessTime(Long.toString(TimeUnit.MILLISECONDS.toMillis(durationTime))+"ms");

			GlobalConstant.accessLog.set(accessLog);

			GwsLogger.info(GwsLoggerTypeEnum.ACCESSTRACE,accessLog.toString());

			if (null == RequestUser.getCurrentUid()) {
				JsonResult jsonResult = new JsonResult(SystemCode.NEED_LOGIN);
				response.getOutputStream().write(JSON.toJSONString(jsonResult).getBytes());
				response.setContentType("application/json;charset=UTF-8");
				response.setHeader("Access-Control-Allow-Origin", corsOrigins);
				response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
				response.setHeader("Access-Control-Allow-Methods", "POST, GET");
				response.setHeader("Access-Control-Allow-Credentials", "true");

				return false;
			}
			return true;

		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

}
