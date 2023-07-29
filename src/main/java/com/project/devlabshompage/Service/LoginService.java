package com.project.devlabshompage.Service;

import com.project.devlabshompage.Repository.UserRepository;
import com.project.devlabshompage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    public void join(User user){
        userRepository.save(user);
        System.out.println("회원가입 완료!");
    }
}
