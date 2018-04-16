package com.notifi.services;

import java.util.ArrayList;
import java.util.List;
import com.notifi.model.User;
import com.notifi.model.UserInfo;

public interface UserService {

   void save(User user);
   User get(long id);
   List<User> list();
   String update(long id, User user);
   void delete(long id);
}
