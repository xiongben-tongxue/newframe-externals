package com.gws.newframe.services.test;

import com.gws.newframe.dto.OperationResult;
import com.gws.newframe.entity.test.TestUser;

import java.util.List;

/**
 * @author:wangdong
 * @description:外层的Service
 */
public interface TestManageService {

    /**
     * 保存TestUser的操作
     * @param testUser
     * @return
     */
    OperationResult<TestUser> saveTestUser(TestUser testUser);

    /**
     * 根据Id获取
     * @param uid
     * @return
     */
    OperationResult<TestUser> getTestUser(Long uid);

    /**
     * 操作主库
     * @param testUser
     * @return
     */
    OperationResult<TestUser> saveTestUserByMaster(TestUser testUser);

    /**
     * 操作从库
     * @param testUser
     * @return
     */
    OperationResult<TestUser> saveTestUserBySlave(TestUser testUser);

    /**
     * 根据Query查询
     * @param age
     * @return
     */
    OperationResult<List<TestUser>> listTestUserByAge(Integer age);
}
