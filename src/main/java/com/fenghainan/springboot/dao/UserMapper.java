package com.fenghainan.springboot.dao;

import com.fenghainan.springboot.entry.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper
{
    User selectByUserName(String userName);
    int insertUser(User user);
    User selectByCode(String code);
    void updateUserStatus(User user);
    int updateUser(User user);
    int updateUserCode(User user);

}
