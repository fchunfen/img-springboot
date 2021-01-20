package com.fenghainan.springboot.dao;

import com.fenghainan.springboot.entry.Img;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImgMapper {
    int deleteByPrimaryKey(String imgKey);

    int insert(Img record);

    int insertSelective(Img record);

    Img selectByPrimaryKey(String imgKey);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);

    List<Img> selectList(Img img);
}