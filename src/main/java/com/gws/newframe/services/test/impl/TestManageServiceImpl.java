package com.gws.newframe.services.test.impl;

import com.gws.newframe.dto.OperationResult;
import com.gws.newframe.entity.test.TestUser;
import com.gws.newframe.enums.BizErrorCode;
import com.gws.newframe.services.test.TestManageService;
import com.gws.newframe.services.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author:wangdong
 * @description:
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
        //这个地方有一些问题
        Optional<TestUser> optionalUser = Optional.ofNullable(testUser);
        if (!optionalUser.isPresent()){
            return new OperationResult<>(BizErrorCode.PARAM_INFO_ERROR);
        }
        TestUser result = testService.saveTestUser(testUser);

        if (null == result){
            return new OperationResult<>(BizErrorCode.SAVE_INFO_ERROR);
        }
        return new OperationResult<>(result);
    }
}
