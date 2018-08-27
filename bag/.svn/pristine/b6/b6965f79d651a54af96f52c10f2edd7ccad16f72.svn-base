package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 消息类型
 */
@Table
@Data
@Entity(name="tm_message")
public class Message {
    /**
     * 消息id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    private Integer id; // 消息id

    /**
     * 消息类型
     */
    @Column(name = "m_type")
    private Integer type;

    /**
     * 消息标题
     */
    @Column(name = "m_title")
    private String title;

    /**
     * 消息内容
     */
    @Column(name = "m_content")
    @Lob
    private String content;

    /**
     * 消息创建时间
     */
    @Column(name = "m_createtime")
    private Date createtime;

    /**
     * 消息创建人
     */
    @Column(name = "m_createor")
    private Long createor;
}
