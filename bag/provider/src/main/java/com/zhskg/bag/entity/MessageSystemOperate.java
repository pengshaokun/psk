package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统通知操作实体
 */
@Table
@Data
@Entity(name="tm_message_system_operate")
public class MessageSystemOperate {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "so_id")
    private Integer id;

    /**
     * 系统通知ID
     */
    @Column(name = "so_messageid")
    private Integer SystyemMessageId;

    /**
     * 操作人
     */
    @Column(name = "so_operateid")
    private Long operateId;

    /**
     * 操作时间
     */
    @Column(name = "so_operatedate")
    private Date operateDate;

    /**
     * 操作状态(0: 系统消息发送失败; 1: 系统消息发送成功)
     */
    @Column(name = "so_operatestatus")
    private Integer operateStatus;
}
