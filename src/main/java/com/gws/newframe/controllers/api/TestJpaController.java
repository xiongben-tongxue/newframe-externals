package com.gws.newframe.controllers.api;

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

    @RequestMapping("saveTestUser")
    private TestUser saveTestUser(TestUser testUser){

        TestUser result = testService.saveTestUser(testUser);

        return result;
    }
}
