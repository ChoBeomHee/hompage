package com.project.devlabshompage.config;

import com.project.devlabshompage.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 시큐리티 필터가 스프링 필터 체인에 등록이 됨.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // 시큐어 어노테이션 활성화, preAuthorize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 이것이 필터. 이것이 기본 필터 체인에 등록이 됨.
    @Bean // 해당 메서드의 리턴되는 오브젝트를 IoC에 등록
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() // 인증만 되면 들어갈 수 있는 주소
                .antMatchers("/core/**").access("hasRole('ROLE_MASTER') or hasRole('ROLE_CORE')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login") // login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
                .defaultSuccessUrl("/") // 로그인이 완료되면 갈 주소, 단, 자기가 가려는 곳이 로그인ㅇ ㅣ필요해서 그럴 경우는 그 사이트로 이동함
                .and()
                .oauth2Login()
                .loginPage("/loginForm")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
    // 구글 후처리. 1. 코드 받기(인증) 2. 엑세스 토큰 받기(권한) 3. 사용자 프로필 정보를 가져옴 4. 정보를 토대로 회원가입
    // 혹은 정보가 부족하면 추가 창이 필요
        // TIP 엑세스 토큰 + 사용자 프로필 바로 받음
    }

    // 만약 유저네임이 아니라 다르게 ex) ID 로 받으려면, usernameParameter("ID") 로 바꿔줘야함
}
