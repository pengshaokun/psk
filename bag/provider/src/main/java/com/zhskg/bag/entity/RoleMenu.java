package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
@Table(indexes = {@Index(columnList = "role_id,menu_id")})
@Data
@Entity(name="ts_role_menu")
public class RoleMenu {

    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Long roleId;

    @Transient
    private Long menuId;

    /**选中状态*/
    @Column(nullable = false)
    private Integer checked=0;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "role_id")
    private Role role;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "menu_id")
    private Menu menu;
}