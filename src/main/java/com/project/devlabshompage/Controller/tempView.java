package com.project.devlabshompage.Controller;

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
    private @ResponseBody String usertest(){ return "유저 페이지"; }

}
