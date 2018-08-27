package com.zhskg.bag.param;

import java.io.Serializable;

/**
 * Created by xiangshiquan on 2017/10/10.
 */
public class AccountParam implements Serializable {
    private String keywords;
    private Long accountId;
    private String account;
    private String name;
    private String mobileNumber;
    private String email;
    private String identityCardNo;
    private Long roleId;
    private Integer adminFlag;
    private String registrationID;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

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

    public Long getRoleId() { return roleId; }

    public void setRoleId(Long roleId) { this.roleId = roleId; }

    public Integer getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(Integer adminFlag) {
        this.adminFlag = adminFlag;
    }

    public String getRegistrationID() {
        return registrationID;
    }
    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }
}
