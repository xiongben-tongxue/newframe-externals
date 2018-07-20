package com.newframe.services.test;

import com.newframe.dto.OperationResult;
import com.newframe.entity.test.TestUser;
import org.springframework.data.domain.Page;

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

    /**
     * 多条件，根据Query查询
     * @param age
     * @param name
     * @return
     */
    OperationResult<List<TestUser>> listTestUserByAgeAndName(Integer age, String name);

    /**
     * 分页查询
     * @param name
     * @param currentPage
     * @param pageSize
     * @return
     */
    OperationResult<Page<TestUser>> listTestUser(String name, Integer currentPage, Integer pageSize);
}
