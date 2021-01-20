package com.fenghainan.springboot.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaseRuntime {
    /**
     * @throws IOException
     * @throws InterruptedException
     */
    public void spider(String keywords) throws IOException, InterruptedException
    {

        String exe = "python";
        String command = "W:\\PythonKits\\PythonWorkPlace\\Spider\\spider-pixivic.py";
//        String keyword = "派蒙";
        String[] cmdArr = new String[] {exe, command, keywords};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str2 = dis.readLine();
        process.waitFor();
        System.out.println(str2);
        return;
}
}
