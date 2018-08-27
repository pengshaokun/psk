package com.zhskg.bag.model;

import java.io.Serializable;

/**
 * Created by heshuang on 2017/11/15.
 */
public class OpinionEntry implements Serializable {
    private Integer opinionId;
    private String fullName;
    private String mobile;
    private String options;
    private Long createTime;
    private Integer consultStatus;
    private Long consultOn;
    private Long consultTime;
    private long userId;

    public Integer getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(Integer opinionId) {
        this.opinionId = opinionId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getConsultStatus() {
        return consultStatus;
    }

    public void setConsultStatus(Integer consultStatus) {
        this.consultStatus = consultStatus;
    }

    public Long getConsultOn() {
        return consultOn;
    }

    public void setConsultOn(Long consultOn) {
        this.consultOn = consultOn;
    }

    public Long getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(Long consultTime) {
        this.consultTime = consultTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
