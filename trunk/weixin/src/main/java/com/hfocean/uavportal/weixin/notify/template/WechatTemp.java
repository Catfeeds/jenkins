package com.hfocean.uavportal.weixin.notify.template;

import java.util.List;

/**
* 
* @author Leslie.Lam
* @create 2017-06-29 15:57
**/
public class WechatTemp {

    private String first;

    private List<String> keywords;

    private String remark;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
