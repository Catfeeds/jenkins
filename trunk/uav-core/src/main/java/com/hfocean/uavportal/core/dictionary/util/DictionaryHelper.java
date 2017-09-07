package com.hfocean.uavportal.core.dictionary.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfocean.common.redis.RedisService;
import com.hfocean.uavportal.core.dictionary.service.DictionaryService;
import com.hfocean.uavportal.core.dictionary.vo.DictionaryVo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
* 
* @author Leslie.Lam
* @create 2017-07-25 15:00
**/
@Component
public class DictionaryHelper {

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private RedisService redisService;


    public final static String DIRECTORY="_ref:dictionary:";

    private final static ObjectMapper objectMapper=new ObjectMapper();

    @PostConstruct
    public void init() throws Exception {
        //初始化数据字典
        List<DictionaryVo> vos = dictionaryService.selectAllWithChildren();
        if(Objects.nonNull(vos)&& !vos.isEmpty()){
            for (DictionaryVo vo:vos){
                if(null==redisService.get(DIRECTORY+vo.getName())){
                    redisService.set(DIRECTORY+vo.getName(), objectMapper.writeValueAsString(vo));
                }
            }
        }

    }

}
