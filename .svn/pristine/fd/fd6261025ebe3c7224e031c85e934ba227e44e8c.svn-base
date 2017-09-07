package com.hfocean.uavportal.core.util;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;


/**
 * 自定义的实体ID生成器，基于MongoDb的ObjectId机制
 * @author Gene
 *
 */
public class ObjectIdHibernateGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SessionImplementor arg0, Object arg1)
			throws HibernateException {
		return new ObjectId().toHexString();
	}

}
