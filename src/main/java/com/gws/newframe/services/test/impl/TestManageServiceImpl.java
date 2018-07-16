package com.gws.newframe.services.test.impl;

import com.gws.newframe.dto.OperationResult;
import com.gws.newframe.entity.test.TestUser;
import com.gws.newframe.enums.BizErrorCode;
import com.gws.newframe.services.test.TestManageService;
import com.gws.newframe.services.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
