
package com.newframe.utils.query.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 查询绑定实体类
 *
 * @version 
 * @author wangdong  2016年4月16日 下午6:18:03
 * 
 */
@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QBindEntity {
	public abstract Class<?> entityClass();
}
