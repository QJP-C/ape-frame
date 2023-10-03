package com.qjp.user.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qjp.entity.BaseEntity;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2023-09-26 15:53:35
 */
@TableName("sys_user")
@Data
public class SysUser extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO) //自增
    private Long id;
    @ApiParam("名称")
    private String name;

    private Integer age;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private Integer version;


}

