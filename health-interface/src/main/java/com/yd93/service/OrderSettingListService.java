package com.yd93.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yd93.entity.PageResult;
import com.yd93.entity.QueryPageBean;


/**
 * @description 预约列表信息管理
 * @author: yuandian
 * @createTime: 2020-02-21 19:21:37
 */
public interface OrderSettingListService {
    /**
     * 预约信息查询
     *
     * @param queryPageBean 封装了分页查询所需要的信息
     * @return
     */
    PageResult findOrderSetting(QueryPageBean queryPageBean);
}
