package com.project.devlabshompage.Controller;

import com.project.devlabshompage.Service.LoginService;
import com.project.devlabshompage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    private @ResponseBody String test(){
        return "시작페이지";
    }

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
