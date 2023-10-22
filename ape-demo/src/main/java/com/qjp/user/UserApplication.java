package com.qjp.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.qjp.*.mapper") //扫描指定包下的所有使用@Repository修饰的mapper ，如果是@Mapper修饰就不用加这个
@ComponentScan(value = "com.qjp")       //扫描指定包下的所有Spring容器
@EnableCaching                  //缓存注解开启
public class UserApplication {

    public static void main(String[] args) {
        //异步日志   大幅提升日志输出效率
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(UserApplication.class);
    }
}
