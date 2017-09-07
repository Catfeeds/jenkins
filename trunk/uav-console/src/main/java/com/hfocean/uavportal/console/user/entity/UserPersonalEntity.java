package com.hfocean.uavportal.console.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author guan.sj
 */
@Entity
@Table(name="tb_user_personal")
public class UserPersonalEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别(F男M女N未知)
     */
    private String sex;

    /**
     * 头像url
     */
    private String headPic;

    /**
     * 状态(1可用,2不可用)
     */
    private Integer status;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 身份证正面图
     */
    private String idcardPic1;

    /**
     * 身份证反面图
     */
    private String idcardPic2;
    
    /**
     * 身份证手持自拍图
     */
    private String idcardPic3;

    /**
     * 认证状态(1未认证,2待认证,3已认证,4认证失败)
     */
    private Integer authStatus;

    /**
     * 证书类型
     */
    private Integer certificateType;

    /**
     * 证书编号
     */
    private String certificateNumber;

    /**
     * QQ
     */
    private String qq;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 住址
     */
    private String address;

    /**
     * 紧急联系人姓名
     */
    private String emergencyContactName;

    /**
     * 紧急联系人手机号码
     */
    private String emergencyContactPhone;

    /**
     * 创建时间
     */
    private Date createTime;
    
    
    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 最后登录时间
     */
	private Date lastLoginTime;
	
	 /**
     * 最后登录ip
     */
	private String lastLoginIp; 
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.REFRESH)
	private PerWxEntity perWxEntity;

	public PerWxEntity getPerWxEntity() {
		return perWxEntity;
	}

	public void setPerWxEntity(PerWxEntity perWxEntity) {
		this.perWxEntity = perWxEntity;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getIdcardPic3() {
		return idcardPic3;
	}

	public void setIdcardPic3(String idcardPic3) {
		this.idcardPic3 = idcardPic3;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdcardPic1() {
        return idcardPic1;
    }

    public void setIdcardPic1(String idcardPic1) {
        this.idcardPic1 = idcardPic1;
    }

    public String getIdcardPic2() {
        return idcardPic2;
    }

    public void setIdcardPic2(String idcardPic2) {
        this.idcardPic2 = idcardPic2;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}