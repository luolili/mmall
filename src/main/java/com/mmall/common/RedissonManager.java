package com.mmall.common;

import com.mmall.util.PropertyUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class RedissonManager {

    private Config config = new Config();
    private Redisson redisson = null;

    private static String ip = PropertyUtil.getProperty("redis.ip");
    //port
    private static Integer port = Integer.parseInt(PropertyUtil.getProperty("redis.port"));

    private static String ip2 = PropertyUtil.getProperty("redis2.ip");
    //port
    private static Integer port2 = Integer.parseInt(PropertyUtil.getProperty("redis2.port"));

    @PostConstruct
    public void init() {

        try {
            config.useSentinelServers().addSentinelAddress(
                    new StringBuilder().append(ip).append(":").append(port).toString());
            redisson = (Redisson) Redisson.create(config);
            log.info("初始化redisson");
        } catch (Exception e) {
            log.info("初始化redisson err");
        }
    }

    public Redisson getRedisson() {
        return redisson;
    }
}
