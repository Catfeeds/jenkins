package com.hfocean.uavportal.core.dictionary.vo;

import javax.persistence.*;
import java.util.List;

/**
* 
* @author Leslie.Lam
* @create 2017-08-09 16:31
**/
@Entity
@Table(name = "tb_dictionary")
public class DictionaryVo {

    @Id
    private Integer id;

    private String name;

    private Integer code;

    @Column(name = "parent_code")
    private Integer parentCode;

    @Column(name = "is_parent")
    private Integer isParent;

    private String remark;

    @Transient
    private List<DictionaryVo> childs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getParentCode() {
        return parentCode;
    }

    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<DictionaryVo> getChilds() {
        return childs;
    }

    public void setChilds(List<DictionaryVo> childs) {
        this.childs = childs;
    }
}
