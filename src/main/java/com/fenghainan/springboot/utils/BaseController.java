package com.fenghainan.springboot.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
 * 使用了MyBatis的pagehelper分页插件
 * */


public class BaseController
{

    /**
     * 设置请求分页数据
     */
    protected void startPage(int pageSize, int pageNum)
    {

        PageHelper.startPage(pageSize, pageNum);

    }

    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setData(list);
        PageInfo<List> pageInfo = new PageInfo(list);

        long total = pageInfo.getTotal();
        if(total == 0)
        {
            rspData.setMsg("无结果");
            rspData.setCode(3);
        }
        else
        {
            int size = (int) (total % SqlUntil.size == 0 ? total / SqlUntil.size : total / SqlUntil.size + 1);
            rspData.setCode(0);
            rspData.setTotal(size);
            rspData.setNextPageNum(pageInfo.getNextPage());
            rspData.setHasNext(pageInfo.isHasNextPage());
            rspData.setMsg("获取插画列表成功");
        }
        return rspData;
    }
}
