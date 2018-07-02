package com.gws.newframe.controllers.api;

import com.gws.newframe.controllers.BaseController;
import com.gws.newframe.controllers.JsonResult;
import com.gws.newframe.dto.OperationResult;
import com.gws.newframe.entity.test.TestUser;
import com.gws.newframe.services.test.TestManageService;
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
public class TestJpaController extends BaseController {

    @Autowired
    private TestManageService testManageService;

    /**
     * 接收实体类，对应于数据库的表
     * @param testUser
     * @return
     */
    @RequestMapping("saveTestUser")
    private JsonResult saveTestUser(TestUser testUser){

        OperationResult<TestUser> result = testManageService.saveTestUser(testUser);

        return success(result);
    }
}
