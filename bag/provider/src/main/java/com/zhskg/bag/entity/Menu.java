package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 菜单表
 */
@Table
@Data
@Entity(name="ts_menu")
public class Menu {
    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="menu_id")
    private Long menuId;

    /**编号*/
    @Column(name="menu_no")
    private String menuNo;

    /**名称*/
    @Column(name="menu_name")
    private String menuName;

    /**标题*/
    private String caption;

    /**图标*/
    @Column(name="icon_cls")
    private String iconCls;

    /**父节点*/
    @Column(name="parent_id",nullable = false)
    private Long parentId = 0L;

    /**路径*/
    private String path;

    /**层级*/
    @Column(nullable = false)
    private Integer level = 0;

    /**排序号*/
    @Column(name="sort_no")
    private Long sortNo = 0L;

    private String url;

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