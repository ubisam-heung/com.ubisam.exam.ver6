package com.ubisam.exam.api.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam.domain.Product;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

import static io.u2ware.common.docs.MockMvcRestDocs.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class ProductTests {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ProductDocs docs;

  @Autowired
  private ProductRepository productRepository;

  //CRUD 테스트
	@Test
	void contextLoads() throws Exception{

    //Crud - C
    mvc.perform(post("/api/products").content(docs::newEntity, "사과"))
    .andDo(print()).andExpect(is2xx()).andDo(result(docs::context, "entity1"));

    //Crud - R
    String url = docs.context("entity1", "$._links.self.href");
    mvc.perform(post(url)).andExpect(is2xx()).andExpect(isJson("$.name", "사과"));

    //Crud - U
    Map<String, Object> body = docs.context("entity1", "$");
    mvc.perform(put(url).content(docs::updateEntity, body, "바나나"))
    .andExpect(is2xx()).andExpect(isJson("$.name", "바나나"));

    //Crud - D
    mvc.perform(delete(url)).andExpect(is2xx());

  }

  // handler 테스트용
  @Test
  void contextLoad2() throws Exception{

    // 사과1 ~ 사과40까지의 상품 추가
    // 1000~40000까지의 가격 추가
    List<Product> productList = new ArrayList<>(); 
    for ( int i = 1; i <= 40; i++){
      productList.add(docs.newEntityforHandler("사과" + i, i+"000"));
    }
    productRepository.saveAll(productList);

    // 1) 특정 상품명으로 검색하기
    JpaSpecificationBuilder<Product> queryName = JpaSpecificationBuilder.of(Product.class);
    queryName.where().and().eq("name", "사과5");
    List<Product> resultName = productRepository.findAll(queryName.build());
    // 쿼리 결과 행를 찾고 그 행의 name필드가 "사과5" 인지 검사
    boolean hasProductName = resultName.stream().anyMatch(u -> "사과5".equals(u.getName()));
    assertEquals(true, hasProductName);

    // 2) 특정 가격으로 검색하기
    JpaSpecificationBuilder<Product> queryPrice = JpaSpecificationBuilder.of(Product.class);
    queryPrice.where().and().eq("price", 12000);
    List<Product> resultPrice = productRepository.findAll(queryPrice.build());
    System.out.println(resultPrice);
    // 쿼리 결과를 찾고 그 행의 price필드가 12000인지 검사
    boolean hasProductPrice = resultPrice.stream().anyMatch(u -> 12000 == u.getPrice());
    assertEquals(true, hasProductPrice);

  }

  @Test
  void contextLoad3() throws Exception{
    // 사과1 ~ 사과40까지의 상품 추가
    // 1000~40000까지의 가격 추가
    List<Product> productList = new ArrayList<>(); 
    for ( int i = 1; i <= 40; i++){
      productList.add(docs.newEntityforHandler("사과" + i, i+"000"));
    }
    productRepository.saveAll(productList);

    //Search - 검색
    mvc.perform(post("/api/products/search").content(docs::setKeyword, "사과5")).andExpect(is2xx());
    mvc.perform(post("/api/products/search").content(docs::setKeyword, "13000")).andExpect(is2xx());
    //Search - 페이지 10개씩 4페이지
    mvc.perform(post("/api/products/search").content(docs::setKeyword, "").param("size", "10")).andExpect(is2xx());
    //Search - 정렬 name, 내림차순
    mvc.perform(post("/api/products/search").content(docs::setKeyword, "").param("sort", "name,desc")).andExpect(is2xx());
  }
}