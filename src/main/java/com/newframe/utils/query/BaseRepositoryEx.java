package com.newframe.utils.query;

import com.newframe.utils.log.GwsLogger;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author:wangdong
 * @description:这个类是专门给对Jpa进行增强
 */
@Transactional(propagation = Propagation.SUPPORTS)
@NoRepositoryBean
public class BaseRepositoryEx<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private EntityManager baseEm;
    private JpaEntityInformation<T, ?> baseEmInfo;

    @Autowired
    @PersistenceContext
    private EntityManager testEm;


    public BaseRepositoryEx(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        baseEm = entityManager;
        baseEmInfo = entityInformation;
    }

    private Specification<T> getConditonByQuery(BaseQuery query) {
        return new QueryToSpecification(query);
    }

    /**
     * 封装自定义组合查询列表的方法
     *
     * @param query
     * @return
     */
    @Override
    public List<T> findAll(BaseQuery query) {
        if (query.getSort() != null) {
            return findAll(getConditonByQuery(query), query.getSort());
        } else if (query.getPage() != null) {
            return findAll(getConditonByQuery(query), query.getPage()).getContent();

        } else {
            return findAll(getConditonByQuery(query));
        }
    }

    /**
     * 封装分页查询
     *
     * @param baseQuery
     * @param pageable
     * @return
     */
    @Override
    public Page<T> findAll(BaseQuery baseQuery, Pageable pageable) {

        return findAll(getConditonByQuery(baseQuery), pageable);
    }

    /**
     * 封装排序查询
     *
     * @param baseQuery
     * @param sort
     * @return
     */
    @Override
    public List<T> findAll(BaseQuery baseQuery, Sort sort) {

        return findAll(getConditonByQuery(baseQuery),sort);
    }

    /**
     * 使用查询条件定位单个记录
     *
     *
     * @param id@return
     */
    @Override
    public T findOne(ID id) {
        Optional<T> optional = findById(id);
        if (!optional.isPresent()){
            return null;
        }
        return optional.get();
    }

    /**
     * 使用查询条件，定位单个记录
     *
     * @param query
     * @return
     */
    @Override
    public T findOne(BaseQuery query) {
        Optional<T> optional = findOne(getConditonByQuery(query));

        if (!optional.isPresent()){
            return null;
        }
        return optional.get();
    }

    /**
     * 根据条件去更新
     * T为指定表对应的实体类
     * baseQuery 为满足条件的数据
     * updateFileds 需要更新的字段
     *
     * @param t
     * @param baseQuery
     * @param updateFileds
     * @return
     */
    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public int update(T t, BaseQuery baseQuery, String... updateFileds) {

        CriteriaBuilder criteriaBuilder = baseEm.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaUpdate<T> update =  (CriteriaUpdate<T>) criteriaBuilder.createCriteriaUpdate(t.getClass());
        Root<T> root = update.from((Class<T>) t.getClass());

        for(String fieldName:updateFileds){
            try {
                Object o = PropertyUtils.getProperty(t, fieldName);
                update.set(fieldName, o);
            } catch (Exception e) {
                GwsLogger.error("update error:"+e);
            }
        }
        //自动更新utime属性
        PropertyDescriptor[] pds= PropertyUtils.getPropertyDescriptors(t);
        List<PropertyDescriptor> pdsList = List.of(pds);
        List<PropertyDescriptor>  pdsUtime= pdsList.stream().filter(e->e.getName().equals("utime")).collect(Collectors.toList());
        if(pdsUtime.size()==1){
            Integer systemTime = Math.toIntExact(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
            update.set("utime",  systemTime);
        }

        update.where(BaseQueryPredicateBuilder.getPredicate2(root, criteriaBuilder,baseQuery));
        return baseEm.createQuery(update).executeUpdate();
    }

    /**
     * 根据唯一主键去更新
     *
     * @param t            T为指定表对应的实体类
     * @param id           为这一列的主键ID
     * @param updateFileds 需要更新的字段
     * @return
     */
    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public int updateById(T t, ID id, String... updateFileds) {

        CriteriaBuilder cb =baseEm.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaUpdate<T> update =  (CriteriaUpdate<T>) cb.createCriteriaUpdate(t.getClass());
        Root<T> root = update.from((Class<T>) t.getClass());
        for(String fieldName:updateFileds){
            try {
                Object o = PropertyUtils.getProperty(t, fieldName);
                update.set(fieldName, o);
            } catch (Exception e) {
                GwsLogger.error(e, "update error:");
            }
        }

        PropertyDescriptor[] pds= PropertyUtils.getPropertyDescriptors(t);
        List<PropertyDescriptor> pdsList = List.of(pds);
        List<PropertyDescriptor>  pdsUtime= pdsList.stream().filter(e->e.getName().equals("utime")).collect(Collectors.toList());
        if(pdsUtime.size()==1){
            Integer systemTime = Math.toIntExact(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
            update.set("utime",  systemTime);
        }


        //定位主键信息
        Iterable<String> idAttributeNames = baseEmInfo.getIdAttributeNames();

        for(String key:idAttributeNames){
            if(key!=null&&key!=""){
                update.where(cb.equal(root.get(key), id));
                break;
            }
        }
        return baseEm.createQuery(update).executeUpdate();
    }


    /**
     * 重写新的保存的方法，自动更新时间戳
     *
     * @param entity
     * @param <S>
     * @return
     */
    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public <S extends T> S save(S entity) {

        Integer systemTime = Math.toIntExact(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        try {
            Object ctime = PropertyUtils.getProperty(entity, "ctime");
            if (ctime == null) {
                PropertyUtils.setProperty(entity, "ctime", systemTime);
            }
            Object utime = PropertyUtils.getProperty(entity, "utime");
            PropertyUtils.setProperty(entity, "utime", systemTime);
        } catch (Exception e) {
            GwsLogger.error("save error:" + e);
        }
        return super.save(entity);
    }
}
