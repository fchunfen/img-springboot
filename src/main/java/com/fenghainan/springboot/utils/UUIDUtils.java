package com.fenghainan.springboot.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
    public static String getCode()
    {
        int code = (int) ((Math.random()*9+1)*100000);
        return (String.valueOf(code));
    }
}