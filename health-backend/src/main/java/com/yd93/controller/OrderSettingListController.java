package com.yd93.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yd93.entity.PageResult;
import com.yd93.entity.QueryPageBean;
import com.yd93.service.OrderSettingListService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description 预约列表
 * @author: yuandian
 * @createTime: 2020-02-21 17:00:16
 */
@RestController
@RequestMapping("/orderSettingList")
public class OrderSettingListController {
    @Reference
    private OrderSettingListService orderSettingListService;

    /**
     * 根据日期查询预约信息
     * @param queryPageBean 封装了条件查询所需要的信息
     * @return
     */
    @RequestMapping("/findList")
    public PageResult findOrderSetting(@RequestBody QueryPageBean queryPageBean){
        return orderSettingListService.findOrderSetting(queryPageBean);
    }
}
