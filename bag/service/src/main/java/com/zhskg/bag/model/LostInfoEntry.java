package com.zhskg.bag.model;

import java.io.Serializable;

public class LostInfoEntry implements Serializable {
    private int id;
    private String clientId;//设备id
    private long userId;//用户id
    private double lon;
    private double lat;
    private long lostTime;
    private String lostContent;
    private int lostFlag;//丢失标识,0正常，1丢失
    private long modifyTime;
    private long modifyOn;//修改的用户id
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public long getLostTime() {
        return lostTime;
    }

    public void setLostTime(long lostTime) {
        this.lostTime = lostTime;
    }

    public String getLostContent() {
        return lostContent;
    }

    public void setLostContent(String lostContent) {
        this.lostContent = lostContent;
    }

    public int getLostFlag() {
        return lostFlag;
    }

    public void setLostFlag(int lostFlag) {
        this.lostFlag = lostFlag;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public long getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(long modifyOn) {
        this.modifyOn = modifyOn;
    }
}
