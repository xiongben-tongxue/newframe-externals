
package com.newframe.dto;

import lombok.Data;

/**
 * sql访问日志
 *
 * @version 
 * @author wangdong  2016年7月18日 上午10:27:26
 * 
 */
@Data
public class SqlAccessLog {
	/**当前访问数据源*/
    private  String dataSource;
    /**当前生成SQL*/
    private  String sql;
    /**调用DAO类名*/
    private  String JpaRepositoryClass;
    /**是否分表*/
    private Boolean isSharding= false;
	/**sql执行时间*/
	private String accessTime;

	@Override
	public String toString() {
		return "dataSource=[" + dataSource + "]`sql=" + sql + "`JpaRepositoryClass=" + JpaRepositoryClass 
				+ "`accessTime=" + accessTime+"`isSharding=" + isSharding;
	}
	
}
