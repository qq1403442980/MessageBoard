package com.sponge.messageboard.service;

import com.sponge.messageboard.bean.User;
import com.sponge.messageboard.dao.UserDao;

public class UserService {
    private UserDao userDao;
    public UserService()
    {
       userDao=new UserDao();
    }
    public User getUserById(Long id)
    {
        return userDao.getUserById(id);
    }
    public boolean updateUser(User user)
    {
        return userDao.updateUser(user);
    }
    public boolean addUser(User user)
    {
        return userDao.addUser(user);
    }
}
