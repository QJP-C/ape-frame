package com.qjp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公共字段类
 */
@Data
public class BaseEntity implements Serializable {

    @TableField(value = "create_by",fill = FieldFill.INSERT)
    private String createBy;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_by",fill = FieldFill.UPDATE)
    private String updateBy;

    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(value = "delete_flag",fill = FieldFill.INSERT)
    @TableLogic//逻辑删除字段  配合yml文件的配置
    private Integer deleteFlag;

    @TableField(value = "version",fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
