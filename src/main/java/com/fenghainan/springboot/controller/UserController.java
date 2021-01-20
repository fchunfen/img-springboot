package com.fenghainan.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.fenghainan.springboot.entry.Img;
import com.fenghainan.springboot.entry.User;
import com.fenghainan.springboot.services.impl.ImgServiceImpl;
import com.fenghainan.springboot.services.impl.UserServiceImpl;
import com.fenghainan.springboot.utils.JwtUtils;
import com.fenghainan.springboot.utils.TableDataInfo;
import com.fenghainan.springboot.utils.UUIDUtils;
import com.fenghainan.springboot.utils.annotation.PassToken;
import com.fenghainan.springboot.utils.annotation.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserServiceImpl userService;

    @PassToken
    @ResponseBody
    @RequestMapping(value = "/register")
    public Object register(User user)
    {
        user.setStatus(0);
        String code = UUIDUtils.getUUID()+ UUIDUtils.getUUID();
        user.setCode(code);
        userService.insertUser(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","注册成功，请登录邮箱激活！");
        jsonObject.put("code", 0);
        return jsonObject;
    }

    @PassToken
    @ResponseBody
    @RequestMapping(value = "/checkCode")
    public Object checkCode(String code)
    {
        User user = userService.selectByCode(code);
        System.out.println(user);
        JSONObject jsonObject = new JSONObject();
        //如果用户不等于null，把用户状态修改status=1
        if (user != null)
        {
            user.setStatus(1);
            //把code验证码清空，已经不需要了
            user.setCode("0");
            System.out.println(user);
            userService.updateUserStatus(user);
            jsonObject.put("message","激活成功");
            jsonObject.put("code", 0);
        }
        else
        {
            jsonObject.put("message","激活失败");
            jsonObject.put("code", 1);
        }
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/login")
    @PassToken
    public Object login(User user)
    {
        JSONObject jsonObject = new JSONObject();
        User userForBase = userService.selectByUserName(user.getUserName());
        System.out.println(userForBase);

        if(userForBase == null)
        {
            jsonObject.put("message","登录失败,用户不存在或未激活");
            jsonObject.put("code", 1);
            return jsonObject;
        }
        else
        {
            if (!userForBase.getUserPassword().equals(user.getUserPassword()))
            {
                jsonObject.put("message","登录失败,密码错误");
                jsonObject.put("code", 1);
            }
            else
            {
                String token = JwtUtils.createToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                jsonObject.put("code", 0);
            }
            return jsonObject;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/forget")
    @PassToken
    public Object forget(User user)
    {
        JSONObject jsonObject = new JSONObject();
        User userForBase = userService.selectByUserName(user.getUserName());
        System.out.println(userForBase);
        if(userForBase == null)
        {
            jsonObject.put("message","登录失败,用户不存在或未激活");
            jsonObject.put("code", 1);
            return jsonObject;
        }
        else
        {
            if (!userForBase.getUserEmail().equals(user.getUserEmail()))
            {
                jsonObject.put("message","邮箱输入错误");
                jsonObject.put("code", 1);
            }
            else
            {
                String code = UUIDUtils.getCode();
                userForBase.setCode(code);
                userService.updateUserCode(userForBase);
                jsonObject.put("message","验证码已发送");
                jsonObject.put("code", 0);

            }
            return jsonObject;
        }
    }
    @ResponseBody
    @RequestMapping(value = "/update")
    @PassToken
    public Object updateUser(User user)
    {
        JSONObject jsonObject = new JSONObject();
        User userForBase = userService.selectByUserName(user.getUserName());
        System.out.println(userForBase);
        if(userForBase == null)
        {
            jsonObject.put("message","修改失败,用户不存在或未激活");
            jsonObject.put("code", 1);
            return jsonObject;
        }
        else
        {
            if (!userForBase.getCode().equals(user.getCode()))
            {
                jsonObject.put("message","验证码错误");
                jsonObject.put("code", 1);
            }
            else
            {
                userForBase.setCode(UUIDUtils.getUUID());
                userForBase.setUserName("");
                userForBase.setUserEmail("");
                userForBase.setUserPassword("");
                userService.updateUserCode(userForBase);
                userForBase.setUserPassword(user.getUserPassword());
                userService.updateUser(userForBase);
                jsonObject.put("message","修改密码成功");
                jsonObject.put("code", 0);
            }
        }
        return jsonObject;


    }




    @UserLoginToken
    @RequestMapping("/getMessage")
    @ResponseBody
    public Object getMessage()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","验证成功");
        jsonObject.put("code", 0);
        return jsonObject;
    }




}
