package com.zhskg.bag.param;

import java.io.Serializable;

/**
 * Created by lwb on 2018/5/19.
 */
public class BagDataParam implements Serializable
{
    private String clientId;

    private long startTime;

    private long endTime;

    private Integer alarmFlag;

    private Integer faultFlag;

    public int start = 0;

    public int end = 20;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Integer getAlarmFlag() {
        return alarmFlag;
    }

    public void setAlarmFlag(Integer alarmFlag) {
        this.alarmFlag = alarmFlag;
    }

    public Integer getFaultFlag() {
        return faultFlag;
    }

    public void setFaultFlag(Integer faultFlag) {
        this.faultFlag = faultFlag;
    }
}
