package com.qjp.redis.util;

import com.qjp.redis.exception.ShareLockException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisShareLockUtil {
    @Resource
    private RedisUtil redisUtil;

    private final Long TIME_OUT = 1000L;  //自旋的超时时间 （加锁的等待时间）  10s

    /**
     * 加锁
     *  拿不到就等待10秒
     * @return
     */
    public boolean lock(String lockKey, String requestId, Long time) {
        //1、参数校验
        if (StringUtils.isBlank(lockKey) || StringUtils.isBlank(requestId) || time <= 0) {
            throw new ShareLockException("分布式锁-加锁-参数异常");
        }
        long currentTime = System.currentTimeMillis();
        long outTime = currentTime + TIME_OUT;
        Boolean result = false;
        //2、加锁可以自旋
        while (currentTime < outTime) {
            //3、借助redis的setnx来进行锁的设置
            result = redisUtil.setNx(lockKey, requestId, time, TimeUnit.MILLISECONDS);
            if (result) return result;
            try {//没抢到锁就休息一会再抢
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //重置一下当前时间
            currentTime = System.currentTimeMillis();
        }
        return result;
    }

    /**
     * 释放锁
     *
     * @return
     */
    public boolean unLock(String lockKey, String requestId) {
        if (StringUtils.isBlank(lockKey) || StringUtils.isBlank(requestId)) {
            throw new ShareLockException("分布式锁-解锁-参数异常");
        }
        boolean res = false;
        try {
            String value = redisUtil.get(lockKey);
            if (requestId.equals(value)) {//如果是一致的才能解锁，不一致相当于解别人的锁
                res = redisUtil.del(lockKey);
                return res;
            }
        } catch (Exception e) {
            //补日志
        }
        return res;
    }

    /**
     * 尝试加锁
     * 拿不到就快速失败，不等待
     * @return
     */
    public boolean tryLock(String lockKey, String requestId, Long time) {
        //1、参数校验
        if (StringUtils.isBlank(lockKey) || StringUtils.isBlank(requestId) || time <= 0) {
            throw new ShareLockException("分布式锁-尝试加锁-参数异常");
        }

        return redisUtil.setNx(lockKey, requestId, time, TimeUnit.MILLISECONDS);

    }
}
