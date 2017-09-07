package com.hfocean.uavportal.core.common.hql.body;

/**
 * @author Leslie.Lam
 * @create 2017-07-15 15:08
 **/
public interface Where extends GroupBy{

    Where and(String condition);

    Where and(String condition, Object param);
}
