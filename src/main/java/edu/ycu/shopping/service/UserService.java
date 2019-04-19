package edu.ycu.shopping.service;

import edu.ycu.shopping.entity.User;

public interface UserService {
    //    登录
    User login(String username, String password);

    boolean checkUser(String username);

    int insertUser(User user);
}
