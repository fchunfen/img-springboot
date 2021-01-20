package com.fenghainan.springboot.services;


import com.fenghainan.springboot.utils.BaseRuntime;
import com.fenghainan.springboot.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class SpiderService
{
    @Async("doSpiderExecutor")
    public String doSomething(String keywords)
    {
        Logger logger = LoggerFactory.getLogger(SpiderService.class);
        logger.info("doSpiderExecutor, keywords={}", keywords);
        String exe = "python";
        String command = "W:\\PythonKits\\PythonWorkPlace\\Spider\\spider-pixivic.py";
        String[] cmdArr = new String[] {exe, command, keywords};
        Process process = null;
        try
        {
            process = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), "gb2312"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            process.waitFor();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return keywords;
    }

}
