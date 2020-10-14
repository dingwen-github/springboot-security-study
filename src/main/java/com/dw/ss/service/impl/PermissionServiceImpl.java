package com.dw.ss.service.impl;

import com.dw.ss.mapper.PermissionMapper;
import com.dw.ss.pojo.Permission;
import com.dw.ss.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:
 * @description: PermissionService 实现类
 * @author: dingwen
 * @create: 2020/10/13 16:12
 **/
@Service
public class PermissionServiceImpl implements PermissionService {
    private PermissionMapper permissionMapper;

    @Autowired
    PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    @Override
    public List<Permission> findByUserId(String userId) {
        return permissionMapper.findByUserId(userId);
    }
}
