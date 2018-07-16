
package com.newframe.utils.query.annotation;



import com.newframe.utils.query.Where;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询绑定属性
 *
 * @version 
 * @author wangdong  2016年4月16日 下午6:20:57
 * 
 */
@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QBindAttrField {
	public abstract String fieldName();
	public abstract Where where();
}
