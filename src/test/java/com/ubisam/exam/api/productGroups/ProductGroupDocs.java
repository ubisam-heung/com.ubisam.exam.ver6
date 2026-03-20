package com.ubisam.exam.api.productGroups;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.ProductGroup;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class ProductGroupDocs extends MockMvcRestDocs{

  //목적: 상품분류명으로 CRUD 작업 테스트를 위한 메소드
  public ProductGroup newEntity(String groupName) {
    ProductGroup productGroupEntity = new ProductGroup();
    productGroupEntity.setGroupName(groupName);
    productGroupEntity.setCreatedAt(String.valueOf(super.randomInt()));
    productGroupEntity.setUpdatedAt(String.valueOf(super.randomInt()));
    productGroupEntity.setDeletedAt(String.valueOf(super.randomInt()));
    return productGroupEntity;
  }

  public Map<String, Object> updateEntity(Map<String, Object> entity, String groupName){
    entity.put("groupName", groupName);
    return entity;
  }

  public Map<String, Object> setKeyword(String keyword){
    Map<String, Object> entity = new HashMap<>();
    entity.put("keyword", keyword);
    return entity;
  }
  
}