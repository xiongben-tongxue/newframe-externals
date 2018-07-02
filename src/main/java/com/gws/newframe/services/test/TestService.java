package com.gws.newframe.services.test;

import com.gws.newframe.dto.User;
import com.gws.newframe.entity.test.TestUser;

/**
 * @author:wangdong
 * @description:
 */
public interface TestService {

    /**
     * 实体类保存的操作
     * @param testUser
     * @return
     */
    TestUser saveTestUser(TestUser testUser);

    /**
     * 非实体类的保存操作
     * @param user
     * @return
     */
    TestUser saveTestUser(User user);
}
