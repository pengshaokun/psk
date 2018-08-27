package com.zhskg.bag.entity;

/**
 * 角色表
 * Created by xiangshiquan on 2017/10/11.
 */

import lombok.Data;

import javax.persistence.*;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
@Table
@Data
@Entity(name="ts_role")
public class Role {
    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    /**角色名称*/
    @Column(name="role_name")
    private String roleName;

    /**角色编码*/
    @Column(name="role_code")
    private String roleCode;

    /**启用标记*/
    @Column(name="enable_flag",nullable = false)
    private Integer enableFlag = 0;

    /**备注*/
    private String comment;

    /**删除标记*/
    @Column(name="delete_flag")
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


}