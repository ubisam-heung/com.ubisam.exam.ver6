package com.ubisam.exam.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="example_user")
public class User {

  @Id
  @GeneratedValue
  private Long id;

  //유저이름
  private String name;
  //유저이메일
  private String email;
  //유저 비밀번호
  private String password;
  //유저 전화번호
  private Integer phone;
  //유저 주소
  private String address;
  //유저 가입일
  private String createdAt;
  //유저 수정일
  private String updatedAt;
  //유저 삭제일
  private String deletedAt;
  //마지막 로그인
  private String lastLoginedAt;

  
}
