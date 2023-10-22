package com.qjp.redis.util;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * guava本地缓存
 *
 * @param <K>
 * @param <V>
 */
@Component
@Slf4j
public class CacheUtil<K, V> {
    @Value("${guava.cache.switch}")
    public Boolean switchCache;
    //初始化本地缓存
    private Cache<String, String> localCache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .build();

    /**
     * 写缓存
     * @param idList id集合
     * @param cachePrefix 缓存前缀
     * @param clazz 缓存对象类型
     * @param function  从数据库中获取数据的方法
     * @return  缓存结果
     */
    public Map<K, V> getResult(List<K> idList, String cachePrefix,
                               Class<V> clazz, Function<List<K>, Map<K, V>> function) {
        // 参数校验 如果idList为空，直接返回空map
        if (CollectionUtils.isEmpty(idList)) {
            return Collections.emptyMap();
        }
        //声明一个结果类
        Map<K, V> resultMap = new HashMap<>(16);
        // 判断开关 如果不开启缓存，直接返回结果
        if (!switchCache) {
            resultMap = function.apply(idList);
            return resultMap;
        }
        //声明未被本地缓存的list
        List<K> noCacheIdList = new ArrayList<>();
        // 从缓存中获取
        for (K id : idList) {
            String cacheKey = cachePrefix + "_" + id;
            String content = localCache.getIfPresent(cacheKey);
            if (StringUtils.isNotBlank(content)) {
                //已缓存 将对象序列化放入结果集
                V v = JSON.parseObject(content, clazz);
                resultMap.put(id,v);
            } else {
                //未缓存 将id放入未缓存id集合
                noCacheIdList.add(id);
            }
        }
        if (CollectionUtils.isEmpty(noCacheIdList)){
            //全都已缓存 直接return
            return resultMap;
        }
        //去拿（查）数据
        Map<K, V> noCacheResultMap = function.apply(noCacheIdList);
        if (noCacheResultMap == null || noCacheResultMap.isEmpty()){
            return  resultMap;
        }
        //遍历未缓存的
        for (Map.Entry<K, V> entry : noCacheResultMap.entrySet()) {
            K id = entry.getKey();
            V result = entry.getValue();
            String cacheKey = cachePrefix + "_" + id;
            //写缓存
            localCache.put(cacheKey, JSON.toJSONString(result));
            //补充刚缓存的
            resultMap.put(id,result);
        }
        return resultMap;
    }
}
