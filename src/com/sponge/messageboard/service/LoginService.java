package com.sponge.messageboard.service;

import com.sponge.messageboard.bean.User;
import com.sponge.messageboard.dao.UserDao;

public class LoginService {
    UserDao userDao=null;
    public LoginService()
    {
         userDao=new UserDao();
    }
    public User login(String username,String password)
    {
        return userDao.login(username,password);
    }
}
