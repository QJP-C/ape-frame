package com.qjp.user.service.impl;

import com.qjp.user.entity.dto.UserDto;
import com.qjp.user.entity.po.UserPo;
import com.qjp.user.mapper.UserMapper;
import com.qjp.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public int addUser(UserDto userDto) {
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(userDto,userPo);
        int count = userMapper.insert(userPo);
        return count;
    }
}
