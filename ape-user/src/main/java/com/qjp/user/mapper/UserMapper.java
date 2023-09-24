package com.qjp.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qjp.user.entity.po.UserPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@Repository
public interface UserMapper extends BaseMapper<UserPo> {
}
