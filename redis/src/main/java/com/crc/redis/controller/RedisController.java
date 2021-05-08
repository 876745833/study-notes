package com.crc.redis.controller;

import com.crc.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author CRC
 * @Date 2020/5/6
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/get/{key}")
    public Object getRedis(@PathVariable("key") String key) {
        return redisService.get(key);
    }

    @GetMapping("/set/{key}/{value}")
    public void setRedis(@PathVariable("key") String key, @PathVariable("value") String value) {
        redisService.set(key, value);
    }

    @GetMapping("/set/{key}/{value}/{time}")
    public void setRedis(@PathVariable("key") String key, @PathVariable("value") String value, @PathVariable("time") Long time) {
        redisService.setEx(key, value, time, TimeUnit.SECONDS);
    }

    @GetMapping("/lock/{key}")
    public boolean lock(@PathVariable("key") String key) {
        return redisService.lock(key);
    }

    @GetMapping("/unlock/{key}")
    public void unlock(@PathVariable("key") String key) {
        redisService.delKey(key);
    }
}
