package com.gws.newframe.services.test;

import com.gws.newframe.dto.OperationResult;
import com.gws.newframe.entity.test.TestUser;

/**
 * @author:wangdong
 * @description:
 */
public interface TestManageService {

    /**
     * 保存TestUser的操作
     * @param testUser
     * @return
     */
    OperationResult<TestUser> saveTestUser(TestUser testUser);

}
