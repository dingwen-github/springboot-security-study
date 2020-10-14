package com.dw.ss.mapper;

import com.dw.ss.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @program:
 * @description: User Mapper
 * @author: dingwen
 * @create: 2020/10/13 15:50
 **/
@Repository
public interface UserMapper {
    User findByUserName(String username);
}
