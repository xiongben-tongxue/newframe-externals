package com.gws.newframe.dto;

import lombok.Data;

/**
 * 【请求辅助工具类】
 *
 * @author wangdong  3/24/17.
 */
public class RequestPub {

    private static final ThreadLocal<PubParam> currentPub= new ThreadLocal<>();

    public static void put(PubParam pubParam) {
        currentPub.set(pubParam);
    }

    public static String getCurrentChannelId() {
        RequestPub.PubParam pubParam  = currentPub.get();
        return null != pubParam ? pubParam.getChannelId() : null;
    }

    public static String getTimestamp() {
        RequestPub.PubParam pubParam  = currentPub.get();
        return null != pubParam ? pubParam.getTimestamp() : null;
    }

    public static String getSid() {
        RequestPub.PubParam pubParam  = currentPub.get();
        return null != pubParam ? pubParam.getSid() : null;
    }

    public static void clear() {
        currentPub.remove();
    }

    @Data
    public static class PubParam {
        private String timestamp;
        private String channelId;
        private String sid;
    }
}
