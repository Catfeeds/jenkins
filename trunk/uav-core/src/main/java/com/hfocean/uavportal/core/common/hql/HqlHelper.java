package com.hfocean.uavportal.core.common.hql;

import com.hfocean.uavportal.core.common.hql.impl.HqlMan;

/**
* 
* @author Leslie.Lam
* @create 2017-07-15 16:20
**/
public class HqlHelper {

    public static HqlMan from(String entity) {
        return new HqlMan(new StringBuffer(HqlMan.FROM).append(entity));
    }

    public static HqlMan select(String fields,String fromEntity) {
        return new HqlMan(new StringBuffer(HqlMan.SELECT).append(fields).append(HqlMan.FROM).append(fromEntity));
    }
}
