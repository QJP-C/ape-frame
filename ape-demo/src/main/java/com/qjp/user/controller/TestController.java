package com.qjp.user.controller;

import com.qjp.redis.util.CacheUtil;
import com.qjp.redis.util.RedisShareLockUtil;
import com.qjp.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private CacheUtil cacheUtil;
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

    @GetMapping("testLocalCache")
    public void testLocalCache() throws Exception{
        List<Long> skuIdList = new ArrayList<>();
        cacheUtil.getResult(skuIdList,"skuInfo.skuName",SkuInfo.class,(list)-> {
            Map<Long, SkuInfo> skuInfoMap = getSkuName(skuIdList);
            return skuInfoMap;
        });

        //准备需要缓存的对象id
        List<Long> skuPriceIdList = new ArrayList<>();
        cacheUtil.getResult(skuIdList,"skuInfo.skuPrice",SkuInfo.class,(list)-> {
            //从数据库中获取数据
            Map<Long, SkuPriceInfo> skuInfoMap = getSkuPriceName(skuPriceIdList);
            return skuInfoMap;
        });
    }

    /**
     * 模拟Sku获取数据的方法
     * @param skuIds
     * @return
     */
    public Map<Long,SkuInfo> getSkuName(List<Long> skuIds){
        return Collections.emptyMap();
    }

    /**
     * 模拟SkuPrice获取数据的方法
     * @param skuPriceIds
     * @return
     */
    public Map<Long,SkuPriceInfo> getSkuPriceName(List<Long> skuPriceIds){
        return Collections.emptyMap();
    }
    class SkuInfo{
        private Long id;
        private String name;
        private String price;
    }

    class SkuPriceInfo{
        private Long id;
        private String name;
        private String price;
    }
}
