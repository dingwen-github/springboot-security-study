package com.dw.ss.service;

import com.dw.ss.pojo.User;

public interface UserService {
    User findByUserName(String username);
}
