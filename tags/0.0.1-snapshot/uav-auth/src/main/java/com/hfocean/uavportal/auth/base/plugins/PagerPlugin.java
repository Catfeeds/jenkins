package com.hfocean.uavportal.auth.base.plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.hfocean.uavportal.auth.base.Pager;


/**
 * 分页插件
 * 
 */
@Intercepts(value = {@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class })})
public class PagerPlugin implements Interceptor {

	public Object intercept(Invocation invocation) throws Throwable {
		Object target = invocation.getTarget();
		if(target instanceof StatementHandler){
			
			StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
			MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
			// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
			// 可以分离出最原始的的目标类)
			while (metaStatementHandler.hasGetter("h")) {
				Object object = metaStatementHandler.getValue("h");
				metaStatementHandler = SystemMetaObject.forObject(object);
			}
			// 分离最后一个代理对象的目标类
			while (metaStatementHandler.hasGetter("target")) {
				Object object = metaStatementHandler.getValue("target");
				metaStatementHandler = SystemMetaObject.forObject(object);
			}
			MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

			BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");

			Object requestParam = boundSql.getParameterObject();
			
			boolean isPagger = false;
			Pager<?> pager = null;
			
			if (requestParam instanceof Map) {
				Map<?, ?> map = (Map<?, ?>) requestParam;
				for (Entry<?, ?> entry : map.entrySet()) {
					Object value = entry.getValue();
					if (value instanceof Pager) {
						isPagger = true;
						pager = (Pager<?>) value;
						break;
					}
				}
			}
			
			if(isPagger && pager!=null){
				String sql = boundSql.getSql();
				Connection connection = (Connection) invocation.getArgs()[0];
				//分页sql
				String pagerSql = buildMySqlPageSql(sql,pager);
				//设置执行sql
				metaStatementHandler.setValue("delegate.boundSql.sql", pagerSql);
				//设置总行数
				pager.setTotalRows(totalRow(sql, connection, mappedStatement, boundSql));
				
				Object result = invocation.proceed();
				
				return result;
			}
			
		}

		
		return invocation.proceed();
	}
	
	
	private String buildMySqlPageSql(String sql,Pager<?> pager){
		sql = sql.trim();
		if (sql.endsWith(";"))
			sql = sql.substring(0, sql.length() - 1);
		StringBuffer sb = new StringBuffer(sql);
		sb.append(" limit ");
		sb.append(pager.getStartRow());
		sb.append(",");
		sb.append(pager.getPageSize());
		return sb.toString();
	}

	private void setParameters(PreparedStatement ps,
			MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws Exception {
		ParameterHandler parameterHandler = new DefaultParameterHandler(
				mappedStatement, parameterObject, boundSql);
		parameterHandler.setParameters(ps);
	}

	private Long totalRow(String sql, Connection connection,
			MappedStatement mappedStatement, BoundSql boundSql)
			throws Exception {
		sql = sql.trim();
		if (sql.endsWith(";"))
			sql = sql.substring(0, sql.length() - 1);
		// 记录总记录数
		String countSql = "select count(1) from (" + sql + ") tb_ ";
		PreparedStatement countStmt = null;
		ResultSet rs = null;

		try {
			countStmt = connection.prepareStatement(countSql);
			BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),
					countSql, boundSql.getParameterMappings(),
					boundSql.getParameterObject());
			setParameters(countStmt, mappedStatement, countBS,
					boundSql.getParameterObject());
			rs = countStmt.executeQuery();
			long totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getLong(1);
			}
			return totalCount;
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (countStmt != null)
				countStmt.close();
		}
	}

	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	public void setProperties(Properties properties) {

	}

}
