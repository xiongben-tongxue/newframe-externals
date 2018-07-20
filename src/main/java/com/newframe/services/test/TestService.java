package com.newframe.services.test;

import com.newframe.entity.test.TestUser;
import org.springframework.data.domain.Page;

import java.util.List;
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

    /**
     * 操作从库
     * @param testUser
     * @return
     */
    TestUser saveTestUserBySlave(TestUser testUser);

    /**
     * 根据条件查询
     * @param age
     * @return
     */
    List<TestUser> listTestUserByAge(Integer age);

    /**
     * 多条件查询
     * @param age
     * @param name
     * @return
     */
    List<TestUser> listTestUserByAgeAndName(Integer age, String name);

    /**
     * 分页查询
     * @param name
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<TestUser> listTestUser(String name, Integer currentPage, Integer pageSize);
}
