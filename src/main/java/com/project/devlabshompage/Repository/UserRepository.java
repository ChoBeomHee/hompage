package com.project.devlabshompage.Repository;

import com.project.devlabshompage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
