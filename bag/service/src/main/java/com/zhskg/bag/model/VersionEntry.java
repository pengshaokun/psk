package com.zhskg.bag.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 版本实体
 */
public class VersionEntry implements Serializable {
    /**
     * 版本id
     */
    private Integer Id;

    /**
     * 版本名称
     */
    private String name;

    /**
     * 版本号
     */
    private String number;

    /**
     * 版本创建时间
     */
    private Date createtime;

    /**
     * 创建人
     */
    private Long createor;

    /**
     * bin文件路径
     */
    private String binPath;

    /**
     * 版本类型对象
     */
    private List<VersionTypeEntry> versionTypeList;

    /**
     * 版本说明
     */
    private String description;

    /**
     * 版本状态
     */
    private Integer status;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getCreateor() {
        return createor;
    }

    public void setCreateor(Long createor) {
        this.createor = createor;
    }

    public String getBinPath() {
        return binPath;
    }

    public void setBinPath(String binPath) {
        this.binPath = binPath;
    }

    public List<VersionTypeEntry> getVersionTypeList() {
        return versionTypeList;
    }

    public void setVersionTypeList(List<VersionTypeEntry> versionTypeList) {
        this.versionTypeList = versionTypeList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
