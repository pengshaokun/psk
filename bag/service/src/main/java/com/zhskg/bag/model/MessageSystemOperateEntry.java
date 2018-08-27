package com.zhskg.bag.model;

import java.io.Serializable;
import java.util.Date;

public class MessageSystemOperateEntry implements Serializable {
    /**
     * ID
     */
    private Integer id;

    /**
     * 系统通知ID
     */
    private Integer SystyemMessageId;

    /**
     * 操作人
     */
    private Long operateId;

    /**
     * 操作时间
     */
    private Date operateDate;

    /**
     * 操作状态(0: 系统消息发送失败; 1: 系统消息发送成功)
     */
    private Integer operateStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSystyemMessageId() {
        return SystyemMessageId;
    }

    public void setSystyemMessageId(Integer systyemMessageId) {
        SystyemMessageId = systyemMessageId;
    }

    public Long getOperateId() {
        return operateId;
    }

    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public Integer getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(Integer operateStatus) {
        this.operateStatus = operateStatus;
    }
}
