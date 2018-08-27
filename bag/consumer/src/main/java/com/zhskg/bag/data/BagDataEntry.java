package com.zhskg.bag.data;

import java.math.BigDecimal;

public class BagDataEntry {
    private String id;

    private int tableId;

    private String deviceName;

    private String productName;

    private String clientId;
    //产家编码
    private String factory;

    private String ip;

    private int port;

    private String stockId;

    private BigDecimal lon;

    private BigDecimal lat;

    private int alarmFlag;

    private int faultFlag;

    private int elFlag;

    private int vaFlag;

    private int evFlag;

    private int teFlag;

    private double el;

    private double va;

    private double ev;

    private double te;

    private long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public int getAlarmFlag() {
        return alarmFlag;
    }

    public void setAlarmFlag(int alarmFlag) {
        this.alarmFlag = alarmFlag;
    }

    public int getFaultFlag() {
        return faultFlag;
    }

    public void setFaultFlag(int faultFlag) {
        this.faultFlag = faultFlag;
    }

    public int getElFlag() {
        return elFlag;
    }

    public void setElFlag(int elFlag) {
        this.elFlag = elFlag;
    }

    public int getVaFlag() {
        return vaFlag;
    }

    public void setVaFlag(int vaFlag) {
        this.vaFlag = vaFlag;
    }

    public int getEvFlag() {
        return evFlag;
    }

    public void setEvFlag(int evFlag) {
        this.evFlag = evFlag;
    }

    public int getTeFlag() {
        return teFlag;
    }

    public void setTeFlag(int teFlag) {
        this.teFlag = teFlag;
    }

    public double getEl() {
        return el;
    }

    public void setEl(double el) {
        this.el = el;
    }

    public double getVa() {
        return va;
    }

    public void setVa(double va) {
        this.va = va;
    }

    public double getEv() {
        return ev;
    }

    public void setEv(double ev) {
        this.ev = ev;
    }

    public double getTe() {
        return te;
    }

    public void setTe(double te) {
        this.te = te;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
