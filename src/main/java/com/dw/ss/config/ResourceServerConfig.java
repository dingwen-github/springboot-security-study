package com.dw.ss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @program:
 * @description: Oauth2.0 资源服务器
 * @author: dingwen
 * @create: 2020/10/13 17:18
 **/

@Order(6)
@Configuration
//OAuth2 的资源服务器，此处主要指定了受资源服务器保护的资源链接
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //密码模式
//        http.csrf().disable()//禁用了 csrf 功能
//                .authorizeRequests()//限定签名成功的请求
//                .antMatchers("/decision/**","/govern/**").hasAnyRole("USER","ADMIN")
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/test/**").authenticated()//必须认证过后才可以访问
//                .anyRequest().permitAll()//其他没有限定的请求，允许随意访问
//                .and().anonymous();//对于没有配置权限的其他请求允许匿名访问

//        //客户端模式
//        http.csrf().disable()//禁用csrf功能
//                .authorizeRequests()//限定签名成功的请求
//                .antMatchers("/admin/**", "/test/**").authenticated()//签名成功后即可访问
//                .anyRequest().permitAll()//没有其他限定的请求允许访问
//                .and().anonymous()//没有配置其他权限的请求允许匿名访问
//                .and().formLogin()//使用Spring Security 默认登录页面
//                .and().httpBasic();//启用Http基础验证

        //授权码模式
        http.csrf().disable()//禁用了 csrf 功能
                .authorizeRequests()//限定签名成功的请求
                .antMatchers("/decision/**","/govern/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/test/**","/admin/**").authenticated()//签名成功后可访问，不受role限制
                .anyRequest().permitAll()//其他没有限定的请求，允许访问
                .and().anonymous()//对于没有配置权限的其他请求允许匿名访问
                .and().formLogin()//使用 spring security 默认登录页面
                .and().httpBasic();//启用http 基础验证

    }
}
