package com.zhskg.bag.model;

import java.io.Serializable;

/**
 * 用户设备/箱包
 */
public class UserDeviceEntry implements Serializable
{
    private int id;
    /**
     * 箱包名称，用户有多个箱包，需要给每个箱包重新取个名称
     */
    private String deviceName;
    //设备编号
    private String clientId;

    private String productName;

    private String productCode;

    private String stockId;

    private int defaultFlag;//1是默认标记

    private long userId;//删除之后，userId设置为0

    private Integer customerId;//留着，以防后期用到

    private int authType;//0是未授权，1是授权箱包

    /**
     * 蓝牙编号
     */
    private String ipaddress;

    private int prot;

    private String mac;

    private String connPwd;//链接蓝牙的密码

    private long createTime;

    private String remark;

    private String nameplate;//铭牌

    private int lostFlag;

    public int getLostFlag() {
        return lostFlag;
    }

    public void setLostFlag(int lostFlag) {
        this.lostFlag = lostFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNameplate() {
        return nameplate;
    }

    public void setNameplate(String nameplate) {
        this.nameplate = nameplate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public int getAuthType() {
        return authType;
    }

    public void setAuthType(int authType) {
        this.authType = authType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(int defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public int getProt() {
        return prot;
    }

    public void setProt(int prot) {
        this.prot = prot;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getConnPwd() {
        return connPwd;
    }

    public void setConnPwd(String connPwd) {
        this.connPwd = connPwd;
    }
}
