package com.qjp.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class UserTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void testSSet() {
        stringRedisTemplate.opsForValue().set("hello", "world");
        System.out.println(stringRedisTemplate.opsForValue().get("hello"));
    }
    @Test
    public void testSet() {
        redisTemplate.opsForValue().set("world", "hello");
        String world = String.valueOf(redisTemplate.opsForValue().get("world"));
        System.out.println(world);
    }
}
