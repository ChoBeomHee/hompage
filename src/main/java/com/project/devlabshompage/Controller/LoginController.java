package com.project.devlabshompage.Controller;

import com.project.devlabshompage.Service.LoginService;
import com.project.devlabshompage.config.auth.PrincipalDetails;
import com.project.devlabshompage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/testlogin")
    public @ResponseBody String testlogin(Authentication authentication, @AuthenticationPrincipal UserDetails userDetails){
        System.out.println("테스트결과--------------");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println(principalDetails.getUser());
        System.out.println(userDetails.getUsername());
        return "세션 정보 확인";
    }
    @Autowired
    private LoginService loginService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
