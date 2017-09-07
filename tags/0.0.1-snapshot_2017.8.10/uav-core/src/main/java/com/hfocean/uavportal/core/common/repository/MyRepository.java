package com.hfocean.uavportal.core.common.repository;/**
 * Created by msi- on 2017/6/19.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 共用基础Repository
 *
 * @author Leslie.Lam
 * @create 2017-06-19 15:18
 **/
@NoRepositoryBean
public interface MyRepository <T,ID extends Serializable> extends JpaRepository<T, ID >,JpaSpecificationExecutor<T> {
}
