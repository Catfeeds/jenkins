package com.hfocean.uavportal.core.common.hql.body;

/**
 * @author Leslie.Lam
 * @create 2017-07-14 17:47
 **/
public interface OrderBy extends HqlBody {

    HqlBody orderBy(String field);
}
