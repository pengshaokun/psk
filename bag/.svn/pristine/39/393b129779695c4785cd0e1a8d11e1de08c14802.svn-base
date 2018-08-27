package com.zhskg.bag.netty.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * 配置文件读取类
 * @author lwb
 *
 */
public final class ConfigUtil
{
    public static final String fileName = "conf.properties";
    private static PropertiesConfiguration config;

    static {
        try {
            config = new PropertiesConfiguration(fileName);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        config.setReloadingStrategy(new FileChangedReloadingStrategy());
    }

    public static final int Server_Port = config.getInt("server_port");

    public static final boolean Redis_Cluster=config.getBoolean("redis_cluster");
    public static final String Redis_URL=config.getString("redis_host");

    public static final int Redis_port=config.getInt("redis_port");

    public static final String REDIS_KEY="bag:data:";


    public static final String ELASTIC_CLUSTERNAME=config.getString("elastic_clustername");
    
    public static final Boolean ELASTIC_SNIFF=config.getBoolean("elastic_sniff",false);

    public static final String ELASTIC_NODES=config.getString("elastic_nodes");

    public static final int ELASTIC_PORT=config.getInt("elastic_post");
}

