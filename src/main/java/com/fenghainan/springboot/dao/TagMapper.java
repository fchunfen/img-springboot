package com.fenghainan.springboot.dao;

import com.fenghainan.springboot.entry.Tag;

public interface TagMapper {
    int deleteByPrimaryKey(String tagsId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(String tagsId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}