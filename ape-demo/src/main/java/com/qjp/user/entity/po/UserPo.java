package com.qjp.user.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qjp.entity.BaseEntity;
import lombok.Data;

@TableName("user")
@Data
public class UserPo extends BaseEntity {
    @TableId(value = "id",type = IdType.AUTO) //自增
    private Long id;

    private String name;

    private Integer age;

}
