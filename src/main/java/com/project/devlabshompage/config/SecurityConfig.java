package com.project.devlabshompage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 시큐리티 필터가 스프링 필터 체인에 등록이 됨.
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 이것이 필터. 이것이 기본 필터 체인에 등록이 됨.
    @Bean // 해당 메서드의 리턴되는 오브젝트를 IoC에 등록
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/CoreMember/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm");
    }
}
