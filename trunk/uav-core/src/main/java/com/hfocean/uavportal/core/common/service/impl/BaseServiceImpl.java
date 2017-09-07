package com.hfocean.uavportal.core.common.service.impl;

import com.hfocean.common.util.VerdictUtil;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.hql.body.HqlBody;
import com.hfocean.uavportal.core.common.repository.MyRepository;
import com.hfocean.uavportal.core.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现service通用方法
 *
 * @author Leslie.Lam
 * @create 2017-06-20 9:22
 **/
public class BaseServiceImpl<T,ID extends Serializable> implements BaseService<T,ID > {

    @Autowired
    private MyRepository<T,ID> myRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public Pager findByPage(HqlBody body, Pager pager) {
        String hql = body.getHql();
        List<Object> params = body.getParams();
        int fromIndex = hql.toLowerCase().indexOf("from");
        int orderIndex = hql.toLowerCase().indexOf("order");
        String hqlCount = "select count(*) " + hql.substring(fromIndex, orderIndex > 0 ? orderIndex : hql.length());
        int totalCount = count(hqlCount, params);
        if(totalCount > 0){
            int pageSize = pager.getPageSize();//页大小
            int totalPage = ((totalCount - 1) / pageSize) + 1;//总页数
            int pageNumber = pager.getPageNumber();//当前页数
            pageNumber=pageNumber>totalPage?totalPage:pageNumber;//当前页码不能大于总页数
            int start = (pageNumber-1)* pageSize;
            pager.setContent(getQueryAfterSetParam(params,entityManager.createQuery(hql)).setFirstResult(start).setMaxResults(pageSize).getResultList());
            pager.setTotalElements(totalCount);
            pager.setTotalPages(totalPage);
            pager.setPageNumber(pageNumber);
        }
        return pager;
    }

    @Override
    public <F> List<F> findAll(HqlBody body, Class<F> fClass) {
        return getQueryAfterSetParam(body.getParams(), entityManager.createQuery(body.getHql(),fClass)).getResultList();
    }

    private Query getQueryAfterSetParam(List<Object> params, Query query) {
        if(VerdictUtil.isValidCollection(params)){
            for (int i = 0; i < params.size(); i++) {
                if(params.get(i) instanceof Date){
                    query.setParameter(i,(Date)params.get(i), TemporalType.TIMESTAMP);
                }else {
                    query.setParameter(i, params.get(i));
                }
            }
        }
        return query;
    }

    @Override
    public Pager findByPage(String hql,Pager pager,List<Object> params) {
        VerdictUtil.assertNotBlank(hql);
        hql = process(hql);
        int fromIndex = hql.toLowerCase().indexOf("from");
        int orderIndex = hql.toLowerCase().indexOf("order");
        String hqlCount = "select count(*) " + hql.substring(fromIndex, orderIndex > 0 ? orderIndex : hql.length());
        int totalCount = count(hqlCount, params);
        if(totalCount > 0){
            int pageSize = pager.getPageSize();//页大小
            int totalPage = ((totalCount - 1) / pageSize) + 1;//总页数
            int pageNumber = pager.getPageNumber();//当前页数
            pageNumber=pageNumber>totalPage?totalPage:pageNumber;//当前页码不能大于总页数
            int start = (pageNumber-1)* pageSize;
            pager.setContent(getQueryAfterSetParam(params,entityManager.createQuery(hql)).setFirstResult(start).setMaxResults(pageSize).getResultList());
            pager.setTotalElements(totalCount);
            pager.setTotalPages(totalPage);
            pager.setPageNumber(pageNumber);
        }
        return pager;
    }

    @Override
    public List findAll(String hql,List<Object> params) {
        VerdictUtil.assertNotBlank(hql);
        hql = process(hql);
        return getQueryAfterSetParam(params,entityManager.createQuery(hql)).getResultList();
    }

    @Override
    public Integer count(final String hql,final List<Object> params) {
        VerdictUtil.assertNotBlank(hql);
        return ((Long)getQueryAfterSetParam(params,entityManager.createQuery(hql)).getSingleResult()).intValue();
    }

    @Override
    public Integer count(final String hql) {
        VerdictUtil.assertNotBlank(hql);
        return ((Long)entityManager.createQuery(hql).getSingleResult()).intValue();
    }

    /**
     *  处理不传where条件只传and的条件查询 (多条件查询时) where 1=1
     *  处理占位符？ 在？后面加数字
     * @param hql
     * @return
     */
    private String process(String hql) {
        if (hql.toLowerCase().indexOf("where") == -1){
            Matcher m = Pattern.compile("and").matcher(hql);
            if (m.find()){
                hql = m.replaceFirst("where");
            }else{
                m = Pattern.compile("AND").matcher(hql);
                if (m.find()){
                    hql = m.replaceFirst("WHERE");
                }
            }
        }
        Pattern pattern = Pattern.compile("\\?(?!\\d)");
        Matcher matcher = pattern.matcher(hql);
        int i=0;
        while (matcher.find()){
            hql=matcher.replaceFirst("?"+i++);
            matcher=pattern.matcher(hql);
        }
        return hql;
    }

    @Override
    public <S extends T> S save(S var1) {
        return myRepository.save(var1);
    }

    @Override
    public T findOne(ID var1) {
        return myRepository.findOne(var1);
    }

    @Override
    public boolean exists(ID var1) {
        return myRepository.exists(var1);
    }

    @Override
    public long count() {
        return myRepository.count();
    }

    @Override
    public void delete(ID var1) {
        myRepository.delete(var1);
    }

    @Override
    public void delete(T var1) {
        myRepository.delete(var1);
    }

    @Override
    public void delete(Iterable<? extends T> var1) {
        myRepository.delete(var1);
    }

    @Override
    public void deleteAll() {
        myRepository.deleteAll();
    }

    @Override
    public Page<T> findAll(Pageable var1) {
        return myRepository.findAll(var1);
    }

    @Override
    public <S extends T> S findOne(Example<S> var1) {
        return myRepository.findOne(var1);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> var1, Pageable var2) {
        return myRepository.findAll(var1,var2);
    }

    @Override
    public <S extends T> long count(Example<S> var1) {
        return myRepository.count(var1);
    }

    @Override
    public <S extends T> boolean exists(Example<S> var1) {
        return myRepository.exists(var1);
    }

    @Override
    public List<T> findAll() {
        return myRepository.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return myRepository.findAll(sort);
    }

    @Override
    public List<T> findAll(Iterable<ID> ids) {
        return myRepository.findAll(ids);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        return myRepository.save(entities);
    }

    @Override
    public void flush() {
        myRepository.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return myRepository.saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        myRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        myRepository.deleteAllInBatch();
    }

    @Override
    public T getOne(ID id) {
        return myRepository.getOne(id);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return myRepository.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return myRepository.findAll(example,sort);
    }

    @Override
    public T findOne(Specification<T> spec) {
        return myRepository.findOne(spec);
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return myRepository.findAll(spec);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return myRepository.findAll(spec,pageable);
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return myRepository.findAll(spec,sort);
    }

    @Override
    public long count(Specification<T> spec) {
        return myRepository.count(spec);
    }
}
