package com.crc.redis.service;

import java.util.concurrent.TimeUnit;

/**
 * @Author CRC
 * @Data 2021/4/27
 */
public interface RedisService {

    Boolean existsKey(String redis);

    Boolean expireKey(String name, int i);

    void set(String key, String value);

    void setEx(String key, Object value, Long time, TimeUnit timeUnit);

    Object get(String key);

    Boolean lock(String key);

    void delKey(String key);
}
