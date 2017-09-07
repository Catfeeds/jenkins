package com.hfocean.uavportal.core.dictionary.service;

import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.dictionary.po.Dictionary;
import com.hfocean.uavportal.core.dictionary.vo.DictionaryVo;

import java.util.List;

/**
 * @author Leslie.Lam
 * @create 2017-08-09 16:28
 **/
public interface DictionaryService extends BaseService<Dictionary,Integer> {

    List<DictionaryVo> selectAllWithChildren();

    String getRemarkByNameAndCode(String name,Integer code) throws Exception;

    DictionaryVo selectChildrenByName(String name) throws Exception;
}
