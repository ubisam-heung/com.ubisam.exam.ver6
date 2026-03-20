package com.ubisam.exam.api.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam.domain.User;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

import static io.u2ware.common.docs.MockMvcRestDocs.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class UserTests {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private UserDocs docs;

  @Autowired
  private UserRepository userRepository;

  //CRUD 테스트
	@Test
	void contextLoads() throws Exception{

    //Crud - C
    mvc.perform(post("/api/users").content(docs::newEntity, "홍길동"))
    .andDo(print()).andExpect(is2xx()).andDo(result(docs::context, "entity1"));

    //Crud - R
    String url = docs.context("entity1", "$._links.self.href");
    mvc.perform(get(url)).andExpect(is2xx())
    .andExpect(isJson("$.name", "홍길동"));

    //Crud - U
    Map<String, Object> body = docs.context("entity1", "$");
    mvc.perform(put(url).content(docs::updateEntity, body, "김길동"))
    .andExpect(is2xx()).andExpect(isJson("$.name", "김길동"));

    //Crud - D
    mvc.perform(delete(url)).andExpect(is2xx());
	}

  //Handler 테스트
  @Test
  void contextLoads2() throws Exception{

    //길동1 ~ 길동40까지의 유저 추가
    List<User> userList = new ArrayList<>(); 
    for ( int i = 1; i <= 40; i++){
      userList.add(docs.newEntityforHandler("길동" + i, "abc"+i+"@abc.com"));
    }
    userRepository.saveAll(userList);

    //1) 특정 유저를 이름으로 검색하기
    JpaSpecificationBuilder<User> queryName = JpaSpecificationBuilder.of(User.class);
    Specification<User> specName = queryName.where()
      .and().eq("name", "길동4").build();
    List<User> resultName = userRepository.findAll(specName);
    //쿼리 결과 행 1개를 찾고 그 행의 name필드가 "길동4" 인지 검사
    boolean hasUserName = resultName.stream().anyMatch(u -> "길동4".equals(u.getName()));
    assertEquals(true, hasUserName);

    //2) 특정 유저를 이메일로 검색하기
    JpaSpecificationBuilder<User> queryEmail = JpaSpecificationBuilder.of(User.class);
    Specification<User> specEmail = queryEmail.where()
    .and().eq("email", "abc4@abc.com").build();
    List<User> resultEmail = userRepository.findAll(specEmail);
    //쿼리 결과 행 1개를 찾고 그 행의 email필드가 "abc4@abc.com" 인지 검사
    boolean hasUserEmail = resultEmail.stream().anyMatch(u -> "abc4@abc.com".equals(u.getEmail()));
    assertEquals(true, hasUserEmail);
  }

  //Search 테스트
  @Test
  void contextLoads3() throws Exception{

    //길동1 ~ 길동40까지의 유저 추가
    List<User> userList = new ArrayList<>(); 
    for ( int i = 1; i <= 40; i++){
      userList.add(docs.newEntityforHandler("길동" + i, "abc"+i+"@abc.com"));
    }
    userRepository.saveAll(userList);

    //Search - 검색
    //이름
    mvc.perform(get("/api/users/search/findByName")
    .param("name", "길동4")).andExpect(is2xx());
    //이메일
    mvc.perform(get("/api/users/search/findByEmail")
    .param("email", "abc7@abc.com")).andExpect(is2xx());

    //Search - 페이징 5개씩 8페이지
    mvc.perform(get("/api/users").param("size", "5")).andExpect(is2xx());

    //Search - 정렬 id 내림차순
    mvc.perform(get("/api/users").param("sort", "id,desc")).andExpect(is2xx());

  }


}