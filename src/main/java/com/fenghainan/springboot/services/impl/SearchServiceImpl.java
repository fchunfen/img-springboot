package com.fenghainan.springboot.services.impl;

import com.fenghainan.springboot.dao.ImgMapper;
import com.fenghainan.springboot.dao.SearchMapper;
import com.fenghainan.springboot.entry.Img;
import com.fenghainan.springboot.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService
{
    @Autowired
    private SearchMapper searchMapper;



    @Override
    public List<Img> selectListByKeywords(Img img)
    {
        return searchMapper.selectListByKeywords(img);
    }
}
