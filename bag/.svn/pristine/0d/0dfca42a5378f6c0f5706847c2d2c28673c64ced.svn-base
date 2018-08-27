package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
@Table( indexes = {@Index(columnList = "parent_id")})
@Data
@Entity(name="ts_area")
public class Area {

    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long areaId;

    /**编码*/
    @Column(name="area_code")
    private String areaCode;

    /**名称*/
    @Column(name="area_name")
    private String areaName;

    /**简称*/
    @Column(name="short_name")
    private String shortName;

    /**全称*/
    @Column(name="full_name")
    private String fullName;

    /**父节点*/
    @Column(name="parent_id",nullable = false)
    private Long parentId = 0L;

    /**路径*/
    private String path;

    /**层级*/
    @Column(nullable = false)
    private Integer level = 0;

    /**层级编码*/
    @Column(name="level_code")
    private String levelCode;

    /**排序号*/
    @Column(name="sort_no",nullable = false)
    private Integer sortNo = 0;

    /**备注*/
    private String comment;

    /**自定义标记*/
    @Column(name="custom_flag",nullable = false)
    private Integer customFlag = 0;

    /**删除标记*/
    @Column(name="delete_flag",nullable = false)
    private Integer deleteFlag = 0;

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


    @Transient
    private Integer total = 0;
    
    

}