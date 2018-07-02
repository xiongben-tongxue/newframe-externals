package com.gws.newframe.repositories.test;

import com.gws.newframe.entity.test.TestUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author:wangdong
 * @description:TestUser的查询类
 */
public interface TestUserRepository extends CrudRepository<TestUser,Long> {

    @Override
    <S extends TestUser> S save(S s);
}
