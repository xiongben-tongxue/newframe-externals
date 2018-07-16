
package com.newframe.interceptor;

import com.alibaba.fastjson.JSON;
import com.newframe.controllers.JsonResult;
import com.newframe.enums.SystemCode;
import com.newframe.utils.GwsLogger;
import com.newframe.utils.IPUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 【ip白名单拦截器】
 *
 * @version 4.0.0
 * @author wangdong
 *
 */

@Configuration
public class IPWhitelistInterceptor extends HandlerInterceptorAdapter {


	@Value("${service.api.ipwhite}")
    private String ipWhilteList = "*";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String reqIp = request.getRemoteAddr();
		if (!IPUtil.isIpInWhitelist(reqIp, ipWhilteList)) {
			GwsLogger.info("the IP address : " + reqIp + "is not allowed" );
			JsonResult jsonResult  = new JsonResult(SystemCode.NOT_IN_WHITELIST);
			response.getOutputStream().write(JSON.toJSONString(jsonResult).getBytes());
			return false;
		}
		return true;
	}

}
