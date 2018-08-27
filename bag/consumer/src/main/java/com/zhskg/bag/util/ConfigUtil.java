package com.zhskg.bag.util;

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
    /*------------短信服务配置---------------*/
    public static final String SEND_MESSAGE_URL=config.getString("SEND_MESSAGE_URL");
    //#注册签名
    public static final String REGISTER_SIGN="智慧式控股";
    //注册模板
    public static final String REGISTER_TEMPLATE_ID="SMS_67670314";
    //有效时长
    public static final int REGISTER_DURATION=5;
    public static final String APP_KEY="23847756";
    public static final String APP_SECRET="43b99aa2627b6d5059c74955e3d804a7";


    public static final String REDIES_PROJECT_NAME="bag:app";
    /*支付服务配置*/
    public static final String WXMCHID = "1493980352";//共享车位 微信商户ID
    public static final String WXKEY = "f075020bf7f4ffdfd27eb6e960848956";
    public static final String WX_NOTIFY_URL=config.getString("WX_NOTIFY_URL");
    public static final String ServerIP= config.getString("wx_service_ip");//"182.242.225.214";
    
    public static final String redirectUrl=config.getString("redirectUrl");
    
    /*redis*/
    public static final String Redis_URL = config.getString("redis_host");
    public static final int Redis_port = config.getInt("redis_port");
    public static final boolean Redis_Cluster = config.getBoolean("redis_cluster");
    public static final int REDIS_Expire=config.getInt("Login_Express");

    public static String FAST_TRACKERS_IP = config.getString("FAST_TRACKERS_IP");
    public static String ABSOLUTE_DISK_PATH = config.getString("ABSOLUTE_DISK_PATH");
    public static String RELATIVE_DISK_PATH = config.getString("RELATIVE_DISK_PATH");
    public static Long TERM_VALIDITY = config.getLong("TERM_VALIDITY");
}

