package com.qjp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时的字段填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("insert qjp");
        this.strictInsertFill(metaObject,"createBy",String.class,"qjp");
        this.strictInsertFill(metaObject,"createTime", Date.class,new Date());
        this.strictInsertFill(metaObject,"deleteFlag",Integer.class,0);//未删除
        this.strictInsertFill(metaObject,"version",Integer.class,0);   //乐观锁0版本
    }

    /**
     * 更新时的字段填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"updateBy",String.class,"qjp");
        this.strictUpdateFill(metaObject,"updateTime", Date.class,new Date());
    }
}
