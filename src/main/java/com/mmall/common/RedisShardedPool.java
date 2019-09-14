package com.mmall.common;

import com.google.common.collect.Lists;
import com.mmall.util.PropertyUtil;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.List;

public class RedisShardedPool {


    //在tomcat启动的时候就要加载出来
    private static ShardedJedisPool pool;// sharded jedis连接池

    private static Integer maxTotal = Integer.parseInt(PropertyUtil.getProperty("redis.max.total", "20"));//最大连接数
    private static Integer maxIdle = Integer.parseInt(PropertyUtil.getProperty("redis.max.idle", "10"));//最大 空闲
    private static Integer minIdle = Integer.parseInt(PropertyUtil.getProperty("redis.min.idle", "2"));//最小 空闲
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertyUtil.getProperty("redis.test.borrow", "true"));// true表示获得的jedis实例是可用的
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertyUtil.getProperty("redis.test.return", "true"));// true表示还的jedis实例是可用的

    private static String ip = PropertyUtil.getProperty("redis.ip");
    //port
    private static Integer port = Integer.parseInt(PropertyUtil.getProperty("redis.port"));

    private static String ip2 = PropertyUtil.getProperty("redis2.ip");
    //port
    private static Integer port2 = Integer.parseInt(PropertyUtil.getProperty("redis2.port"));

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setBlockWhenExhausted(true);//false:抛出ex,true:阻塞直至超时
        JedisShardInfo info1 = new JedisShardInfo(ip, port, 1000 * 2);
        JedisShardInfo info2 = new JedisShardInfo(ip2, port2, 1000 * 2);
        List<JedisShardInfo> infoList = Lists.newArrayList();
        infoList.add(info1);
        infoList.add(info2);
        pool = new ShardedJedisPool(config, infoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);

    }

    static {
        initPool();
    }

    public static ShardedJedis getJedis() {
        return pool.getResource();
    }

    public static void returnResource(ShardedJedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }

    public static void returnBrokenResource(ShardedJedis jedis) {
        if (jedis != null) {
            pool.returnBrokenResource(jedis);
        }
    }

    public static void main(String[] args) {
        ShardedJedis jedis = getJedis();
        jedis.set("name", "hu");
        returnResource(jedis);
        //pool.destroy();//临时call
        System.out.println("--end");//启动后，在redis cli get name
        System.out.println();


    }
}

