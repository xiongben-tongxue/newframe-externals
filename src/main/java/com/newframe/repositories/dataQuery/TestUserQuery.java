package com.newframe.repositories.dataQuery;

import com.newframe.entity.test.TestUser;
import com.newframe.utils.query.BaseQuery;
import com.newframe.utils.query.Where;
import com.newframe.utils.query.annotation.QBindAttrField;
import com.newframe.utils.query.annotation.QBindEntity;
import lombok.Data;

/**
 * @author:wangdong
 * @description:
 */
@QBindEntity(entityClass = TestUser.class)
@Data
public class TestUserQuery extends BaseQuery {

    @QBindAttrField(fieldName = "age", where = Where.equal)
    private Integer age;

    @QBindAttrField(fieldName = "name", where = Where.equal)
    private String name;

    //模糊查询
    @QBindAttrField(fieldName = "name", where = Where.like)
    private String likeName;
}
