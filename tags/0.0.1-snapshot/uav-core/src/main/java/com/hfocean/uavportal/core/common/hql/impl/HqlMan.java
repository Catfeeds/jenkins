package com.hfocean.uavportal.core.common.hql.impl;

import com.hfocean.uavportal.core.common.hql.body.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 为了保证hql拼接正确，须按以下顺序调用方法
 *      from--join--and--group by--having--order by
 * @author Leslie.Lam
 * @create 2017-07-15 15:18
**/
public class HqlMan implements Join {

    public final static String SELECT=" SELECT ";

    public final static String FROM=" FROM ";

    public final static String LEFTJOIN=" LEFT JOIN ";

    public final static String RIGHTJOIN=" RIGHT JOIN ";

    public final static String INNERJOIN=" INNER JOIN ";

    public final static String AND=" AND ";

    public final static String GROUPBY=" GROUP BY ";

    public final static String ORDERBY=" ORDER BY ";

    public final static String HAVING=" HAVING ";

    private StringBuffer hql;

    private List<Object> params;

    public HqlMan(StringBuffer hql) {
        this.hql = hql;
    }

    @Override
    public Join leftJoin(String entity) {
        hql=hql.append(LEFTJOIN).append(entity);
        return this;
    }

    @Override
    public Join rightJoin(String entity) {
        hql=hql.append(RIGHTJOIN).append(entity);
        return this;
    }

    @Override
    public Join innerJoin(String entity) {
        hql=hql.append(INNERJOIN).append(entity);
        return this;
    }

    @Override
    public Where and(String condition) {
        hql=hql.append(AND).append(condition);
        return this;
    }

    @Override
    public Where and(String condition, Object param) {
        params=null==params?new ArrayList<>():params;
        hql=hql.append(AND).append(condition);
        params.add(param);
        return this;
    }

    @Override
    public GroupBy groupBy(String field) {
        hql=hql.append(GROUPBY).append(field);
        return this;
    }

    @Override
    public OrderBy having(String field) {
        hql=hql.append(HAVING).append(field);
        return this;
    }

    @Override
    public HqlBody orderBy(String field) {
        hql=hql.append(ORDERBY).append(field);
        return this;
    }

    @Override
    public String getHql() {
        String fq = hql.toString();
        if(!fq.contains("FROM"))throw new RuntimeException("HQL must have 'FROM'");
        Matcher m = Pattern.compile("AND").matcher(fq);
        if(m.find())fq =m.replaceFirst("WHERE");
        Pattern pattern = Pattern.compile("\\?(?!\\d)");
        Matcher matcher = pattern.matcher(fq);
        int i=0;
        while (matcher.find()){
            fq=matcher.replaceFirst("?"+i++);
            matcher=pattern.matcher(fq);
        }
        return fq;
    }

    @Override
    public List<Object> getParams() {
        return params;
    }
}
