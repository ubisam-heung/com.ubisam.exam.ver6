package com.ubisam.exam.api.productGroups;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam.domain.Product;
import com.ubisam.exam.domain.ProductGroup;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

import static io.u2ware.common.docs.MockMvcRestDocs.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class ProductGroupTests {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ProductGroupDocs docs;

  @Autowired
  private ProductGroupRepository productGroupRepository;

  //CRUD 테스트
	@Test
	void contextLoads() throws Exception{

    //Crud - C
    mvc.perform(post("/api/productGroups").content(docs::newEntity, "사무용"))
    .andDo(print()).andExpect(is2xx()).andDo(result(docs::context, "entity1"));
 
    //Crud - R
    String url = docs.context("entity1", "$._links.self.href");
    mvc.perform(post(url)).andExpect(is2xx()).andExpect(isJson("$.groupName", "사무용"));
 
    //Crud - U
    Map<String, Object> body = docs.context("entity1", "$");
    mvc.perform(put(url).content(docs::updateEntity, body, "주방용"))
    .andExpect(is2xx()).andExpect(isJson("$.groupName", "주방용"));
  
    //Crud - D
    mvc.perform(delete(url)).andExpect(is2xx());
  
  }

  // handler 테스트용
  @Test
  void contextLoad2() throws Exception{

    // 사무용1~사무용40까지의 그룹 추가
    List<ProductGroup> productGroupList = new ArrayList<>(); 
    for ( int i = 1; i <= 40; i++){
      productGroupList.add(docs.newEntity("사무용" + i));
    }
    productGroupRepository.saveAll(productGroupList);

    // 1) 특정 그룹명으로 검색하기
    JpaSpecificationBuilder<ProductGroup> queryGroupName = JpaSpecificationBuilder.of(ProductGroup.class);
    queryGroupName.where().and().eq("groupName", "사무용7");
    List<ProductGroup> resultName = productGroupRepository.findAll(queryGroupName.build());
    // 쿼리 결과 행를 찾고 그 행의 name필드가 "사무용7" 인지 검사
    boolean hasProductName = resultName.stream().anyMatch(u -> "사무용7".equals(u.getGroupName()));
    assertEquals(true, hasProductName);

  }

  @Test
  void contextLoad3() throws Exception{

    // 사무용1~사무용40까지의 그룹 추가
    List<ProductGroup> productGroupList = new ArrayList<>(); 
    for ( int i = 1; i <= 40; i++){
      productGroupList.add(docs.newEntity("사무용" + i));
    }
    productGroupRepository.saveAll(productGroupList);

    //Search - 검색
    mvc.perform(post("/api/productGroups/search")
    .content(docs::setKeyword, "사무용8")).andExpect(is2xx());
    //Search - 페이지 10개씩 4페이지
    mvc.perform(post("/api/productGroups/search")
    .content(docs::setKeyword, "").param("size", "10")).andExpect(is2xx());
    //Search - 정렬 name, 내림차순
    mvc.perform(post("/api/productGroups/search")
    .content(docs::setKeyword, "").param("sort", "name,desc")).andExpect(is2xx());
  }
}