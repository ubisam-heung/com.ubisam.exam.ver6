package com.ubisam.exam.api.users;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.User;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class UserDocs extends MockMvcRestDocs{

  //목적: 이름을 검색하여 결과를 찾는 테스트 코드를 위한 메소드
  public User newEntity(String name) {
    User userEntity = new User();
    userEntity.setName(name);
    userEntity.setEmail(super.randomText("email"));
    userEntity.setPassword(super.randomText("password"));
    userEntity.setPhone(String.valueOf("010"+super.randomInt()));
    userEntity.setAddress(super.randomText("address"));
    userEntity.setCreatedAt(String.valueOf(super.randomInt()));
    userEntity.setUpdatedAt(String.valueOf(super.randomInt()));
    userEntity.setDeletedAt(String.valueOf(super.randomInt()));
    userEntity.setLastLoginedAt(String.valueOf(super.randomInt()));
    return userEntity;
  }

  //목적: 이름 혹은 이메일 검색하여 결과를 찾는 테스트 코드를 위한 메소드
   public User newEntityforHandler(String... entity) {
    User userEntity = new User();
    userEntity.setName(entity.length > 0 ? entity[0] : super.randomText("name"));
    userEntity.setEmail(entity.length > 0 ? entity[1] : super.randomText("email"));
    userEntity.setPassword(super.randomText("password"));
    userEntity.setPhone(String.valueOf("010"+super.randomInt()));
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