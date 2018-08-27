package com.zhskg.bag.data;


import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;

import com.zhskg.bag.model.AccountRoleEntry;

public class AdminUserData implements Serializable {
    private Long userId;
    private Long accountId;
    @Size(max=16,message="姓名长度应小于16位")
    private String fullName;
    private Integer gender;
    private Long createOn;
    private Long createTime;
    private Long modifyOn;
    private Long modifyTime;
    private Integer deleteFlag=0;
    @Pattern(regexp="^[a-zA-Z]\\w{4,20}$",message="账号必须为4-20位字母,区分大小写")
    private String account;
    private String password;
    private String mobileNumber;
    @Email(message="邮箱格式不对或存在空格")
    private String email;
    private String identityCardNo;
    private Integer adminFlag=0;
    private Integer enableFlag=1;
    private Integer nextChangeFlag=0;
    private String comment;
    private AccountRoleEntry[] roles;
    private Long birthDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
		fullName = StringUtils.trimToNull(fullName);
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getAccount() {
        return StringUtils.trimToNull(account);
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return StringUtils.trimToNull(mobileNumber);
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return StringUtils.trimToNull(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentityCardNo() {
        return StringUtils.trimToNull(identityCardNo);
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
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

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }
}
