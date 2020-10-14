package com.dw.ss.service.impl;

import com.dw.ss.pojo.Permission;
import com.dw.ss.pojo.User;
import com.dw.ss.service.PermissionService;
import com.dw.ss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program:
 * @description: spring 提供了一个 UserDetailService 接口，通过它可以获取用户信息
 * @author: dingwen
 * @create: 2020/10/13 16:18
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserService userService;
    private PermissionService permissionService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsServiceImpl(UserService userService, PermissionService permissionService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.permissionService = permissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户
        User user = userService.findByUserName(username);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if (user != null) {
            System.out.println(user);
            //获取用户的授权
            List<Permission> permissionList = permissionService.findByUserId(user.getId().toString());
            //声明授权文件
            for (Permission permission : permissionList) {
                if (permission != null && permission.getName() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + permission.getName());
                    grantedAuthorityList.add(grantedAuthority);
                }
            }
            System.out.println(grantedAuthorityList);
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthorityList);
        }
        return null;
    }
}
