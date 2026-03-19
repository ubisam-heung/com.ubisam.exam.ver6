package com.ubisam.exam.api.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubisam.exam.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
  
}
