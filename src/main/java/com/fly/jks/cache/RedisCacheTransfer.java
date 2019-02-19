package com.fly.jks.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author liang
 * @date 2019/2/19 - 14:39
 */
public class RedisCacheTransfer {
    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);

    }
}
