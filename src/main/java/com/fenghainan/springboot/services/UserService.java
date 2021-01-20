package com.fenghainan.springboot.services;

import com.fenghainan.springboot.entry.User;

public interface UserService
{
    User selectByUserName(String userName);
    int insertUser(User user);
    User selectByCode(String code);
    void updateUserStatus(User user);
    int updateUser(User user);
    int updateUserCode(User user);
}
