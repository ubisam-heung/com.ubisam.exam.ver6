package com.ubisam.exam.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="example_product")
public class Product {

  @Id
  @GeneratedValue
  private Long id;

  //상품명
  private String name;
  //상품 설명
  private String description;
  //상품 가격
  private Integer price;
  //사진 경로
  private String imageUrl;
  //재고
  private Integer stock;
  //상품 등록일
  private String createdAt;
  //상품 수정일
  private String updatedAt;
  //상품 삭제일
  private String deletedAt;

  //판매자(User - Product 매칭)
  //1명의 User가 여러개의 Product 등록 가능
  //1개의 Product가 여러명의 User 등록 불가능
  @ManyToOne
  private User user;

  //분류(Product - ProductGroup 매칭)
  //1개의 Product가 여러개의 ProductGroup에 등록 가능
  //1개의 ProductGroup이 여러개의 Product 등록 가능
  @ManyToMany
  private ProductGroup productGroup;
  
}
