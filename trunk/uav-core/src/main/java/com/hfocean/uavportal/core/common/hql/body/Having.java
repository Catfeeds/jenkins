package com.hfocean.uavportal.core.common.hql.body;

/**
 * @author Leslie.Lam
 * @create 2017-07-14 17:57
 **/
public interface Having extends OrderBy{

    OrderBy having(String field);
}
