package com.gws.newframe.dto;

import com.gws.newframe.enums.OsTypeEnum;
import com.gws.newframe.utils.GwsLogger;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 【请求UA解析】
 *
 * @author wangdong  3/24/17.
 */
public class RequestUA {

    private static final ThreadLocal<UserAgent> currentUA = new ThreadLocal<>();
    /**
     * 311之前
     * 匹配格式:GameMall/iOS/3.0.8(5503E68D-610B-4E0C-90A0-A45176C9C790,WIFI,iPhone 5S,11.0,,ARM64)
     */
    /**
     * 311匹配格式
     * IOS
     * GameMall/iOS/3.1.0(SID,WIFI,iPhone,10.1.1,杭州,ARM64,AppleIMEI,iPhone9#2)
     * 特征串/系统类型/app版本号/(sid,网络类型,厂商,操作系统版本,地理位置,CPU类型,IMEI,机型)
     * 安卓
     *  User-Agent: GameMall/Android/3.1.0.1(867981021742153,WIFI,Huawei_Nexus 6P,6.0,,armeabi-v7a,
     *  867981021742153)
     */
    private static final Pattern UA_PATTERN = Pattern.compile("GameMall/(Android|iOS)/[0-9._]+\\(([\\s\\S]+)\\)",Pattern.CASE_INSENSITIVE);

    public static void put(String ua, String ip) {
        UserAgent userAgent = new UserAgent();
        userAgent.setIp(ip);

        try {
            if (!StringUtils.isBlank(ua)) {
                if (UA_PATTERN.matcher(ua).matches()) {
                    String[] os = ua.substring(0, ua.indexOf("(")).split("/");

                    if ("Android".equals(os[1])) {
                        userAgent.setOsType(OsTypeEnum.Android.getCode());
                    } else if ("iOS".equals(os[1])) {
                        userAgent.setOsType(OsTypeEnum.iOS.getCode());
                    }
                    userAgent.setAppVersion(os[2]);

                    String[] extras = ua.substring(ua.indexOf("(") + 1, ua.length() - 1).split(",");
                    //兼容老版的
                    if (extras.length == 6) {
                        userAgent.setDeviceId(extras[0]);
                        userAgent.setNt(extras[1]);
                        userAgent.setDt(extras[2]);
                        userAgent.setDeviceVersion(extras[3]);
                        userAgent.setArea(null == extras[4] ? "" : extras[4]);
                        userAgent.setCpu(extras[5]);
                    }

                    //新版的
                    if (extras.length == 8) {
                        userAgent.setDeviceId(extras[0]);
                        userAgent.setNt(extras[1]);
                        userAgent.setDt(extras[2]);
                        userAgent.setDeviceVersion(extras[3]);
                        userAgent.setArea(null == extras[4] ? "" : extras[4]);
                        userAgent.setCpu(extras[5]);
                        //安卓有唯一标示,IOS固定写AppleIMEI
                        userAgent.setImei(extras[6]);
                        userAgent.setModel(extras[7]);
                        userAgent.setManufacturer(extras[2]);
                    }

                } else if (ua.contains("IOS") || ua.contains("iOS") || ua.contains("Android")) {
                    //匹配格式:YOUXIMAO.COM/3.1.0/1.0.0.0/IOS/10.2.1/iPhone 5S/5C66667A-A1B8-4163-8446-34FF521427BF
                    String[] os = ua.split("/");
                    /*if (!StringUtils.isBlank(os[3]) && "ios".equals(os[3].toLowerCase())) {
                        userAgent.setOsType(OsTypeEnum.iOS.getCode());
                    } else if (!StringUtils.isBlank(os[3]) && "Android".equals(os[3])) {
                        userAgent.setOsType(OsTypeEnum.Android.getCode());
                    }*/
                }
            }
        } catch (Exception e) {
            GwsLogger.error(e, "parse ua[{}] error", ua);
        }

        currentUA.set(userAgent);

    }

    public static UserAgent getUserAgent() {
        return currentUA.get();
    }


    public static void clear() {
        currentUA.remove();
    }
}
