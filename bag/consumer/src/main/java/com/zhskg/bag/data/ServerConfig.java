package com.zhskg.bag.data;

import java.io.Serializable;

/**
 * Created by lwb on 2018/5/28.
 */
public class ServerConfig implements Serializable
{
    private String serverIP;

    private int dataPort;

    private int heartPort;

    private String domain;

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public int getDataPort() {
        return dataPort;
    }

    public void setDataPort(int dataPort) {
        this.dataPort = dataPort;
    }

    public int getHeartPort() {
        return heartPort;
    }

    public void setHeartPort(int heartPort) {
        this.heartPort = heartPort;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
