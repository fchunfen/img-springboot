package com.fenghainan.springboot.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fenghainan.springboot.entry.User;
import com.fenghainan.springboot.services.UserService;
import com.fenghainan.springboot.services.impl.UserServiceImpl;
import com.fenghainan.springboot.utils.JwtUtils;
import com.fenghainan.springboot.utils.annotation.PassToken;
import com.fenghainan.springboot.utils.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class JwtAuthenticationInterceptor implements HandlerInterceptor
{
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception
    {
        // 从请求头中取出 token  这里需要和前端约定好把jwt放到请求头一个叫token的地方
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod))
        {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class))
        {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required())
            {
                return true;
            }
        }
        //默认全部检查
        else
        {
            System.out.println("被jwt拦截需要验证");
            // 执行认证
            System.out.println(token);
            if (token == null || token.equals("null"))
            {
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json; charset=utf-8");
                PrintWriter out = null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("message","请先登录");
                jsonObject.put("code", 4);
                out = httpServletResponse.getWriter();
                out.append(jsonObject.toString());
                return false;
                //这里其实是登录失效,没token了   这个错误也是我自定义的，读者需要自己修改
            }

            // 获取 token 中的 user Name
            String userName = JwtUtils.getAudience(token);

            //找找看是否有这个user   因为我们需要检查用户是否存在，读者可以自行修改逻辑
            User user = userService.selectByUserName(userName);
            System.out.println(user);
            if (user == null)
            {
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json; charset=utf-8");
                PrintWriter out = null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("message","无该用户");
                jsonObject.put("code", 1);
                out = httpServletResponse.getWriter();
                out.append(jsonObject.toString());
                return false;
            }

            // 验证 token

            if(!JwtUtils.verifyToken(token, user))
            {
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json; charset=utf-8");
                PrintWriter out = null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("message","登录超时,请重新登录");
                jsonObject.put("code", 2);
                out = httpServletResponse.getWriter();
                out.append(jsonObject.toString());
                return false;
            }

//            //获取载荷内容
            String userId = JwtUtils.getClaimByName(token, "userId").asString();
            String userEmail = JwtUtils.getClaimByName(token, "userEmail").asString();


            //放入attribute以便后面调用
            httpServletRequest.setAttribute("userId", userId);
            httpServletRequest.setAttribute("userName", userName);
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}