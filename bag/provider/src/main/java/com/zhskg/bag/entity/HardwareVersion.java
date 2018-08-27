package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Auther:jean
 * @Date:2018/8/10
 * @Descripsion
 */

/**文件存储表*/
@Table
@Data
@Entity(name="ts_hardware_version")
public class HardwareVersion {

    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    /**硬件类别*/
    @Column(name="type")
    private Integer type;

    /**版本名称*/
    @Column(name="version_Name")
    private String versionName;

    /**版本号*/
    @Column(name="version_Number")
    private Integer versionNumber;

    /**描述*/
    @Column(name="describe")
    private String describe;

    /**存储位置*/
    @Column(name="url")
    private String url;

    /**创建时间*/
    @Column(name="create_Time")
    private Date createTime;

    /**更新时间*/
    @Column(name="update_Time")
    private Date updateTime;

    /**是否删除*/
    @Column(name="delete_Flag")
    private Integer deleteFlag = 0;

    /**操作人*/
    @Column(name="operator")
    private Integer operator;

    public HardwareVersion() {
    }

}
