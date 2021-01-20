package com.fenghainan.springboot.services;

import com.fenghainan.springboot.entry.Img;

import java.util.List;


public interface ImgService
{
    int deleteByPrimaryKey(String imgKey);

    int insert(Img record);

    int insertSelective(Img record);

    Img selectByPrimaryKey(String imgKey);

    List<Img> selectList(Img img);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);
}
