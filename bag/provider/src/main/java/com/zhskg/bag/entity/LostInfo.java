package com.zhskg.bag.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * 设备丢失信息表
 */
@Table
@Data
@Entity(name="tp_lost_info")
public class LostInfo {
    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**设备id*/
    @Column(name = "client_id")
    private String clientId;

    /**用户id*/
    @Column(name = "user_id")
    private long userId;

    /**经度*/
    private double lon;

    /**纬度*/
    private double lat;

    /**丢失时间*/
    @Column(name = "lost_time")
    private long lostTime;

    /**丢失说明*/
    @Column(name = "lost_content")
    private String lostContent;

    /**丢失标记*/
    @Column(name = "lost_flag")
    private int lostFlag;

    /**修改时间*/
    @Column(name = "modify_time")
    private long modifyTime;

    /**修改人*/
    @Column(name = "modify_on")
    private long modifyOn;

    /**丢失人名称*/
    @Column(name = "full_name")
    private String fullName;


}
