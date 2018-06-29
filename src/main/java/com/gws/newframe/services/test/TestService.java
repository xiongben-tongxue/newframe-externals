package com.gws.newframe.services.test;

import com.gws.newframe.entity.test.TestUser;

/**
 * @author:wangdong
 * @description:
 */
public interface TestService {

    /**
     * 保存的操作
     * @param testUser
     * @return
     */
    TestUser saveTestUser(TestUser testUser);
}
