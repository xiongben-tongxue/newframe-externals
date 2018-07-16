package com.newframe.utils.query;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * @author:wangdong
 * @description:
 */
@Transactional(propagation = Propagation.SUPPORTS)
@NoRepositoryBean
public class BaseRepositoryEx<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID>{

    private EntityManager entityManager;

    private JpaEntityInformation<T,?> jpaEntityInformation;

    public BaseRepositoryEx(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        entityManager = entityManager;
        jpaEntityInformation = entityInformation;
    }

    private Specification<T> getConditonByQuery(BaseQuery query) {
        return new QueryToSpecification(query);
    }
    /**
     * 封装自定义组合查询列表的方法
     * @param query
     * @return
     */
    @Override
    public List<T> findAll(BaseQuery query) {
        if(query.getSort()!=null){
            return findAll(getConditonByQuery(query), query.getSort());
        }
        else if(query.getPage()!=null){
            return findAll(getConditonByQuery(query),query.getPage()).getContent();

        }else{
            return findAll(getConditonByQuery(query));
        }
    }
}
