package com.hfocean.uavportal.core.user.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author guan.sj
 */
@Entity
@Table(name="tb_user_wx")
public class UserWxPO  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;//微信open_id

    private String userId;
    
    private Integer type;

	public UserWxPO() {
	}

	public UserWxPO(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
    
}