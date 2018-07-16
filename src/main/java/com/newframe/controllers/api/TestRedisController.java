package com.newframe.controllers.api;

import com.newframe.controllers.BaseController;
import com.newframe.controllers.JsonResult;
import com.newframe.dto.OperationResult;
import com.newframe.entity.test.TestUser;
import com.newframe.services.test.TestManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:wangdong
 * @description:测试redis的Controller
 */
@RestController
@RequestMapping("/api/testJpa/")
public class TestRedisController extends BaseController {

    @Autowired
    private TestManageService testManageService;


    /**
     * 根据redis去获取数据
     * @param uid
     * @return
     */
    @RequestMapping("getTestUser")
    private JsonResult getTestUser(Long uid){

        OperationResult<TestUser> result = testManageService.getTestUser(uid);

        if (result.getSucc()){
            return success(result.getEntity());
        }
        return error(result.getErrorCode());
    }
}
