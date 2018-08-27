package com.zhskg.bag.util;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticCilent 
{
	public static Settings settings = Settings.builder()
			.put("cluster.name", PropertiesUtil.ELASTIC_CLUSTERNAME)
			.put("client.transport.sniff",PropertiesUtil.ELASTIC_SNIFF)
			.put("client.transport.ping_timeout", "100s")
			.build();
	public static TransportClient client;

	public static TransportClient getClient()
	{
		try {
			if (client == null) {
				synchronized (ElasticCilent.class)
				{
					if(client==null){
						List<TransportAddress> addresses = new ArrayList<TransportAddress>();
						String[] array = PropertiesUtil.ELASTIC_NODES.split(";");
						for(String address:array)
						{
							addresses.add(new TransportAddress(InetAddress.getByName(address),PropertiesUtil.ELASTIC_PORT));
						}
						client = new PreBuiltTransportClient(settings)
								.addTransportAddresses(addresses.toArray(new TransportAddress[addresses.size()]));
					}
				}
				return client;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}
}
