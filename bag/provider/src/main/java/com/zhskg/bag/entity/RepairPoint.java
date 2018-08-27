package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 设备故障维修点查询
 * Created by lwb on 2018/4/10.
 */
@Table
@Data
@Entity(name="tr_repairpoint")
public class RepairPoint
{
    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="area_code")
    private int areaCode;

    @Column(name="repair_name")
    private String repairName;//维修公司名称：或维修点名

    //地址
    private String address;

    //电话
    private String tel;

    //备注
    private String remark;

    /**创建时间*/
    @Column(name = "create_time")
    private long createTime;


}
