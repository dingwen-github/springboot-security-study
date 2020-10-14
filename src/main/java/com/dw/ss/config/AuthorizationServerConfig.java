package com.dw.ss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @program:
 * @description: Oauth2.0 认证服务器配置
 * @author: dingwen
 * @create: 2020/10/13 16:57
 **/
//配置
@Configuration
//Oauth2.0授权服务器
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//        @Qualifier("authenticationManagerBean")
        /*
         @Qualifier配合@Autowired，说明由哪一个实现类注入的Bean
         */
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public TokenStore tokenStore(){
        //使用内存中的TokenStore
//        return new InMemoryTokenStore();
        return new JdbcTokenStore(dataSource);
        //使用JDBC TokenStore
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();//允许表单登录
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //密码模式
//        clients.jdbc(dataSource)
//                .withClient("client")
//                .secret(new BCryptPasswordEncoder().encode("123456"))
//                .authorizedGrantTypes("password", "refresh_token")//允许授权范围
//                .authorities("ROLE_ADMIN","ROLE_USER")//客户端可以使用的权限
//                .scopes( "read", "write")
//                .accessTokenValiditySeconds(7200)
//                .refreshTokenValiditySeconds(7200);

        //客户端模式
//        clients.jdbc(dataSource)
//                .withClient("client1")
//                .secret(new BCryptPasswordEncoder().encode("123456"))
//                .authorizedGrantTypes("client_credentials")
//                .scopes("read","write")
//                .authorities("client_credentials")
//                .accessTokenValiditySeconds(7200);

        //授权码模式
        clients.jdbc(dataSource)
                .withClient("client_code")
                .secret(new BCryptPasswordEncoder().encode("123456"))
                .authorizedGrantTypes("authorization_code", "refresh_token",
                        "password", "implicit")
                .scopes("all")
                .authorities("ROLE_ADMIN")
                .redirectUris("http://ww.baidu.com")
                .accessTokenValiditySeconds(1200)
                .refreshTokenValiditySeconds(50000);



    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);//必须设置 UserDetailsService 否则刷新token 时会报错

    }
}
