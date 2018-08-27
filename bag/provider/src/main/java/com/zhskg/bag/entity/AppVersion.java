package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by fuhaibo on 2017/06/07.
 */

@Table
@Data
@Entity(name="ts_app_version")
public class AppVersion {

    /**版本id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="version_id")
    private Integer versionId;

    /**系统类别*/
    @Column(name="system_category")
    private Integer systemCategory;

    /**版本号*/
    @Column(name="version_number")
    private String versionNumber;

    /**更新时间*/
    @Column(name="update_time")
    private Long updateTime;

    /**下载地址*/
    @Column(name="download_url")
    private String downloadUrl;

    /**备注*/
    private String remark;

    /**创建时间*/
    @Column(name="create_on")
    private Long createOn;

    @Column(name="version_code")
    private Integer versionCode;

    /**家庭还是企业 1企业 2家庭*/
    @Column(name="customer_type")
    private Integer customerType;


}