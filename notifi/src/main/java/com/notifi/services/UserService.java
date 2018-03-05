package com.notifi.services;

import java.util.ArrayList;
import java.util.List;
import com.notifi.model.User;

public interface UserService {

   ArrayList<String> save(User user);
   User get(long id);
   List<User> list();
   String update(long id, User user);
   void delete(long id);
}
