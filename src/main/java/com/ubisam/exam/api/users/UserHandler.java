package com.ubisam.exam.api.users;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.User;

@Component
@RepositoryEventHandler
public class UserHandler {

  @HandleBeforeCreate
  public void beforeCreate(User u) throws Exception{
    //로그 코드 작성 (Auditing)
    //테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandlebeforeCreate] "+u);
  }

  @HandleBeforeSave
  public void beforeSave(User u) throws Exception{
    //로그 코드 작성 (Auditing)
    //테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandlebeforeSave] "+u);
  }
  
  @HandleBeforeDelete
  public void beforeDelete(User u) throws Exception{
    //로그 코드 작성 (Auditing)
    //테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandlebeforeDelete] "+u);
  }

  @HandleAfterCreate
  public void afterCreate(User u) throws Exception{
    //로그 코드 작성 (Auditing)
    //테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterCreate] "+u);
  }

  @HandleAfterSave
  public void afterSave(User u) throws Exception{
    //로그 코드 작성 (Auditing)
    //테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterSave] "+u);
  }
  
  @HandleAfterDelete
  public void afterDelete(User u) throws Exception{
    //로그 코드 작성 (Auditing)
    //테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterDelete] "+u);
  }
  
}
