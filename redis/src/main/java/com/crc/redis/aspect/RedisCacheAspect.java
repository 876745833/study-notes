package com.crc.redis.aspect;

import com.crc.redis.annotation.RedisCache;
import com.crc.redis.service.RedisService;
import com.crc.redis.utils.AspectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Redis业务层数据缓存
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
@Aspect
@Component
public class RedisCacheAspect {

    private static final String BIZ_CACHE_PREFIX = "biz_cache_";

    @Autowired
    private RedisService redisService;

    @Pointcut(value = "@annotation(com.crc.redis.annotation.RedisCache)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint point) throws Throwable {
        Method currentMethod = AspectUtil.INSTANCE.getMethod(point);
        //获取操作名称
        RedisCache cache = currentMethod.getAnnotation(RedisCache.class);
        boolean enable = cache.enable();
        if (!enable) {
            return point.proceed();
        }
        boolean flush = cache.flush();
        if (flush) {
            String classPrefix = AspectUtil.INSTANCE.getKey(point, BIZ_CACHE_PREFIX);
            log.info("清空缓存 - {}*", classPrefix);
            redisService.delKey(classPrefix);
            return point.proceed();
        }
        String key = AspectUtil.INSTANCE.getKey(point, cache.key(), BIZ_CACHE_PREFIX);
        boolean hasKey = redisService.existsKey(key);
        if (hasKey) {
            try {
                log.info("{}从缓存中获取数据", key);
                return redisService.get(key);
            } catch (Exception e) {
                log.error("从缓存中获取数据失败！", e);
            }
        }
        //先执行业务
        Object result = point.proceed();
        redisService.setEx(key, result, cache.expire(), cache.unit());
        log.info("{}从数据库中获取数据", key);
        return result;
    }
}
