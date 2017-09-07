package com.hfocean.uavportal.core.dictionary.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfocean.common.redis.RedisService;
import com.hfocean.uavportal.core.common.hql.HqlHelper;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.dictionary.po.Dictionary;
import com.hfocean.uavportal.core.dictionary.service.DictionaryService;
import com.hfocean.uavportal.core.dictionary.util.DictionaryHelper;
import com.hfocean.uavportal.core.dictionary.vo.DictionaryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
* 
* @author Leslie.Lam
* @create 2017-08-09 16:28
**/
@Service
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary,Integer> implements DictionaryService {

    @Resource
    private RedisService redisService;

    private final static ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public List<DictionaryVo> selectAllWithChildren() {
        List<DictionaryVo> vos=null;
        List<Dictionary> dictionaries = findAll(Example.of(new Dictionary(-1)));//查询所有顶级数据字典
        if(Objects.nonNull(dictionaries)&& !dictionaries.isEmpty()){
            DictionaryVo vo;
            vos =new ArrayList<>();
            for (Dictionary dictionary:dictionaries ){
                vo=new DictionaryVo();
                BeanUtils.copyProperties(dictionary,vo);
                vo.setChilds(findChildren(vo.getName(),vo));
                vos.add(vo);
            }
        }
        return vos;
    }

    private List<DictionaryVo> findChildren(String name,DictionaryVo vo) {
        List<DictionaryVo> list=null;
        if (vo.getIsParent()==1){
            list = findAll(HqlHelper.from("DictionaryVo").and("name like ?",name+'%').and("parentCode = ?",vo.getCode()),DictionaryVo.class);
            if(Objects.nonNull(list)&& !list.isEmpty()){
                for (DictionaryVo d:list ){
                    d.setChilds(findChildren(name,d));
                }
            }
        }
        return list;
    }

    @Override
    public String getRemarkByNameAndCode(String name, Integer code) throws Exception {
        String remark=null;
        String json = redisService.get(DictionaryHelper.DIRECTORY + name);
        if(Objects.isNull(json)){
            Dictionary dictionary = findOne(Example.of(new Dictionary(name+((null==code||code==0)?"":"-"+code))));
            if(Objects.nonNull(dictionary)) remark=dictionary.getRemark();
        }else {
            DictionaryVo dictionaryVo = objectMapper.readValue(json, DictionaryVo.class);
            if(Objects.nonNull(dictionaryVo)){
                if(null==code||code==0||Objects.equals(code,dictionaryVo.getCode())){
                    remark=dictionaryVo.getRemark();
                }else {
                    remark=findChildRemark(dictionaryVo,code);
                }
            }
        }
        return remark;
    }

    private String findChildRemark(DictionaryVo dictionaryVo,Integer code){
        String remark=null;
        if(dictionaryVo.getIsParent()==1){
            List<DictionaryVo> childs = dictionaryVo.getChilds();
            if(Objects.nonNull(childs)&& !childs.isEmpty()){
                for (DictionaryVo vo:childs){
                    if(Objects.equals(code,vo.getCode())){
                        remark=vo.getRemark();
                        break;
                    }else {
                        remark=findChildRemark(vo,code);
                    }
                }
            }
        }
        return remark;
    }

    @Override
    public DictionaryVo selectChildrenByName(String name) throws Exception {
        DictionaryVo vo=null;
        String s = redisService.get(DictionaryHelper.DIRECTORY + name);
        if(Objects.isNull(s)){
            Dictionary dictionary = findOne(Example.of(new Dictionary(name)));
            if(Objects.nonNull(dictionary)){
                vo=new DictionaryVo();
                BeanUtils.copyProperties(dictionary,vo);
                vo.setChilds(findChildren(name,vo));
            }
        }else {
            vo=objectMapper.readValue(s,DictionaryVo.class);
        }
        return vo;
    }
}
