package com.ubisam.exam.api.users;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.User;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class UserDocs extends MockMvcRestDocs{

  public User newEntity(String name) {
    User userEntity = new User();
    userEntity.setName(name);
    userEntity.setEmail(super.randomText("email"));
    userEntity.setPassword(super.randomText("password"));
    userEntity.setPhone(super.randomInt());
    userEntity.setAddress(super.randomText("address"));
    userEntity.setCreatedAt(String.valueOf(super.randomInt()));
    userEntity.setUpdatedAt(String.valueOf(super.randomInt()));
    userEntity.setDeletedAt(String.valueOf(super.randomInt()));
    userEntity.setLastLoginedAt(String.valueOf(super.randomInt()));
    return userEntity;
  }

  public Map<String, Object> updateEntity(Map<String, Object> entity, String name){
    entity.put("name", name);
    return entity;
  }
  
}