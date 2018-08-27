package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 消息类型
 */
@Table
@Data
@Entity(name="tm_message_type")
public class MessageType {
    /**
     * 消息类型ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Integer id;

    /**
     * 消息类型名称
     */
    @Column(name = "t_dictname")
    private String dictname;

    /**
     * 消息类型值
     */
    @Column(name = "t_dictvalue")
    private Integer dictvalue;

    /**
     * 消息类型描述
     */
    @Column(name = "t_description")
    private String description;
}
