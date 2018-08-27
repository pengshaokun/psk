package com.zhskg.bag.model;

import java.io.Serializable;
import java.util.Date;

public class MessageRecordEntry implements Serializable {
    /**
     * 记录ID
     */
    private Integer id;

    /**
     * 消息
     */
    private Integer message;

    /**
     * 推送时间
     */
    private Date time;

    /**
     * 推送人
     */
    private Long user;

    /**
     * 推送状态
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
