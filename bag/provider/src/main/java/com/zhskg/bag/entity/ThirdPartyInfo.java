package com.zhskg.bag.entity;

import com.zhskg.bag.enums.ThirdTerraceType;
import lombok.Data;

import javax.persistence.*;

/**
 * 第三方登录信息表
 * Created by fuhaibo on 2018/02/27.
 */
@Table
@Data
@Entity(name="ts_third_party_info")
public class ThirdPartyInfo {
    /**主键id,openId*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "open_id")
    private String openId;

    /**联合id*/
    @Column(name="union_id")
    private String unionId;

    /**访问令牌*/
    @Column(name="access_token")
    private String accessToken;

    /**过期时间*/
    @Column(name="expiration_date")
    private Long expirationDate;

    /**用户名*/
    private String name;

    /**城市*/
    private String city;

    /**省份*/
    @Column(name="")
    private String prvinice;

    /**国家*/
    private String country;

    /***/
    @Column(name="")
    private String gender;

    /**头像*/
    @Column(name="icon_url")
    private String iconUrl;

    /**用户id*/
    @Column(name="user_id")
    private Long userId;

    /**授权类别*/
    @Column(name="empower_type")
    private Integer empowerType = 0;

    /**创建人*/
    @Column(name="create_on")
    private Long createOn;

    /**创建人姓名*/
    @Column(name="create_name")
    private String createName;

    /**创建时间*/
    @Column(name="create_time")
    private Long createTime;

    /**删除标记*/
    @Column(name="delete_flag")
    private Integer deleteFlag = 0;

    /**平台*/
    @Enumerated(EnumType.STRING)
    @Column(name = "third_terrace_type")
    private ThirdTerraceType thirdTerraceType;
}