
package com.gws.newframe.configuration;

import com.gws.newframe.interceptor.HttpInterceptor;
import com.gws.newframe.interceptor.IPWhitelistInterceptor;
import com.gws.newframe.interceptor.IdentityInterceptor;
import com.gws.newframe.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * spring web 配置
 *
 * @version 
 * @author wangdong
 */
@Configuration
@ComponentScan("com.gws.newframe.interceptor")
public class GwsWebConfig extends WebMvcConfigurationSupport {

	@Value("${spring.corsOrigins}")
	private String corsOrigins;

	/**
	 *
	 * 1、通用接口规范推荐
	 * 1）非app, web, api接口，一律拦截
	 *
	 * 2、app/web拦截
	 * 1) 身份认证模块
	 * 2) 登录拦截
	 *
	 * 3、api拦截器 ：
	 * 1) ip白名单
	 *
	 * 4、web拦截
	 * 1）cors
	 *
	 *
	 * 5、通用日志模块 httpInterceptor
	 *
	 **/
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(httpInterceptor());

		/**app和web接口**/

		registry.addInterceptor(identityInterceptor()).addPathPatterns("/web/**");
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/web/**");

		registry.addInterceptor(identityInterceptor()).addPathPatterns("/app/**");
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/app/**");


		/**api接口**/
		registry.addInterceptor(iPWhitelistInterceptor()).addPathPatterns("/api/**");

		/** 兼容老接口 */
		registry.addInterceptor(identityInterceptor()).addPathPatterns("/guildnew/**");
	}


	@Bean
	public HandlerInterceptor httpInterceptor() {
		return new HttpInterceptor();
	}

	@Bean
	public HandlerInterceptor identityInterceptor() {
		return new IdentityInterceptor();
	}

	@Bean
	public HandlerInterceptor iPWhitelistInterceptor() {
		return new IPWhitelistInterceptor();
	}

	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	/**
	 *  
	 * 跨站 HTTP 请求支持
	 *
	 * 
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry)
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/web/**").
				allowedOrigins(corsOrigins).
                allowedMethods("POST", "GET").
                allowedHeaders("Origin, X-Requested-With, Content-Type, Accept").
                allowCredentials(true).
                maxAge(3600);
		super.addCorsMappings(registry);
	}

}
