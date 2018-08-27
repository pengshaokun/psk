package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户角色关联表
 * Created by xiangshiquan on 2017/10/11.
 */
@Table(indexes = {@Index(columnList = "account_id,role_id")})
@Data
@Entity(name="ts_account_role")
public class AccountRole {

    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**默认角色   1为默认角色*/
    @Column(name="default_flag")
    private Integer defaultFlag = 0;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "account_id")
    private Account account;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "role_id")
    private Role role;

    @Transient
    private Long accountId;

    @Transient
    private Long roleId;

    @Transient
    private String  roleName;

    @Transient
    private int enableFlag;

    @Transient
    private int default_flag;

    @Transient
    private String  comment;

    @Transient
    private boolean checked;
}