package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统通知
 */
@Table
@Data
@Entity(name="tm_message_system")
public class MessageSystem {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private Integer id;

    /**
     *  标题
     */
    @Column(name = "s_title")
    private String title;

    /**
     * 内容
     */
    @Column(name = "s_content")
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "s_createtime")
    private Date createtime;

    /**
     * 创建人ID
     */
    @Column(name = "s_userid")
    private Long userId;

    /**
     * 删除状态(0: 已经删除; 1: 未删除)
     */
    @Column(name = "s_deleted")
    private Integer deleted;
}
