package com.gws.newframe.entity.test;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author:wangdong
 * @description:
 */
@Data
@Entity
@Table(name = "test_user")
public class TestUser {

    @Id
    @Column(name = "uid")
    private Long uid;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "ctime")
    private Integer ctime; //创建时间

    @Column(name = "utime")
    private Integer utime; //更新时间
}
