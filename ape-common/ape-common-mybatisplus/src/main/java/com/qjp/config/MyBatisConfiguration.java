package com.qjp.config;

import com.qjp.inteceptor.SqlBeautyInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册配置
 */
@Configuration
public class MyBatisConfiguration {
    @Bean
    //给日志优化功能做一个开关            配置文件中的key           值为”true“才生效                       默认值 true 开启
    @ConditionalOnProperty(name = {"sql.beauty.show"},havingValue = "true",matchIfMissing = true)
    public SqlBeautyInterceptor sqlBeautyInterceptor(){
        return new SqlBeautyInterceptor();
    }

}
