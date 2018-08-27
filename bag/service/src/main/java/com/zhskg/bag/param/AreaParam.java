package com.zhskg.bag.param;

import java.io.Serializable;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public class AreaParam implements Serializable {
    private String keywords;
    
    private String areaCode;
    
    private String areaName;
    
    private String shortName;
    
    private String fullName;
    
    private Long parentId;

    private String comment;
    
    private Integer customFlag;
    
    private Integer deleteFlag;

    private Integer level;
    
    public String getKeywords() { return keywords; }
    public void setKeywords(String keywords) { this.keywords = keywords; }

    public String getAreaCode() { return areaCode; }
    public void setAreaCode(String areaCode) { this.areaCode = areaCode; }
    
    public String getAreaName() { return areaName; }
    public void setAreaName(String areaName) { this.areaName = areaName; }
    
    public String getShortName() { return shortName; }
    public void setShortName(String shortName) { this.shortName = shortName; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public Long getParentId() { return parentId; }
    public void setParentId(Long parentId) { this.parentId = parentId; }
    
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    
    public Integer getCustomFlag() { return customFlag; }
    public void setCustomFlag(Integer customFlag) { this.customFlag = customFlag; }
    
    public Integer getDeleteFlag() { return deleteFlag; }
    public void setDeleteFlag(Integer deleteFlag) { this.deleteFlag = deleteFlag; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }
}