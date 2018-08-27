package com.zhskg.bag.entity;

/**
 * Created by heshuang on 2017-10-23.
 * 产品手册实体类
 */
public class Instructions {
    private Integer instructionsId;
    private String fileName;
    private String downloadUrl;
    private Long createOn;
    private String createName;
    private Long createTime;
    private Long deleteOn;
    private String deleteName;
    private Long deleteTime;
    private Integer deleteFlag;


    public Integer getInstructionsId() {
        return instructionsId;
    }

    public void setInstructionsId(Integer instructionsId) {
        this.instructionsId = instructionsId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
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

    public Long getDeleteOn() {
        return deleteOn;
    }

    public void setDeleteOn(Long deleteOn) {
        this.deleteOn = deleteOn;
    }

    public Long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getDeleteName() {
        return deleteName;
    }

    public void setDeleteName(String deleteName) {
        this.deleteName = deleteName;
    }
}
