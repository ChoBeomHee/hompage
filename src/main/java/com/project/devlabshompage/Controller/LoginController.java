package com.project.devlabshompage.Controller;

import com.project.devlabshompage.Service.LoginService;
import com.project.devlabshompage.config.auth.PrincipalDetails;
import com.project.devlabshompage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/test/login")
    public @ResponseBody String loginTest(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetail){
        // DI 의존성 주입 어센티케이션 안에 프린시펄이 있고 다운캐스팅하여 겟유저를 호출
        // 어노테이션을이용할 수도, 어센틱을 가져올수도 있다.
        System.out.println("/test/login ==============================================");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal(); // 원래는 유저디테일이여야 하는데 다형성으로 프린시펄이 가능
        System.out.println("authentication" + principalDetails.getUser());
        System.out.println("userDetail" + principalDetail.getUser());
        return "세션 정보 확인";
    }
    @GetMapping("/test/oauth/login")
    public @ResponseBody String loginoauthTest(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth){
        System.out.println("/test/oauth/login ==============================================");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("auth" + oAuth2User.getAttributes());
        System.out.println("oAuth" + oAuth.getAttributes());
        return "google oauth 세션 정보 확인";
    }

    // 스프링 시큐리티는 자기만의 시큐리티 세션을 가지고 있다.
    // 이 영역안에 시큐리티가 관리하는 세션이 따로 있다.
    // 세션에 들어갈 수 있는 타입은 Authentication 객체이다.
    // 필요할때 마다 의존성주입을 할 수 있음. 들어갈수 있는 타입은 UserDetail, OAuth2User 이다.
    // 유저 타입 -> 일반적인 로그인
    // 오어쓰 타입 -> 오어쓰 로그인
    // 즉 , 두개의 로그인 타입을 쓰면 일관적으로 사용할 수가 없음
    // Solution : 클래스하나를 만들어서 유저디테일과 오어스2를 implementation 하면된다.
    @Autowired
    private LoginService loginService;
    @GetMapping("/loginForm")
    private String loginform(){
        return "loginform";
    }

    @GetMapping("/joinForm")
    private String joinForm(){
        return "joinform";
    }

    @PostMapping("/join")
    private String join(User user){
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        loginService.join(user);
        return "redirect:/loginForm"; // 리다이렉트를 붙이면 함수를 호출해준다.
    }

}
