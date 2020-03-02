package com.yd93.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yd93.dao.OrderSettingListDao;
import com.yd93.entity.PageResult;
import com.yd93.entity.QueryPageBean;
import com.yd93.pojo.OrderSetting;
import com.yd93.service.OrderSettingListService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description
 * @author: yuandian
 * @createTime: 2020-02-21 19:25:12
 */
@Service
public class OrderSettingListServiceImpl implements OrderSettingListService {
    @Autowired
    OrderSettingListDao orderSettingListDao;

    /**
     * 查询预约列表信息，结果使用分页展示
     *
     * @param queryPageBean 封装的是查询条件相关的信息
     * @return 分页查询的结果
     */
    @Override
    public PageResult findOrderSetting(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //条件分页查询的一个灵活的位置就是当传进来参数和不传进来参数的时候的处理，这里采用的是在service里面判断是不是传进来参数了，如果没有就直接用%代替同时使用模糊查询进行，传参了就用%把它包裹起来进行模糊查询
        Page<OrderSetting> page = orderSettingListDao.findOrderSetting(queryPageBean.getQueryString() == null? "%" : "%"+queryPageBean.getQueryString()+"%");
        return new PageResult(page.getTotal(), page.getResult());
    }
}
