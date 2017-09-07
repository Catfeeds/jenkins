package com.hfocean.uavportal.auth.fishsea.pojo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_sys_user_organization_ref")
public class UserOrgRef {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "org_id")
    private String orgId;

    private String creater;

    @Column(name = "create_time")
    private Date createTime;

    public UserOrgRef() {
    }

    public UserOrgRef(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
