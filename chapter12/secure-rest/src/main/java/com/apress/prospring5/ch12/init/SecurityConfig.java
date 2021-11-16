package com.apress.prospring5.ch12.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Created by iuliana.cosmina on 8/16/16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    /**
     * configureGlobal(AuthenticationManagerBuilder 刨出）方法定义了身份验旺信息。 此处定义了个简单的身份验证提
     * 供程序 中使用硬编码的用户和密码（都设置为 remote）， 并分配了阻MOTE 角色 而在企业环境中，很可能通过
     * 数据库 LDAP 完成身份验证。
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        try {
            auth.inMemoryAuthentication()
                    .withUser("prospring5")
                    .password("prospring5")
                    .roles("REMOTE");
        } catch (Exception e) {
            logger.error("Could not configure authentication!", e);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                /**
                 * sessionCreationPolicy()方法用来允许是否在身份验证时创
                 * 会建会话，由于使用的 RESTful-WS是无状态的，因此将值设置为 SessionCreationPolicy.STATELESS ，指示 SpringSecurity
                 * 不要为所有请求创建 HTTP 会话，从而有助于提高应用的性能
                 */
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //不对任何请求鉴权
				.antMatchers("/**").permitAll()
                //在 antMatcbers（"/rest/**"）中， 设置只有分配了 REMOTE 角色的用户才能访 RESTful服务。
                .antMatchers("/rest/**").hasRole("REMOTE").anyRequest().authenticated()
                .and()
                /**
                 * formLogin()方法用来告诉 pring 生成基本登录表单，用于测试应用程序是否被正确保护。登录表单可通过
                 * localhost:8080/login 访问。
                 */
                .formLogin()
                .and()
                //httpBasic()方法指定 RESTfol 务仅支持 HTTP 基本身份验证。
                .httpBasic()
                .and()
                .csrf()
                .disable();
    }
}