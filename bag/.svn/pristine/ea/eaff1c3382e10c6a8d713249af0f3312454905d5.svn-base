package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 常见问题表
 * Created by lwb on 2018/3/20.
 */
@Table
@Data
@Entity(name="tp_problems")
public class Problem
{
    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**标题*/
    private String title;

    /**内容*/
    private String contxt;

    /**类型*/
    @Column(name="cxt_type")
    private int cxtType;

    /**创建人名称*/
    @Column(name = "create_name")
    private String createName;

    /**创建人*/
    @Column(name = "create_time")
    private long createTime;

    /**创建人*/
    @Column(name = "create_on")
    private long createOn;


}
