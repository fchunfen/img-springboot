package com.fenghainan.springboot.config;

import com.fenghainan.springboot.interceptor.JwtAuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class JwtInterceptorConfig implements WebMvcConfigurer
{
    @Bean
    public HandlerInterceptor getMyInterceptor()
    {
        return new JwtAuthenticationInterceptor();
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //默认拦截所有路径
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/img/*");

    }


}
