package com.fenghainan.springboot.services.impl;

import com.fenghainan.springboot.dao.ImgMapper;
import com.fenghainan.springboot.dao.UserMapper;
import com.fenghainan.springboot.entry.User;
import com.fenghainan.springboot.services.IMailService;
import com.fenghainan.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IMailService imailService;


    @Override
    public User selectByUserName(String userName)
    {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public int insertUser(User user)
    {

        userMapper.insertUser(user);
        String code = user.getCode();
        System.out.println("code:"+code);
        //主题
        String subject = "来自动漫素材采集管理系统的激活邮件";
        //user/checkCode?code=code(激活码)是我们点击邮件链接之后根据激活码查询用户，如果存在说明一致，将用户状态修改为“1”激活
        //上面的激活码发送到用户注册邮箱
        String context = "<a href=\"http://192.168.2.210:8080/user/checkCode?code="+code+"\">激活请点击:"+code+"</a>";
        //发送激活邮件
        imailService.sendHtmlMail (user.getUserEmail(),subject,context);
        return 1;
    }

    @Override
    public User selectByCode(String code)
    {
        return userMapper.selectByCode(code);
    }

    @Override
    public void updateUserStatus(User user)
    {
        userMapper.updateUserStatus(user);
    }

    @Override
    public int updateUser(User user)
    {
        return userMapper.updateUser(user);
    }

    @Override
    public int updateUserCode(User user)
    {
        int res = userMapper.updateUserCode(user);
        String code = user.getCode();
        System.out.println("code:"+code);
        //主题
        String subject = "来自动漫素材采集管理系统的验证码邮件";
        //user/checkCode?code=code(激活码)是我们点击邮件链接之后根据激活码查询用户，如果存在说明一致，将用户状态修改为“1”激活
        //上面的激活码发送到用户注册邮箱
        String context = "<p>您正在进行修改密码操作，您的验证码为:"+code+"</a>";
        //发送激活邮件
        imailService.sendHtmlMail (user.getUserEmail(),subject,context);
        return res;

    }


}
