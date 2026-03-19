package com.ubisam.exam.api.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static io.u2ware.common.docs.MockMvcRestDocs.*;

import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class ProductTests {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ProductDocs docs;

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
}