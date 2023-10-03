package com.qjp.redis.init;

import com.qjp.redis.util.SpringContextUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitCache implements CommandLineRunner {
    /**
     * 项目启动时自动干的事
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            //我们要知道哪些缓存需要预热
            ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
            //获取抽象类的所有子类
            Map<String, AbstractCache> beanMap = applicationContext.getBeansOfType(AbstractCache.class);
            //调用init方法
            if (beanMap.isEmpty()) return;
            for (Map.Entry<String,AbstractCache> entry : beanMap.entrySet()){
                AbstractCache abstractCache = (AbstractCache) SpringContextUtil.getBean(entry.getValue().getClass());
                abstractCache.initCache();
            }
        }).start();

    }
}
