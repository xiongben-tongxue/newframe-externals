package com.gws.newframe.services.test;

import com.gws.newframe.entity.test.TestUser;

import java.util.Optional;

/**
 * @author:wangdong
 * @description:原子层的操作
 */
public interface TestService {

    /**
     * 实体类保存的操作
     * @param testUser
     * @return
     */

    TestUser saveTestUserByMaster(TestUser testUser);

    /**
     * 根据Uid查找
     * @param uid
     * @return
     */
    Optional<TestUser> getTestUser(Long uid);

    TestUser saveTestUserBySlave(TestUser testUser);
}
