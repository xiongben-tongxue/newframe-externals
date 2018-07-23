package com.newframe.utils.query;

import com.newframe.utils.log.GwsLogger;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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

    /**
     * 重写新的保存的方法，自动更新时间戳
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> S save(S entity) {

        Integer systemTime = Math.toIntExact(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        try {
            Object ctime = PropertyUtils.getProperty(entity,"ctime");
            if (ctime == null){
                PropertyUtils.setProperty(entity,"ctime",systemTime);
            }
            Object utime = PropertyUtils.getProperty(entity,"utime");
            PropertyUtils.setProperty(entity,"utime",systemTime);
        } catch (Exception e) {
            GwsLogger.error("save error:"+e);
        }
        return super.save(entity);
    }
}
