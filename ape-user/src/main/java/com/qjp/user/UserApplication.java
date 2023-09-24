package com.qjp.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.qjp.*.mapper") //扫描指定包下的所有使用@Repository修饰的mapper ，如果是@Mapper修饰就不用加这个
@ComponentScan(value = "com.qjp")       //扫描指定包下的所有Spring容器
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
