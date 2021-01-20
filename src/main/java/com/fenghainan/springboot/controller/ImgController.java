package com.fenghainan.springboot.controller;

import com.fenghainan.springboot.dao.SearchMapper;
import com.fenghainan.springboot.entry.Img;
import com.fenghainan.springboot.services.ImgService;
import com.fenghainan.springboot.services.SpiderService;
import com.fenghainan.springboot.services.impl.ImgServiceImpl;
import com.fenghainan.springboot.services.impl.SearchServiceImpl;
import com.fenghainan.springboot.utils.annotation.PassToken;
import com.fenghainan.springboot.utils.annotation.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import com.fenghainan.springboot.utils.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping("/img")
public class ImgController extends BaseController
{
    @Autowired
    private ImgServiceImpl imgService;

    @Autowired
    private SearchServiceImpl searchService;

    @Autowired
    private SpiderService spiderService;

    @ResponseBody
    @RequestMapping(value = "/list")
    @PassToken
    public TableDataInfo list(Img img)
    {
        startPage(img.getPage(), img.getSize());
        List<Img> list = imgService.selectList(img);
        return getDataTable(list);
    }

    @ResponseBody
    @RequestMapping(value = "/search")
    @UserLoginToken
    public TableDataInfo search(Img img, HttpServletRequest request)
    {
        startPage(img.getPage(), img.getSize());
        List<Img> list = searchService.selectListByKeywords(img);
        TableDataInfo tableDataInfo = getDataTable(list);
        if (tableDataInfo.getCode() == 3)
        {
            spiderService.doSomething(img.getImgName());
        }
        System.out.println(11111111);
        String userName = (String) request.getAttribute("userName");
        tableDataInfo.setUserName(userName);
        return tableDataInfo;
    }


    @RequestMapping(value = "/test")
    public String test()
    {
        return "01.html";
    }


}
