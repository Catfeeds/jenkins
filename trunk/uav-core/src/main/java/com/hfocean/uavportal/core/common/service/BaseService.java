package com.hfocean.uavportal.core.common.service;/**
 * Created by msi- on 2017/6/20.
 */

import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.hql.body.HqlBody;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * 基础service接口，抽象通用方法
 *
 * @author Leslie.Lam
 * @create 2017-06-20 9:20
 **/
public interface BaseService<T,ID extends Serializable> {

     Integer count(final String hql);

     Integer count(final String hql,final List<Object> params);

     Pager findByPage(HqlBody body, Pager pager);

     <F> List<F> findAll(HqlBody body,Class<F> fClass);

     Pager findByPage(String hql,Pager pager,List<Object> params);

     List findAll(String hql,List<Object> params);

    //#####################################  CrudRepository  ####################################//

    /**
     * 保存单个实体
     */
    <S extends T> S save(S var1);

    /**
     * 根据id查找实体
     */
    T findOne(ID var1);

    /**
     * 根据id判断实体是否存在
     */
    boolean exists(ID var1);

    /**
     * 查询实体数量
     */
    long count();

    /**
     * 根据Id删除实体
     */
    void delete(ID var1);

    /**
     * 删除一个实体
     */
    void delete(T var1);

    /**
     * 删除一个实体的集合
     */
    void delete(Iterable<? extends T> var1);

    /**
     * 删除所有实体,不用或慎用
     */
    void deleteAll();

    //#####################################  PagingAndSortingRepository  ####################################//

    /**
     * 分页查询（含排序功能）
     */
    Page<T> findAll(Pageable var1);

    //#####################################  QueryByExampleExecutor  ####################################//

    /**
     * 排序
     */
    <S extends T> S findOne(Example<S> var1);

    <S extends T> Page<S> findAll(Example<S> var1, Pageable var2);

    <S extends T> long count(Example<S> var1);

    <S extends T> boolean exists(Example<S> var1);

    //#####################################  JpaRepository  ####################################//

    List<T> findAll();

    List<T> findAll(Sort sort);

    /**
     * 根据id集合查询对应实体集合
     */
    List<T> findAll(Iterable<ID> ids);

    /**
     * 保存集合
     */
    <S extends T> List<S> save(Iterable<S> entities);

    /**
     * Flushes all pending changes to the database.
     */
    void flush();

    /**
     * Saves an entity and flushes changes instantly.
     *
     * @param entity
     * @return the saved entity
     */
    <S extends T> S saveAndFlush(S entity);

    /**
     * Deletes the given entities in a batch which means it will create a single {@link Query}. Assume that we will clear
     * the {@link javax.persistence.EntityManager} after the call.
     *
     * @param entities
     */
    void deleteInBatch(Iterable<T> entities);

    /**
     * Deletes all entities in a batch call.
     */
    void deleteAllInBatch();

    /**
     * Returns a reference to the entity with the given identifier.
     *
     * @param id must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @see EntityManager#getReference(Class, Object)
     */
    T getOne(ID id);


    <S extends T> List<S> findAll(Example<S> example);


    <S extends T> List<S> findAll(Example<S> example, Sort sort);

    //#####################################  JpaSpecificationExecutor  ####################################//

    /**
     * Returns a single entity matching the given {@link Specification}.
     *
     * @param spec
     * @return
     */
    T findOne(Specification<T> spec);

    /**
     * Returns all entities matching the given {@link Specification}.
     *
     * @param spec
     * @return
     */
    List<T> findAll(Specification<T> spec);

    /**
     * Returns a {@link Page} of entities matching the given {@link Specification}.
     *
     * @param spec
     * @param pageable
     * @return
     */
    Page<T> findAll(Specification<T> spec, Pageable pageable);

    /**
     * Returns all entities matching the given {@link Specification} and {@link Sort}.
     *
     * @param spec
     * @param sort
     * @return
     */
    List<T> findAll(Specification<T> spec, Sort sort);

    /**
     * Returns the number of instances that the given {@link Specification} will return.
     *
     * @param spec the {@link Specification} to count instances for
     * @return the number of instances
     */
    long count(Specification<T> spec);
}
