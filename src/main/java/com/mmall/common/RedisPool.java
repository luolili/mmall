package com.mmall.common;

import com.mmall.util.PropertyUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
    //在tomcat启动的时候就要加载出来
    private static JedisPool pool;//jedis连接池

    private static Integer maxTotal = Integer.parseInt(PropertyUtil.getProperty("redis.max.total", "20"));//最大连接数
    private static Integer maxIdle = Integer.parseInt(PropertyUtil.getProperty("redis.max.idle", "10"));//最大 空闲
    private static Integer minIdle = Integer.parseInt(PropertyUtil.getProperty("redis.min.idle", "2"));//最小 空闲
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertyUtil.getProperty("redis.test.borrow", "true"));// true表示获得的jedis实例是可用的
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertyUtil.getProperty("redis.test.return", "true"));// true表示还的jedis实例是可用的


    private static String ip = PropertyUtil.getProperty("redis.ip");
    //port
    private static Integer port = Integer.parseInt(PropertyUtil.getProperty("redis.port"));

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setBlockWhenExhausted(true);//false:抛出ex,true:阻塞直至超时
        pool = new JedisPool(config, ip, port, 2 * 1000);
    }

    static {
        initPool();
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }

    public static void returnBrokenResource(Jedis jedis) {
        if (jedis != null) {
            pool.returnBrokenResource(jedis);
        }
    }

    public static void main(String[] args) {
        Jedis jedis = getJedis();
        jedis.set("name", "hu");
        returnResource(jedis);
        pool.destroy();//临时call
        System.out.println("--end");//启动后，在redis cli get name
        System.out.println();


    }
}
