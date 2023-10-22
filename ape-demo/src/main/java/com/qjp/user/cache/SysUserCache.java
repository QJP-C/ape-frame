package com.qjp.user.cache;

import com.qjp.redis.init.AbstractCache;
import com.qjp.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 继承缓存抽象方法 编写该模块的缓存方法
 */
@Component
public class SysUserCache extends AbstractCache {

    private static final String SYS_USER_CACHE_KEY = "SYS_USER";

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 初始化缓存
     */
    @Override
    public void initCache() {
        //跟数据库做联动，跟其他数据来源做联动
        redisUtil.set("age","18");
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    @Override
    public <T> T getCache(String key) {
        if (!redisUtil.exist(key)){//如果不存在重新加载缓存
            reloadCache();
        }
        return (T) redisUtil.get(key);
    }

    /**
     * 清除缓存
     */
    @Override
    public void clearCache() {
        redisUtil.del(SYS_USER_CACHE_KEY);
    }

}
