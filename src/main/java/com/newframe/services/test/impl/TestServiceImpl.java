package com.newframe.services.test.impl;

import com.newframe.common.cache.CacheModule;
import com.newframe.entity.test.TestUser;
import com.newframe.repositories.dataMaster.test.TestUserMaster;
import com.newframe.repositories.dataQuery.TestUserQuery;
import com.newframe.repositories.dataSlave.test.TestUserSlave;
import com.newframe.services.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author:wangdong
 * @description:Test的原子层接口
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestUserMaster testUserMaster;

    @Autowired
    private TestUserSlave testUserSlave;
    /**
     * 保存的操作
     *
     * @param testUser
     * @return
     */
    @Cacheable(cacheNames = CacheModule.MIN5,
            key = "T(com.newframe.common.cache.CachePrefix).USER_TESTUSER.concat(T(String).valueOf(#testUser.getUid()))")
    @Override
    public TestUser saveTestUserByMaster(TestUser testUser) {
        if (null == testUser){
            return null;
        }

        return testUserMaster.save(testUser);
    }

    /**
     * 根据Uid查找
     *
     * @param uid
     * @return
     */
    @Cacheable(cacheNames = CacheModule.MIN5,
            key = "T(com.newframe.common.cache.CachePrefix).USER_TESTUSER.concat(T(String).valueOf(#uid))")
    @Override
    public Optional<TestUser> getTestUser(Long uid) {
        if (null == uid){
            return null;
        }

        return testUserMaster.findById(uid);
    }

    @Override
    public TestUser saveTestUserBySlave(TestUser testUser) {
        if (null == testUser){
            return null;
        }

        return testUserSlave.save(testUser);
    }

    /**
     * 根据条件查询
     *
     * @param age
     * @return
     */
    @Override
    public List<TestUser> listTestUserByAge(Integer age) {
        if (null == age){
            return Collections.EMPTY_LIST;
        }

        TestUserQuery query = new TestUserQuery();
        query.setAge(age);
        List<TestUser> testUsers = testUserSlave.findAll(query);

        return CollectionUtils.isEmpty(testUsers) ? Collections.EMPTY_LIST : testUsers;
    }

    /**
     * 多条件查询
     *
     * @param age
     * @param name
     * @return
     */
    @Override
    public List<TestUser> listTestUserByAgeAndName(Integer age, String name) {

        if (null == age || StringUtils.isEmpty(name)){
            return Collections.EMPTY_LIST;
        }
        TestUserQuery query = new TestUserQuery();
        query.setAge(age);
        query.setName(name);
        List<TestUser> testUsers = testUserSlave.findAll(query);
        return CollectionUtils.isEmpty(testUsers) ? Collections.EMPTY_LIST : testUsers;
    }
}
