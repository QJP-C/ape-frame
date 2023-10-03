package com.qjp.redis.init;

import org.springframework.stereotype.Component;

@Component
public abstract class AbstractCache {
    /**
     * 初始化缓存
     */
    public void initCache(){}

    /**
     * 获取缓存
     * @param key
     * @return
     * @param <T>
     */
    public <T> T getCache(String key){
        return null;
    }

    /**
     * 清除缓存
     */
    public void clearCache(){}

    /**
     * 重新加载缓存
     */
    public void reloadCache(){
        clearCache();
        initCache();
    }
}
