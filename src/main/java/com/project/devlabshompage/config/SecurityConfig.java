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
                .antMatchers("/user/**").authenticated() // 인증만 되면 들어갈 수 있는 주소
                .antMatchers("/CoreMember/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login") // login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
                .defaultSuccessUrl("/"); // 로그인이 완료되면 갈 주소, 단, 자기가 가려는 곳이 로그인ㅇ ㅣ필요해서 그럴 경우는 그 사이트로 이동함
    }

    // 만약 유저네임이 아니라 다르게 ex) ID 로 받으려면, usernameParameter("ID") 로 바꿔줘야함
}
