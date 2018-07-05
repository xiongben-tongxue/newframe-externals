package com.gws.newframe.dto;

import lombok.Data;

/**
 * 【请求辅助工具类】
 *
 * @author wangdong  3/24/17.
 */
public class RequestUser {

    private static final ThreadLocal<User> currentUserInfo = new ThreadLocal<>();

    public static void put(User user) {
        currentUserInfo.set(user);
    }

    public static Long getCurrentUid() {
        RequestUser.User user  = currentUserInfo.get();

        return null != user ? user.getUid() : null;
    }

    public static String getToken() {
        User user = currentUserInfo.get();
        return null != user ? user.getToken() : null;
    }

    public static void clear() {
        currentUserInfo.remove();
    }

    @Data
    public static class User {
        private Long uid;
        private String token;
    }
}
