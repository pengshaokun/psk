package com.zhskg.bag.data;

import java.io.Serializable;

/**
 * Created by lwb on 2018/5/8.
 */
public class LoginData implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 手机号码
     */
    private String mobileNumber;

    /**
     * 手机验证码
     */
    private String code;

    /**
     * 登录设备唯一标识符
     */
    private String registrationID;

    private String openId;

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getRegistrationID() {
        return registrationID;
    }
    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }
}
