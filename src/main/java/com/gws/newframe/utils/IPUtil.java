package com.gws.newframe.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP操作工具类
 * @author wangdong
 */
public class IPUtil {

	/**
	 *
	 * 【获取请求ip地址】
	 *
	 * @author wangdong 2016年4月17日
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (null == request) {
			return null;
		}
		String proxs[] = { "X-Forwarded-For" };
		String ip = request.getHeader(proxs[0]);

		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15
			if(ip.indexOf(",")>0){
				ip = ip.substring(0,ip.indexOf(","));
			}
		}

		if (null == ip) {
			return request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 【是否在ip白名单ip内】
	 * @author wangdong
	 * @param ip
	 * @param whiteListArg
     * @return
     */
	public static boolean isIpInWhitelist(String ip, String whiteListArg) {
		//允许所有
		if(whiteListArg.trim().equals("*")){
			return true;
		}
		boolean result = false;
		String[] whitelistArray = whiteListArg.split(";");
		for (String whitelist : whitelistArray) {
			whitelist = whitelist.trim();
			String[] whiteListConfig = whitelist.split("/");
			if (whiteListConfig.length == 1) {
				if (whiteListConfig[0].equals(ip)) {
					result = true;
					break;
				}
			} else if (whiteListConfig.length == 2) {
				try {
					int whiteListIp = getIpInt(whiteListConfig[0]);
					int requestIp = getIpInt(ip);
					int mask = 0xffffffff << (32 - Integer
							.valueOf(whiteListConfig[1]));
					if ((requestIp & mask) == (whiteListIp & mask)) {
						result = true;
						break;
					}
				} catch (UnknownHostException e) {
					throw new RuntimeException();
				}
			}
		}
		return result;
	}

	private static int getIpInt(String hostIp) throws UnknownHostException {
		byte[] address = InetAddress.getByName(hostIp).getAddress();
		int ipI = 0;
		ipI |= ((address[0] << 24) & 0xff000000);
		ipI |= ((address[1] << 16) & 0xff0000);
		ipI |= ((address[2] << 8) & 0xff00);
		ipI |= (address[3] & 0xff);
		return ipI;
	}
}
