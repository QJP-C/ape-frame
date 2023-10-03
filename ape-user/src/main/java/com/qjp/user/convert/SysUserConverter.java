package com.qjp.user.convert;

import com.qjp.user.entity.po.SysUser;
import com.qjp.user.entity.req.SysUserReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 转换器
 * 指定属性拷贝（mapstruct）效率高于BeanUtils.copy
 */
@Mapper
public interface SysUserConverter {

    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

//    @Mapping(source = "name",target = "username") //属性名不一样时去指定映射的字段
    SysUser converReqToSysUser(SysUserReq sysUserReq);

}
