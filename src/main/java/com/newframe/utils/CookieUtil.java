package com.newframe.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * cookie 工具类
 *
 * @author wangdong<wangdong@zjyushi.com>
 * @version 1.0
 * @data
 */
@Configuration
public class CookieUtil {

	@Value("${spring.domain}")
	private String domain;

	public static String TOKEN = "token";
	public static String SID = "sid";
    public static String UID = "uid";

    public static String WEBUID = "Uid";
    public static String WEBTOKEN = "Token";



	/**
	 * 添加cookie信息
	 *
	 * @param response
	 * @param name
	 * @param value
	 * @param path
	 * @param maxAge
	 */
	public void addCookie(HttpServletResponse response, String name,
                          String value, String path, int maxAge) {
		try {
			Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
			cookie.setMaxAge(maxAge);
			cookie.setPath(path);
			cookie.setDomain(domain);
			GwsLogger.info("addCookie domain=" + domain + ",path=" + path + ",cookie Value=" + cookie.getValue());
			response.addCookie(cookie);
			GwsLogger.info("addCookie cookie value=" + cookie.getValue());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}


	public void addCookie(HttpServletResponse response, String name,
                          String value) {
		try {
			Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
			cookie.setDomain(domain);
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 获取cookie值
	 *
	 * @param request
	 * @param name
	 * @return
	 */
	public String getValue(HttpServletRequest request, String name) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					try {
						value = URLDecoder.decode(cookie.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException(e.getMessage());
					}
					break;
				}
			}
		}
		return value;
	}

	/**
	 * 删除cookie信息
	 *
	 * @param response
	 * @param name
	 */
	public void delCookie(HttpServletRequest request, HttpServletResponse response,
                          String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			// 遍历浏览器发送到服务器端的所有Cookie，找到自己设置的Cookie
			for (Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				if (cookieName.equals(name)) {
					cookie = new Cookie(name, null);
					// 设置Cookie立即失效
					cookie.setMaxAge(0);
					/**
					 * 删除Cookie时，只设置maxAge=0将不能够从浏览器中删除cookie,
					 * 因为一个Cookie应当属于一个path与domain，所以删除时，Cookie的这两个属性也必须设置。
					 */
					cookie.setDomain(domain);
					// 必须设置path属性的值  
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
	}

}
