package com.zhskg.bag.model;

import java.io.Serializable;
import java.util.Date;

public class MessageEntry implements Serializable {
    /**
     * 消息id
     */
    private Integer id; // 消息id

    /**
     * 消息类型
     */
    private MessageTypeEntry type;

    /**
     * 消息类型名称
     */
    private String typeName;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息创建时间
     */
    private Date createtime;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 消息创建人
     */
    private Long createor;
    
    /**
     * 消息创建人名称
     */
    private String createName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getCreateor() {
        return createor;
    }

    public void setCreateor(Long createor) {
        this.createor = createor;
    }

    public MessageTypeEntry getType() {
        return type;
    }

    public void setType(MessageTypeEntry type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}

