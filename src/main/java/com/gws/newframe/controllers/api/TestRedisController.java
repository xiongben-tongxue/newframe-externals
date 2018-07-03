package com.gws.newframe.controllers.api;

import com.gws.newframe.controllers.BaseController;
import com.gws.newframe.services.test.TestManageService;
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

}
