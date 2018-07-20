package com.newframe.services.test.impl;

import com.newframe.dto.OperationResult;
import com.newframe.entity.test.TestUser;
import com.newframe.enums.BizErrorCode;
import com.newframe.repositories.dataQuery.TestUserQuery;
import com.newframe.services.test.TestManageService;
import com.newframe.services.test.TestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author:wangdong
 * @description:外层的Service
 */
@Service
public class TestManageServiceImpl implements TestManageService {

    @Autowired
    private TestService testService;

    /**
     * 保存TestUser的操作
     *
     * @param testUser
     * @return
     */
    @Override
    public OperationResult<TestUser> saveTestUser(TestUser testUser) {
        if (null == testUser) {
            return new OperationResult<>(BizErrorCode.PARAM_INFO_ERROR);
        }
        TestUser result = testService.saveTestUserByMaster(testUser);
        Optional<TestUser> optionalUser = Optional.ofNullable(testUser);

        if (!optionalUser.isPresent()) {
            return new OperationResult<>(BizErrorCode.SAVE_INFO_ERROR);
        }

        return new OperationResult<>(result);
    }

    /**
     * 根据Id获取
     *
     * @param uid
     * @return
     */
    @Override
    public OperationResult<TestUser> getTestUser(Long uid) {
        if (null == uid) {
            return new OperationResult<>(BizErrorCode.PARAM_INFO_ERROR);
        }

        Optional<TestUser> testUser = testService.getTestUser(uid);

        return new OperationResult<>(testUser.get());
    }

    @Override
    public OperationResult<TestUser> saveTestUserByMaster(TestUser testUser) {
        if (null == testUser) {
            return new OperationResult<>(BizErrorCode.PARAM_INFO_ERROR);
        }
        TestUser result = testService.saveTestUserByMaster(testUser);
        Optional<TestUser> optionalUser = Optional.ofNullable(testUser);

        if (!optionalUser.isPresent()) {
            return new OperationResult<>(BizErrorCode.SAVE_INFO_ERROR);
        }

        return new OperationResult<>(result);
    }

    @Override
    public OperationResult<TestUser> saveTestUserBySlave(TestUser testUser) {
        if (null == testUser) {
            return new OperationResult<>(BizErrorCode.PARAM_INFO_ERROR);
        }
        TestUser result = testService.saveTestUserBySlave(testUser);
        Optional<TestUser> optionalUser = Optional.ofNullable(testUser);

        if (!optionalUser.isPresent()) {
            return new OperationResult<>(BizErrorCode.SAVE_INFO_ERROR);
        }

        return new OperationResult<>(result);
    }

    /**
     * 根据Query查询
     *
     * @param age
     * @return
     */
    @Override
    public OperationResult<List<TestUser>> listTestUserByAge(Integer age) {
        if (null == age){
            return new OperationResult<>(BizErrorCode.PARAM_INFO_ERROR);
        }

        List<TestUser> testUsers = testService.listTestUserByAge(age);

        return new OperationResult<>(testUsers);
    }

    /**
     * 多条件，根据Query查询
     *
     * @param age
     * @param name
     * @return
     */
    @Override
    public OperationResult<List<TestUser>> listTestUserByAgeAndName(Integer age, String name) {

        if (null == age || StringUtils.isEmpty(name)){
            return new OperationResult<>(BizErrorCode.PARAM_INFO_ERROR);
        }

        List<TestUser> testUsers = testService.listTestUserByAgeAndName(age,name);

        return new OperationResult<>(testUsers);
    }

    /**
     * 分页查询
     *
     * @param name
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public OperationResult<Page<TestUser>> listTestUser(String name, Integer currentPage, Integer pageSize) {

        if (null == currentPage || null == pageSize){
            return new OperationResult<>(BizErrorCode.PARAM_INFO_ERROR);
        }

        Page<TestUser> testUserPage = testService.listTestUser(name,currentPage,pageSize);

        return new OperationResult<>(testUserPage);
    }
}
