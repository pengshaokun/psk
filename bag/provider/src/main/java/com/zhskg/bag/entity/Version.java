package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 版本实体
 */
@Table
@Data
@Entity(name="tv_version")
public class Version {
    /**
     * 版本id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "v_id")
    private Integer Id;
    
    /**
     * 版本名称
     */
    @Column(name = "v_name")
    private String name;

    /**
     * 版本号
     */
    @Column(name = "v_number")
    private String number;

    /**
     * 版本创建时间
     */
    @Column(name = "v_createtime")
    private Date createtime;

    /**
     * 创建人
     */
    @Column(name = "v_createor")
    private Long createor;

    /**
     * bin文件路径
     */
    @Column(name = "v_bin_path")
    private String binPath;

    /**
     * 版本类型
     */
    @Column(name = "v_type")
    private Integer type;

    /**
     * 版本说明
     */
    @Column(name = "v_description")
    private String description;

    /**
     * 版本状态
     */
    @Column(name = "v_status")
    private Integer status;

    /**
     * 删除状态(0: 已删除;  1: 未删除)
     */
    @Column(name = "v_deleted")
    private Integer deleted;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
