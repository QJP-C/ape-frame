package com.qjp.user.controller;

import com.qjp.redis.util.RedisShareLockUtil;
import com.qjp.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private RedisUtil redisUtil;

    @Resource
    private RedisShareLockUtil redisShareLockUtil;

    @GetMapping("/test")
    public String test() {
        return "Hello World! 你好";
    }

    @GetMapping("testRedis")
    public void testRedis() {
        redisUtil.set("name", "疯狂烤翅");
        String name = redisUtil.get("name");
        System.out.println(name);
    }

    @GetMapping("testRedisLock")
    public void testRedisLock() {
        System.out.println(redisShareLockUtil.lock("jiji", "12424", 100000L));
    }

    @GetMapping("testLog")
    public void testLog() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            log.info("这是{}条日志", i);
        }
        long endTime = System.currentTimeMillis();
        log.info("当前打印日志耗时：{}",endTime-startTime);
    }
}
