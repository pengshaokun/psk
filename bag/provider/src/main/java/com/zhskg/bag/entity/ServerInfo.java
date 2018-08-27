package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 服务器信息表
 */
@Table
@Data
@Entity(name="tp_server_info")
public class ServerInfo {
    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**域名*/
    @Column(name = "domain_name")
    private String domainName;

    /**ip地址*/
    @Column(name = "ip_address")
    private String ipAddress;

    /**数据端口*/
    @Column(name = "data_port")
    private int dataPort;

    /**心跳端口*/
    @Column(name = "heart_port")
    private int heartPort;

    /**心跳频率*/
    @Column(name = "heart_rate")
    private int heartRate;

    /**软件版本*/
    @Column(name = "software_version")
    private String softwareVersion;

    /**创建人id*/
    @Column(name = "create_on")
    private long createOn;

    /**创建人姓名*/
    @Column(name = "create_name")
    private String createName;

    /**创建人时间*/
    @Column(name = "create_time")
    private long createTime;

    /**修改人id*/
    @Column(name = "modify_on")
    private long modifyOn;

    /**修改人姓名*/
    @Column(name = "modify_name")
    private String modifyName;

    /**修改时间*/
    @Column(name = "modify_time")
    private long modifyTime;

}
