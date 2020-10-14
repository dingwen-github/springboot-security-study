package com.dw.ss.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @program:
 * @description: Spring Security 配置类
 * @author: dingwen
 * @create: 2020/10/13 15:06
 **/
//表示这是一个配置类

@Order(2)
@Configuration
//开启Spring Security
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     *配置用户签名服务 主要是user-details 机制
     * @param [auth] 签名管理器构造器，用于构建用户具体权限控制
     * @return void
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /*
     *用来构建Filter链
     * @param [web]
     * @return void
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean(name = "authenticationManagerBean")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
     *用来配置拦截保护的请求
     * @param [http]
     * @return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Spring security
//        //禁用csrf功能 跨站请求伪造
//        http.csrf().disable()
//                //限定签名成功的请求
//                .authorizeRequests()
//                //对decision、govern下的接口需要USER或者ADMIN权限
//                .antMatchers("/decision/**", "govern/**").hasAnyRole("USER", "ADMIN")
//                //对admin/login不限定
//                .antMatchers("/admin/login").permitAll()
//                //admin下的接口需要ADMIN权限
//                .antMatchers("admin/**").hasRole("ADMIN")
//                //不拦截Oauth开放的资源
//                .antMatchers("/oauth/**").permitAll()
//                //没有其他限定的请求允许访问
//                .anyRequest().permitAll()
//                //对于没有配置权限的其他请求允许匿名访问
//                .and().anonymous()
//                //使用Spring Security 的默认登录页面
//                .and().formLogin()
//                //启用Http基础验证
//                .and().httpBasic();

//        Oauth2.0
//        不拦截 oauth 开放的资源
        http.csrf().disable();

        http.requestMatchers()//使HttpSecurity接收以"/login/","/oauth/"开头请求。
                .antMatchers("/oauth/**", "/login/**", "/logout/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .formLogin();
        ;

    }
}
