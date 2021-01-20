package com.fenghainan.springboot.services.impl;

import com.fenghainan.springboot.dao.ImgMapper;
import com.fenghainan.springboot.entry.Img;
import com.fenghainan.springboot.services.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgServiceImpl implements ImgService
{
    @Autowired
    private ImgMapper imgMapper;

    @Override
    public int deleteByPrimaryKey(String imgKey)
    {
        return 0;
    }

    @Override
    public int insert(Img record)
    {
        return 0;
    }

    @Override
    public int insertSelective(Img record)
    {
        return 0;
    }

    @Override
    public Img selectByPrimaryKey(String imgKey)
    {
        return null;
    }

    @Override
    public List<Img> selectList(Img img)
    {
        return imgMapper.selectList(img);
    }

    @Override
    public int updateByPrimaryKeySelective(Img record)
    {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Img record)
    {
        return 0;
    }
}
