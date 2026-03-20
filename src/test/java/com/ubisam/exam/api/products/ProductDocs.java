package com.ubisam.exam.api.products;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.Product;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class ProductDocs extends MockMvcRestDocs{

  //목적: 상품명으로 CRUD 작업 테스트를 위한 메소드
  public Product newEntity(String name) {
    Product productEntity = new Product();
    productEntity.setName(name.length() > 0 ? name : super.randomText("name"));
    productEntity.setDescription(super.randomText("description"));
    productEntity.setPrice(super.randomInt());
    productEntity.setImageUrl(super.randomText("url"));
    productEntity.setStock(super.randomInt());
    productEntity.setCreatedAt(String.valueOf(super.randomInt()));
    productEntity.setUpdatedAt(String.valueOf(super.randomInt()));
    productEntity.setDeletedAt(String.valueOf(super.randomInt()));
    return productEntity;
  }

  //목적: 상품명 혹은 가격을 검색하여 결과를 찾는 테스트 코드를 위한 메소드
  public Product newEntityforHandler(String... entity) {
    Product productEntity = new Product();
    productEntity.setName(entity.length > 0 ? entity[0] : super.randomText("name"));
    productEntity.setDescription(super.randomText("description"));
    Integer price = entity.length > 1 ? Integer.valueOf(entity[1]) : super.randomInt();
    productEntity.setPrice(price);
    productEntity.setImageUrl(super.randomText("url"));
    productEntity.setStock(super.randomInt());
    productEntity.setCreatedAt(String.valueOf(super.randomInt()));
    productEntity.setUpdatedAt(String.valueOf(super.randomInt()));
    productEntity.setDeletedAt(String.valueOf(super.randomInt()));
    return productEntity;
   }

  public Map<String, Object> updateEntity(Map<String, Object> entity, String name){
    entity.put("name", name);
    return entity;
  }

  public Map<String, Object> setKeyword(String keyword){
    Map<String, Object> entity = new HashMap<>();
    entity.put("keyword", keyword);
    return entity;
  }
  
}