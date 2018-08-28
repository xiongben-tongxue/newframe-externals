package com.newframe.dto.user;

import lombok.Data;

/**
 * @author:wangdong
 * @description:
 */
@Data
public class User {

    private Long uid;

    private String name;

    private Integer age;

    private Integer ctime; //创建时间

    private Integer utime; //更新时间
}
