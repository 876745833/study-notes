package com.crc.redis.service.impl;

import com.crc.redis.redis.RedisUtil;
import com.crc.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author CRC
 * @Data 2021/4/27
 */
@Service
@Slf4j
public class ReidsServiceImpl implements RedisService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Boolean existsKey(String key) {
        return redisUtil.hasKey(key);
    }

    @Override
    public Boolean expireKey(String key, int i) {
        return redisUtil.expire(key, i, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key,String value) {
        redisUtil.set(key,value);
    }

    @Override
    public void setEx(String key, Object value,Long time,TimeUnit timeUnit) {
        redisUtil.setEx(key,value,time,timeUnit);
    }

    @Override
    public Object get(String key) {
        return redisUtil.get(key);
    }

    @Override
    public Boolean lock(String key) {
        log.info("redis枷锁 ===> {}",key);
        return redisUtil.lock(key);
    }

    @Override
    public void delKey(String key) {
        log.info("redis删除 ===> {}",key);
        redisUtil.delete(key);
    }


}
