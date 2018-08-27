package com.zhskg.bag.netty.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lwb on 2018/5/15.
 * 报警，地理位置信息数据
 */
public class BagData
{
    private String id;

    private int tableId;//区分报警数据和地理位置信息数据

    private String clientId;
    //产家编码
    private String factory;

    private String ip;//发送端ip

    private int port;//端口号

    private String stockId;//设备id

    private BigDecimal lon;//经度

    private BigDecimal lat;//纬度

    private int alarmFlag;//报警标志  0 正常

    private int faultFlag;//故障标记  0 正常
    /**
     * 报警集合
     */
    private List<Integer> af;
    /**
     * 预警
     */
    private List<Integer> wa;

    private String data;

    /**
     * 故障
     */
    private List<Integer> ff;

    private long createTime;

    public BagData(TableEnum tableEnum)
    {
        this.tableId = tableEnum.getValue();
    }

    public String getId() {

        return clientId+createTime;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Integer> getAf() {
        return af;
    }

    public void setAf(List<Integer> af) {
        this.af = af;
    }

    public List<Integer> getWa() {
        return wa;
    }

    public void setWa(List<Integer> wa) {
        this.wa = wa;
    }

    public List<Integer> getFf() {
        return ff;
    }

    public void setFf(List<Integer> ff) {
        this.ff = ff;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getTableId() {
        return tableId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
