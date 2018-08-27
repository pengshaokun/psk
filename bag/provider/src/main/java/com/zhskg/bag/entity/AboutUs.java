package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;


/**
 * Created by fuhaibo on 2017/12/12.
 */
@Table
@Data
@Entity(name="ts_about_us")
public class AboutUs {
    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "about_id")
    private Integer aboutId;


    /** 联系电话*/
    @Column(name = "link_phone")
    private String linkPhone;

    /**网站地址*/
    @Column(name = "website_url")
    private String websiteUrl;
    /**安卓下载地址*/
    @Column(name = "android_download")
    private String androidDownload;

    /**ios下载地址*/
    @Column(name="ios_download")
    private String iosDownload;

    /**安卓版本*/
    @Column(name="android_version")
    private String androidVersion;

    /**ios版本*/
    @Column(name="ios_version")
    private String iosVersion;

    /**公司名称*/
    @Column(name="company_name")
    private String companyName;
    

}