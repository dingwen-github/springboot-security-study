package com.dw.ss.mapper;

import com.dw.ss.pojo.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program:
 * @description: Permission Mapper
 * @author: dingwen
 * @create: 2020/10/13 16:04
 **/
@Repository
public interface PermissionMapper {
    List<Permission> findAll();
    List<Permission> findByUserId(String userId);
}
