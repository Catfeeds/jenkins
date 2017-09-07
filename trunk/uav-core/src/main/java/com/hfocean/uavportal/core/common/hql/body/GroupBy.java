package com.hfocean.uavportal.core.common.hql.body;

/**
 * @author Leslie.Lam
 * @create 2017-07-14 17:56
 **/
public interface GroupBy extends Having{

    Having groupBy(String field);
}
