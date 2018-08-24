package com.newframe.utils.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author:wangdong
 * @description:
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {

    /**
     * 在BaseRepositoryEx实现增强
      * @param baseQuery
     * @return
     */
    List<T> findAll(BaseQuery baseQuery);

    /**
     * 封装分页查询
     * @param baseQuery
     * @param pageable
     * @return
     */
    Page<T> findAll(BaseQuery baseQuery, Pageable pageable);

    /**
     * 封装排序查询
     * @param baseQuery
     * @param sort
     * @return
     */
    List<T> findAll(BaseQuery baseQuery, Sort sort);

    /**
     * 使用唯一索引定位单个记录
     * @param id
     * @return
     */
    T findOne(ID id);

    /**
     * 使用查询条件，定位单个记录
     * @param query
     * @return
     */
    T findOne(BaseQuery query);

    /**
     * 根据条件去更新
     * T为指定表对应的实体类
     * baseQuery 为满足条件的数据
     * updateFileds 需要更新的字段
     * @param t
     * @param baseQuery
     * @param updateFileds
     * @return
     */
    int update(T t, BaseQuery baseQuery, String... updateFileds);

    /**
     * 根据唯一主键去更新
     * @param t T为指定表对应的实体类
     * @param id 为这一列的主键ID
     * @param updateFileds 需要更新的字段
     * @return
     */
    int updateById(T t, ID id, String... updateFileds);
}
