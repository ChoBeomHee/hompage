package com.project.devlabshompage.config.auth;

import com.project.devlabshompage.Repository.UserRepository;
import com.project.devlabshompage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcesingUrl 요청이 오면 자동으로 유저 디테일 서비스 타입으로 IoC 되어있는
// loadUserbyUsername 함수가 실행
@Service
public class PrincipalDetailService implements UserDetailsService {

    // 시큐리티 세션 = authentication = UserDetails
    @Autowired
    private UserRepository userRepository;
    // Authentication 어노테이션이 함수 종료시 만들어짐
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User userEntity = userRepository.findByUsername(username);
        System.out.println(userEntity.getRole());
        if(userEntity != null)
            return new PrincipalDetails(userEntity);

        System.out.println("로그인 완료");

        return null;
    }
}
