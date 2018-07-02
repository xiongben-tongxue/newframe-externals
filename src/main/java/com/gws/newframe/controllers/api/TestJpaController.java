package com.gws.newframe.controllers.api;

import com.gws.newframe.dto.User;
import com.gws.newframe.entity.test.TestUser;
import com.gws.newframe.services.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:wangdong
 * @description:测试Jpa和druid是否配置成功的类
 */
@RestController
@RequestMapping("/api/testJpa/")
public class TestJpaController {

    @Autowired
    private TestService testService;

    /**
     * 接收一个实体类，非数据库表的实体类
     * @param user
     * @return
     */
    @RequestMapping("saveUser")
    private TestUser saveUser(User user){

        TestUser result = testService.saveTestUser(user);

        return result;
    }

    /**
     * 接收实体类，对应于数据库的表
     * @param testUser
     * @return
     */
    @RequestMapping("saveTestUser")
    private TestUser saveTestUser(TestUser testUser){

        TestUser result = testService.saveTestUser(testUser);

        return result;
    }
}
