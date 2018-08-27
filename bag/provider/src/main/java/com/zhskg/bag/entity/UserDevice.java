package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户-箱包绑定表
 * Created by lwb on 2018/5/11.
 */
@Table
@Data
@Entity(name="tu_user_device")
public class UserDevice
{
    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * 箱包名称，用户有多个箱包，需要给每个箱包重新取个名称
     */
    @Column(name = "device_name")
    private String deviceName;
    //设备编号
    @Column(name = "client_id")
    private String clientId;
    //产品名称
    @Column(name = "product_name")
    private String productName;
    //产品编号
    @Column(name = "product_code")
    private String productCode;
    //库存id
    @Column(name = "stock_id")
    private String stockId;

    @Column(name = "default_flag")
    private int defaultFlag;//1是默认标记

    @Column(name = "user_id")
    private long userId;//删除之后，userId设置为0

    @Column(name = "customer_id")
    private Integer customerId;//留着，以防后期用到

    @Column(name = "auth_type")
    private int authType;//0是未授权，1是授权箱包

    /**
     * 蓝牙编号
     */
    @Column(name = "ip_address")
    private String ipaddress;

    private int port;
    //mac地址
    private String mac;

    @Column(name = "conn_pwd")
    private String connPwd;//链接蓝牙的密码
    //创建时间
    @Column(name = "create_time")
    private long createTime;
    //备注
    private String remark;
    //铭牌
    private String nameplate;


}
