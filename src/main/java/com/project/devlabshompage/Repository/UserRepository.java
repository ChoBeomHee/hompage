package com.project.devlabshompage.Repository;

import com.project.devlabshompage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // select * from user where username = username
    public User findByUsername(String username);

}
