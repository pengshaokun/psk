package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户表
 */
@Table
@Data
@Entity(name="tu_user")
public class User {
    /** 主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /** 用户id*/
    @Column(name="account_id")
    private Long accountId;

    /**姓名*/
    @Column(name="full_name")
    private String fullName;

    /**性别*/
    private Integer gender;

    /**创建人id*/
    @Column(name="create_on")
    private Long createOn;

    /**创建时间*/
    @Column(name="create_time")
    private Long createTime;

    /**修改人id*/
    @Column(name="modify_on")
    private Long modifyOn;

    /**修改时间*/
    @Column(name="modify_time")
    private Long modifyTime;

    /**删除标记*/
    @Column(name="delete_flag")
    private Integer deleteFlag = 0;

    /**手机号码*/
    @Column(name="mobile_number")
    private String mobileNumber;

    /**电子邮箱*/
    private String email;

    /**身份证号码*/
    @Column(name="identity_card_no")
    private String identityCardNo;

    /**生日*/
    @Column(name="birth_date")
    private Long birthDate;

    /**头像*/
    @Column(name="head_portrait")
    private String headPortrait;

    /**昵称*/
    @Column(name="nick_name")
    private String nickName;


}