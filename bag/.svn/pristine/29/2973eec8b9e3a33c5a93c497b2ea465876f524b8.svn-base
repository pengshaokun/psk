package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
@Table
@Data
@Entity(name="ts_resource")
public class Resource {

    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private Long resourceId;

    /**编号*/
    @Column(name="resource_no")
    private String resourceNo;

    /**名称*/
    @Column(name="resource_name")
    private String resourceName;

    /***/
    @Column(name="parent_id",nullable = false)
    private Long parentId=0L;

    /**路径*/
    private String path;

    /**层级*/
    @Column(nullable = false)
    private Integer level = 0;

    /**排序号*/
    @Column(name="sort_no",nullable = false)
    private Long sortNo=0L;

    /**类别*/
    @Column(nullable = false)
    private Integer category=0;

    /**标题*/
    private String caption;

    /**资源地址*/
    private String url;

    /**请求方式*/
    private String method;

    /**描述*/
    private String description;

    /**参数*/
    private String json;

    /**备注*/
    private String comment;

    /***/
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