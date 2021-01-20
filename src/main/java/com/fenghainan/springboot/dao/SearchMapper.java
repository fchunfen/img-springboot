package com.fenghainan.springboot.dao;


import com.fenghainan.springboot.entry.Img;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchMapper
{
    List<Img> selectListByKeywords(Img img);
}
