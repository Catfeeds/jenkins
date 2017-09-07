package com.hfocean.uavportal.auth.base.plugins;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.hfocean.uavportal.auth.system.permission.annotation.MjConvertPermission;
import com.hfocean.uavportal.auth.system.permission.eumn.PermissionFieldTypeEumn;
import com.hfocean.uavportal.auth.system.permission.utils.PermissionHelper;

@Intercepts(value={@Signature(args = {Statement.class}, method = "handleResultSets", type = ResultSetHandler.class)})
public class HandleResultPlugin implements Interceptor{

	public Object intercept(Invocation invocation) throws Throwable {
		
		Object obj = invocation.proceed();
		
		if(obj instanceof List){
			List<?> list = (List<?>)obj;
			if(list.size()>0 && list.get(0)!=null && list.get(0) instanceof Object){
				for(Object o : list){
					Field[] fields = getAllFields(o);
					for(Field field:fields){
						field.setAccessible(true);
						MjConvertPermission permission = field.getAnnotation(MjConvertPermission.class);
						//权限转换
						doMjPermission(o, fields, field, permission);
					}
				}
			}
		}
		
		return obj;
		
	}
	
	
	private void doMjPermission(Object o, Field[] fields, Field field, MjConvertPermission permission)
			throws IllegalAccessException {
		if(permission!=null){
			PermissionFieldTypeEumn type = permission.type();
			String key = permission.key();
			Field ff = findField(fields,key);
			Object keyValue = ff.get(o);
			if(ff!=null && keyValue instanceof String){
				String value = PermissionHelper.find((String)keyValue, type);
				if(value!=null){
					field.set(o, value);
				}
			}
		}
	}
	
	private static Field findField(Field[] fields,String fieldName){
		for(Field field : fields){
			field.setAccessible(true);
			if(field.getName().equals(fieldName))return field;
		}
		return null;
	}
	
	private static Field[] getAllFields(Object obj){
		String objName = "Object";
		List<Field> list = new ArrayList<Field>();
		Class<?> clazz = obj.getClass();
		if(clazz.getSimpleName().equals(objName)){
			return new Field[0];
		}
		Collections.addAll(list, clazz.getDeclaredFields());
		clazz = clazz.getSuperclass();
		while(!(clazz.getSimpleName().equals(objName))){
			Collections.addAll(list, clazz.getDeclaredFields());
			clazz = clazz.getSuperclass();
		}
		return list.toArray(new Field[]{});
	}

	public Object plugin(Object target) {
		if (target instanceof ResultSetHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	public void setProperties(Properties properties) {
		
	}

}
