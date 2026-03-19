package com.ubisam.exam.api.products;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.Product;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class ProductDocs extends MockMvcRestDocs{

  public Product newEntity(String name) {
    Product productEntity = new Product();
    productEntity.setName(name);
    productEntity.setDescription(super.randomText("description"));
    productEntity.setPrice(super.randomInt());
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