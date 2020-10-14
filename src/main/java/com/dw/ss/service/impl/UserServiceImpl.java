package com.dw.ss.service.impl;

import com.dw.ss.mapper.UserMapper;
import com.dw.ss.pojo.User;
import com.dw.ss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program:
 * @description: UserService 实现类
 * @author: dingwen
 * @create: 2020/10/13 16:13
 **/
@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Autowired
    UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
