package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by heshuang on 2017-11-15.
 * 反馈意见
 */
@Table
@Data
@Entity(name="tp_opinion")
public class Opinion {
    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opinion_id")
    private Integer opinionId;

    /**姓名*/
    @Column(name="full_name")
    private String fullName;

    /**手机号码*/
    private String mobile;

    /**反馈意见*/
    private String options;

    /**反馈时间*/
    @Column(name="create_time")
    private Long createTime;

    /**查阅状态(0待查看1已查阅)*/
    @Column(name="consult_status")
    private Integer consultStatus;

    /**查阅人*/
    @Column(name="consult_on")
    private Long consultOn;

    @Transient
    private String consultName;

    /**查阅时间*/
    @Column(name="consult_time")
    private Long consultTime;

    @Column(name="user_id")
    private long userId;


}
