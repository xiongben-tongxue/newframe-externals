package com.newframe.entity.test;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author:wangdong
 * @description:
 */
@Data
@Entity
@Table(name = "test_user")
public class TestUser implements Serializable {

    private static final long serialVersionUID = -7934771152804718613L;
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
