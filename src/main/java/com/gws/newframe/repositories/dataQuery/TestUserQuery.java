package com.gws.newframe.repositories.dataQuery;

import com.gws.newframe.entity.test.TestUser;
import com.gws.newframe.utils.query.BaseQuery;
import com.gws.newframe.utils.query.Where;
import com.gws.newframe.utils.query.annotation.QBindAttrField;
import com.gws.newframe.utils.query.annotation.QBindEntity;
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
}
