package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
@Table(indexes = {@Index(columnList = "role_id,resource_id")})
@Data
@Entity(name="ts_role_resource")
public class RoleResource {
    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Long roleId;

    @Transient
    private Long resourceId;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "role_id")
    private Role role;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "resource_id")
    private Resource resource;

}