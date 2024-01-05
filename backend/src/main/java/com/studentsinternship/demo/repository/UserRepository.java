package com.studentsinternship.demo.repository;

import com.studentsinternship.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    User findByEmail(@Param("email") String email);
}
