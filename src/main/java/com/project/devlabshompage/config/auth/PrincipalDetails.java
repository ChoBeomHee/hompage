package com.project.devlabshompage.config.auth;

// 시큐리티가 로그인 주소 요청이 오면 낚아채서 로그인을 진행
// 로그인을 진행이 완료되면 session을 만들어준다. (시큐리티 컨텍스트홀더)
// 시큐리티가 가지고 있는 세선을 들어갈 수 있는 오브젝트가 정해져있다.
// Authentication 객체여야한다.
// 이 안에는 user정보가 있어야 함.
// 유저 오브젝트타입 : 유저디테일 타입 객체
// 시큐리티 세션 => Authentication => UserDetails


import com.project.devlabshompage.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// 프린시펄 디테일을 만든 이유
// Authentication에는 UserDetail과 OAuth2User가 있는데 두가지 타입을 모두 받아내기 위함
// 회원가입은 User 타입으로받음
// 프린시펄디테일에서 impelmatation 하고 User Object를 품었음
// 그러므로 유저디테일 -> 프린시펄 디테일 그런데 로그인 타입에 따라 달라짐
// 오어스도 이제 유저 타입을 갖게됨
@Data
public class PrincipalDetails implements UserDetails, OAuth2User { // 유저 디테일의 인터페이스를 구현
    private User user; // 콤포지션
    private Map<String, Object> attributes;

    // 일반 로그인
    public PrincipalDetails(User user){
        this.user = user;
    }

    // 오어스 로그인
    public PrincipalDetails(User user, Map<String, Object> attributes){
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 해당 유저의 권한을 리턴 ex) 유저, 운영진 등
        Collection<GrantedAuthority> collection = new ArrayList<>(); // 어레이리스트는 컬렉션의 자식
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { // 휴면계정 전환
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
