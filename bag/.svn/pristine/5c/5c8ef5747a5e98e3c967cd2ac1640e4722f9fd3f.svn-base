package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

@Table
@Data
@Entity(name="tp_threshold")
public class Threshold {
    /**主键id,openId*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**设备id*/
    @Column(name = "client_id")
    private String clientId;

    /**电池电量*/
    @Column(name = "battery_power")
    private double batteryPower;

    /**电池电压*/
    @Column(name = "battery_voltage")
    private double batteryVoltage;

    /**电池温度*/
    @Column(name = "battery_temperature")
    private double batteryTemperature;

    /**电池电流*/
    @Column(name = "battery_current")
    private double batteryCurrent;

    /**防丢距离*/
    @Column(name = "safe_distance")
    private double safeDistance;

    /**跟踪距离*/
    @Column(name = "tracking_distance")
    private double trackingDistance;

    /**障碍距离*/
    @Column(name = "obstacle_distance")
    private double obstacleDistance;

    /**光线阈值*/
    @Column(name = "ray_threshold")
    private double rayThreshold;

    /**速度阀值*/
    @Column(name = "speed_threshold")
    private double speedThreshold;

    /**称重限值*/
    @Column(name = "weight_limit")
    private double weightLimit;

    /**电池充电次数*/
    @Column(name = "charge_num")
    private int chargeNum;

    /**创建时间*/
    @Column(name = "create_time")
    private long createTime;

    /**创建人*/
    @Column(name = "create_on")
    private long createOn;

    /**修改时间*/
    @Column(name = "modify_time")
    private long modifyTime;

    /**修改时间*/
    @Column(name = "modify_on")
    private long modifyOn;

    @Column(name = "set_flag")
    private int setFlag;//保存时为1,下发为0


}
