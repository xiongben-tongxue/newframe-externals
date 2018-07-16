
package com.newframe.dto;

import lombok.Data;

/**
 * 用户访问日志
 *
 * @version 
 * @author wangdong  2016年4月19日 下午5:14:38
 * 
 */
@Data
public class AccessLog {

	/**用户访问ip地址*/
	private String ip;

	/**用户访问url地址*/
	private String url;

	/**用户会话sid*/
	private String sid;

	/**用户id*/
	private String userId;

	/**用户ua信息*/
	private String ua;

	/**终端类型*/
	private String terminalType;

	/**终端型号*/
	private String terminalName;

	/**渠道号*/
	private String channelId;

	/**设备ID*/
	private String devicesId;

	/**访问时间*/
	private String accessTime;

	private String port;

	private String action;

	private boolean isSecure;
	
	@Override
	public String toString() {
		return "ip=" + ip + "`url=" + url + "`action="+action+"`ua=" + ua
				+ "`terminalType=" + terminalType + "`terminalName=" + terminalName + "`channelId=" + channelId
				+ "`devicesId=" + devicesId+"`accessTime=" + accessTime+"`port="+port;
	}		
	
}
