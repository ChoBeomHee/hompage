package com.project.devlabshompage.Controller;

import com.project.devlabshompage.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class tempView {
    @GetMapping("/")
    private @ResponseBody String test(){
        return "시작페이지!!";
    }

    @GetMapping("/user/")
    private @ResponseBody String usertest(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println(principalDetails.getUser());
        return "유저 페이지"; }

}
