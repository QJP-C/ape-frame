package com.qjp.user.convert;

import com.qjp.user.entity.po.SysUser;
import com.qjp.user.entity.req.SysUserReq;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-23T22:43:47+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class SysUserConverterImpl implements SysUserConverter {

    @Override
    public SysUser converReqToSysUser(SysUserReq sysUserReq) {
        if ( sysUserReq == null ) {
            return null;
        }

        SysUser sysUser = new SysUser();

        sysUser.setCreateBy( sysUserReq.getCreateBy() );
        sysUser.setCreateTime( sysUserReq.getCreateTime() );
        sysUser.setUpdateBy( sysUserReq.getUpdateBy() );
        sysUser.setUpdateTime( sysUserReq.getUpdateTime() );
        sysUser.setDeleteFlag( sysUserReq.getDeleteFlag() );
        sysUser.setVersion( sysUserReq.getVersion() );

        return sysUser;
    }
}
