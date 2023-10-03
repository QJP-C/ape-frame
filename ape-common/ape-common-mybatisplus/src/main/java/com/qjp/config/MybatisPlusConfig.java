package com.qjp.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.qjp.handler.MyMetaObjectHandler;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.io.IOException;

//@Configuration
public class MybatisPlusConfig {
    /**
     * MyBatis 字段值自动填充
     * @return
     */
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        //加载数据源
        mybatisPlus.setDataSource(dataSource);
        //全局配置
        GlobalConfig globalConfig  = new GlobalConfig();
        //配置填充器
        globalConfig.setMetaObjectHandler(new MyMetaObjectHandler());
        mybatisPlus.setGlobalConfig(globalConfig);
        return mybatisPlus;
    }
}
