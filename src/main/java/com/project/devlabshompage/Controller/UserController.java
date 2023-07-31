package com.project.devlabshompage.Controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Secured("ROLE_MASTER") // 특정 메소드에 간단하게
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "info";
    }

    @PreAuthorize("hasRole('MASTER') or hasRole('CORE')") // 특정 메소드에 간단하게
    @GetMapping("/data")
    public @ResponseBody String data(){
        return "data";
    }
}