package com.project.devlabshompage.config.auth;

// 시큐리티가 로그인 주소 요청이 오면 낚아채서 로그인을 진행
// 로그인을 진행이 완료되면 session을 만들어준다. (시큐리티 컨텍스트홀더)
// 시큐리티가 가지고 있는 세선을 들어갈 수 있는 오브젝트가 정해져있다.
// Authentication 객체여야한다.
// 이 안에는 user정보가 있어야 함.
// 유저 오브젝트타입 : 유저디테일 타입 객체
// 시큐리티 세션 => Authentication => UserDetails


import com.project.devlabshompage.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails { // 유저 디테일의 인터페이스를 구현

    private User user; // 콤포지션

    public PrincipalDetails(User user){
        this.user = user;
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
        return null;
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
}
