package com.dw.ss.service;

import com.dw.ss.pojo.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();
    List<Permission> findByUserId(String userId);
}
