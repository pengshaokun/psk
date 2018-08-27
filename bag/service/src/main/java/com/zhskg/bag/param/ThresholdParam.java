package com.zhskg.bag.param;

import java.io.Serializable;

public class ThresholdParam implements Serializable{
    private int id;
    private String clientId;
    private int setFlag;

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

    public int getSetFlag() {
        return setFlag;
    }

    public void setSetFlag(int setFlag) {
        this.setFlag = setFlag;
    }
}
