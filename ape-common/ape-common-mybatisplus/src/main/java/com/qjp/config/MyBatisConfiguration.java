package com.qjp.config;

import com.qjp.inteceptor.SqlBeautyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册配置
 */
@Configuration
public class MyBatisConfiguration {
    @Bean
    public SqlBeautyInterceptor sqlBeautyInterceptor(){
        return new SqlBeautyInterceptor();
    }

}
