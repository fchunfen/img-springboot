package com.fenghainan.springboot.services;

import com.fenghainan.springboot.entry.Img;

import java.util.List;

public interface SearchService
{
    List<Img> selectListByKeywords(Img img);

}
