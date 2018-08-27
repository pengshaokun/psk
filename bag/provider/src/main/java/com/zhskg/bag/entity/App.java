package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by xiangshiquan on 2017/10/10.
 */
@Table
@Data
@Entity(name="ts_app")
public class App {

    /**应用id*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "app_id")
    private String appId;

    /**应用名称*/
    @Column(name = "app_name")
    private String appName;

    /**删除标记  1为删除，0正常*/
    @Column(name = "delete_flag",nullable = false)
    private Integer deleteFlag = 0;

    private String comment;

    /**创建人*/
    @Column(name = "create_on")
    private Long createOn;

    /**创建时间*/
    @Column(name = "create_time")
    private Long createTime;

    /**修改人*/
    @Column(name = "modify_on")
    private Long modifyOn;

    /**修改时间*/
    @Column(name = "modify_time")
    private Long modifyTime;



}