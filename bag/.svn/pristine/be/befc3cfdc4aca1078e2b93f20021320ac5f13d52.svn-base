package com.zhskg.bag.netty.util;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

public class RedisUtil
{
    //访问密码
    private static String AUTH = "";

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 20;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    private static JedisCluster jedisCluster = null;

    /**
     * 初始化Redis连接池
     */
    static 
    {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            if (ConfigUtil.Redis_Cluster){
                Set<HostAndPort> jedisClusterNodes = new HashSet<>();
                String[] ADDRESS = ConfigUtil.Redis_URL.split(";");
                for (String url : ADDRESS) {
                    jedisClusterNodes.add(new HostAndPort(url.split(":")[0], Integer.parseInt(url.split(":")[1])));
                }
                // 根据节点集创集群链接对象
                //JedisCluster jedisCluster = newJedisCluster(jedisClusterNodes);
                // 节点，超时时间，最多重定向次数，链接池
                jedisCluster = new JedisCluster(jedisClusterNodes, 2000, 10, config);
            }else {
                jedisPool = new JedisPool(config, ConfigUtil.Redis_URL, ConfigUtil.Redis_port, TIMEOUT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     * @return
     */
    public static JedisCommands getJedis() {
        try {
            if(ConfigUtil.Redis_Cluster)
            {
                if (jedisCluster != null) {
                    return jedisCluster;
                } else {
                    return null;
                }
            }
            else {
                if (jedisPool != null) {
                    Jedis resource = jedisPool.getResource();
                    return resource;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static String set(String key,String val)
	{
        JedisCommands jedis = getJedis();
		String result =jedis.set(key, val);
		return result;
	}
	public static String setex(String key, int seconds, String value)
	{
		//Jedis jedis = jedisPool.getResource();
        JedisCommands jedis = getJedis();
		String result =jedis.setex(key, seconds, value);
		return result;
	}
	
	public static String get(String key)
	{
		//Jedis jedis = jedisPool.getResource();
        JedisCommands jedis = getJedis();
		String result =jedis.get(key);
		return result;
	}
	
	public static Boolean exists(String key) 
	{
		//Jedis jedis = jedisPool.getResource();
        JedisCommands jedis = getJedis();
        try {
            return jedis.exists(key);
        }
        catch (Exception ex){
            return false;
        }
    }
	
	public static boolean delete(String key)
	{
		//Jedis jedis = jedisPool.getResource();
        JedisCommands jedis = getJedis();
        try {
            long rs=jedis.del(key);
            if (rs>0) {
				return true;
			}
            return false;
        }
        catch (Exception ex){
            return false;
        }
	}
}
