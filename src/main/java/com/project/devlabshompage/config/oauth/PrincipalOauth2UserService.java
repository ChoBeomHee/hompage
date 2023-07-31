package com.project.devlabshompage.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

// 후처리 함수
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        return super.loadUser(userRequest);
        // 구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인 완료 -> code 리턴(oauth 클라이언트 라이브러리가 해줌) -> 엑세스토큰 요청
        // userReq 정보 -> 회원프로필 받기(loadUser) -> 구글로부터 회원프로필 받기

    }
    // 유저 네임 : google_sub
    // 패스워드 : 암호화 후 우리마음
    // 이메일 : 그대로
    // 이 정보들로 회원가입 진행
    // ROLE = USER
}
