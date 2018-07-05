
package com.gws.newframe.interceptor;

import com.gws.newframe.dto.RequestPub;
import com.gws.newframe.dto.RequestUA;
import com.gws.newframe.dto.RequestUser;
import com.gws.newframe.utils.CookieUtil;
import com.gws.newframe.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * http 拦截器 demo
 *
 * @version 
 * @author wangdong  2016年4月12日 下午1:35:11
 * 
 */
public class HttpInterceptor extends HandlerInterceptorAdapter {

	private Long startTime = 0L;

	@Autowired
	private CookieUtil cookieUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

	    /**清理线程变量**/
		RequestUser.clear();
		RequestUA.clear();
		RequestPub.clear();

		/**设置user-agent*/
	    String ua = request.getHeader("User-Agent");
		String ip = IPUtil.getIpAddr(request);
	    RequestUA.put(ua, ip);

		/**设置公共请求参数*/
		RequestPub.PubParam pubParam = new RequestPub.PubParam();
		pubParam.setChannelId(request.getParameter("channelId"));
		pubParam.setSid(cookieUtil.getValue(request, CookieUtil.SID));

		RequestPub.put(pubParam);
		startTime = System.currentTimeMillis();
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		super.afterCompletion(request, response, handler, ex);
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
