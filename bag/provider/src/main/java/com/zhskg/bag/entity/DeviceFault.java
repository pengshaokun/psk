package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 设备故障表
 * Created by lwb on 2018/4/10.
 */
@Table
@Data
@Entity(name="tr_device_fault")
public class DeviceFault
{
    /**设备id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**用户id*/
    @Column(name="user_id")
    private long userId;

    @Column(name="product_name")
    private String productName;

    /**设备编码*/
    @Column(name="client_id")
    private String clientId;

    /**故障原因*/
    private String reason;

    /**0是未处理，1是已处理*/
    private int status;
    /** 处理人用户Id */
    @Column(name="dispose_user_id")
    private Long disposeUserId;

    /**申报人联系电话*/
    private String tel;

    /**申报时间*/
    @Column(name="create_time")
    private long createTime;

    /**备注*/
    private String remark;

    /**上传的图片路径*/
    private String img;

    /**申报人姓名*/
    @Column(name="create_name")
    private String createName;


}
