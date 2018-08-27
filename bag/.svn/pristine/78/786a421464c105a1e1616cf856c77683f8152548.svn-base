package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
@Table
@Data
@Entity(name="ts_account")
public class Account {

    /**用户id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    /** 账户*/
    private String account;

    /** 名称*/
    private String name;

    /**密码*/
    private String password;

    /**手机号码*/
    @Column(name="mobile_number")
    private String mobileNumber;

    /**邮箱*/
    private String email;

    /**身份证号码*/
    @Column(name="identity_card_no")
    private String identityCardNo;

    /**超级管理员标记  默认0 ，不是超级管理员*/
    @Column(name="super_flag",nullable = false)
    private Integer superFlag = 0;

    /**系统管理员标记 */
    @Column(name="admin_flag")
    private Integer adminFlag = 0;

    /**启用标记 1 启用 0未启用*/
    @Column(name="enable_flag",nullable = false)
    private Integer enableFlag = 1;

    /**删除标记 1删除，0正常*/
    @Column(name="delete_flag",nullable = false)
    private Integer deleteFlag = 0;

    /**下次登录必须修改密码  0未启用，1启用*/
    @Column(name="next_change_flag",nullable = false)
    private Integer nextChangeFlag = 0;
    
    private String comment;

    /**创建人*/
    @Column(name="create_on")
    private Long createOn;

    /**创建时间*/
    @Column(name="create_time")
    private Long createTime;

    /**修改人*/
    @Column(name="modify_on")
    private Long modifyOn;

    /**修改时间*/
    @Column(name="modify_time")
    private Long modifyTime;

    /** 登录设备唯一标识符 */
    @Column(name = "registration_id")
    private String registrationID;




}