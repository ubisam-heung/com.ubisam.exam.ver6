package com.ubisam.exam.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "example_product_group")
public class ProductGroup {
  
  @Id
  @GeneratedValue
  private Long id;
  //분류명(사무용, 주방용 등)
  private String groupName;
  //분류 생성일
  private String createdAt;
  //분류 수정일
  private String updatedAt;
  //분류 삭제일
  private String deletedAt;
}
