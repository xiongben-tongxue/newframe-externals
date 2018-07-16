package com.newframe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 【新生成的用户】
 *
 * @author wangdong  11/26/16.
 */
@Data
public class LoginInfo {

    private Long uid;

    private String token;

    public LoginInfo(Long uid, String token) {
        this.uid = uid;
        this.token = token;
    }
}
