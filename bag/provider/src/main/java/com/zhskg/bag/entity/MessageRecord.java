package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 消息记录
 */
@Table
@Data
@Entity(name="tm_message_record")
public class MessageRecord {
    /**
     * 记录ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Integer id;

    /**
     * 消息
     */
    @Column(name = "r_message")
    private Integer message;

    /**
     * 推送时间
     */
    @Column(name = "r_time")
    private Date time;

    /**
     * 推送人
     */
    @Column(name = "r_user")
    private Long user;

    /**
     * 推送状态
     */
    @Column(name = "r_status")
    private Integer status;
}
