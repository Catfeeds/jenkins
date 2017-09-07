package com.hfocean.uavportal.core.common.hql.body;

/**
 * @author Leslie.Lam
 * @create 2017-07-15 15:28
 **/
public interface From extends Join {

    Join from(String entity);

}
