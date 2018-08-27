package com.zhskg.bag.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther:jean
 * @Date:2018/8/9
 * @Descripsion
 */
public class HardwareVersionEntry implements Serializable {
    private Integer id;
    private Integer type;
    private String versionName;
    private Integer versionNumber;
    private String describe;
    private String url;
    private Date createTime;
    private Date updateTime;
    private Integer deleteFlag = 0;
    private Long operator;
    private Long createTimeStr;
    private Long updateTimeStr;


    public HardwareVersionEntry() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public Long getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(Long createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Long getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(Long updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }
}
