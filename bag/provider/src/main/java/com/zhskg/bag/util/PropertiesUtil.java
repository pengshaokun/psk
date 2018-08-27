package com.zhskg.bag.util;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 配置文件读取
 * @author lwb
 *
 */
public class PropertiesUtil 
{
	public static final String fileName = "conf.properties";
	private static PropertiesConfiguration config ;
	static
	{
		try {
			config = new PropertiesConfiguration(fileName);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static final String ELASTIC_CLUSTERNAME = config.getString("elastic_clustername");
	public static final String ELASTIC_NODES=config.getString("elastic_nodes");
	public static final Boolean ELASTIC_SNIFF=config.getBoolean("elastic_sniff",false);
	public static final int ELASTIC_PORT=Integer.parseInt(config.getString("elastic_post"));
}
