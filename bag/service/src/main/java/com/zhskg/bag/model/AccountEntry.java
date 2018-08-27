package com.zhskg.bag.model;

import java.io.Serializable;

/**
 * Created by xiangshiquan on 2017/9/22.
 */
public class AccountEntry implements Serializable {
    private Long accountId;
    private String account;
    private String name;
    private String password;
    private String mobileNumber;
    private String email;
    private String identityCardNo;
    private Integer superFlag = 0;
    private Integer adminFlag = 0;
    private Integer enableFlag = 1;
    private Integer deleteFlag = 0;
    private Integer nextChangeFlag = 0;
    private String comment;
    private AccountRoleEntry[] roles;
    private Long createOn;
    private Long createTime;
    private Long modifyOn;
    private Long modifyTime;
    /** 登录设备唯一标识符 */
    private String registrationID;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    public Integer getSuperFlag() {
        return superFlag;
    }

    public void setSuperFlag(Integer superFlag) {
        this.superFlag = superFlag;
    }

    public Integer getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(Integer adminFlag) {
        this.adminFlag = adminFlag;
    }

    public Integer getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Integer enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getNextChangeFlag() {
        return nextChangeFlag;
    }

    public void setNextChangeFlag(Integer nextChangeFlag) {
        this.nextChangeFlag = nextChangeFlag;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AccountRoleEntry[] getRoles() {
        return roles;
    }

    public void setRoles(AccountRoleEntry[] roles) {
        this.roles = roles;
    }

    public Long getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Long createOn) {
        this.createOn = createOn;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Long modifyOn) {
        this.modifyOn = modifyOn;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }
    
	public String getRegistrationID() {
		return registrationID;
	}
	
	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}
}
