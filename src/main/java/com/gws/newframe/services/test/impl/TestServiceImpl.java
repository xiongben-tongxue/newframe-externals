package com.gws.newframe.services.test.impl;

import com.gws.newframe.dto.User;
import com.gws.newframe.entity.test.TestUser;
import com.gws.newframe.repositories.test.TestUserRepository;
import com.gws.newframe.services.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:wangdong
 * @description:Test的原子层接口
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestUserRepository testUserRepository;
    /**
     * 保存的操作
     *
     * @param testUser
     * @return
     */
    @Override
    public TestUser saveTestUser(TestUser testUser) {
        if (null == testUser){
            return null;
        }

        return testUserRepository.save(testUser);
    }

    @Override
    public TestUser saveTestUser(User user) {
        return null;
    }
}
