package com.fenghainan.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan("com.fenghainan.springboot")
@MapperScan("com.fenghainan.springboot.dao")
@EnableAsync
public class Springboot02Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Springboot02Application.class, args);
    }

}
