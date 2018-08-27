package com.zhskg.bag.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

/**
 * Created by lwb on 2017/9/18.
 */
public class Password implements Serializable {
    private String oldPassword;
    @Pattern(regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$",message="密码必须为6-18位数字加字母")
    private String newPassword;
    private Long accountId;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
