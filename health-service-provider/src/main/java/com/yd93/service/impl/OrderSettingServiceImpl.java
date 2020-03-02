package com.yd93.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yd93.constant.MessageConstant;
import com.yd93.dao.OrderSettingDao;
import com.yd93.entity.Result;
import com.yd93.pojo.OrderSetting;
import com.yd93.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-17 08:19:24
 **/
@Service
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    OrderSettingDao orderSettingDao;

    /**
     * 批量导入预约信息
     *
     * @param orderSettings 预约信息
     */
    @Override
    public void add(List<OrderSetting> orderSettings) {
        for (OrderSetting orderSetting : orderSettings) {
            //对于批量更新的插入而言，在mybatis中可以直接使用xml文件里面的sql语句拼接执行，效率会有所的提升
            if (orderSettingDao.findByDate(orderSetting.getOrderDate()) > 0) {
                orderSettingDao.update(orderSetting);
            } else {
                orderSettingDao.add(orderSetting);
            }
        }
    }

    /**
     * 单个预约执行操作，也就是页面上进行的手动点击进行的设置
     *
     * @param orderSetting
     */
    @Override
    public void addOne(OrderSetting orderSetting) {
        //对传过来的数据进行检验，如果日期存在就直接执行更新操作，否则才执行添加操作
        if (orderSettingDao.findByDate(orderSetting.getOrderDate()) > 0) {
            orderSettingDao.updateOrder(orderSetting);
        } else {
            orderSettingDao.add(orderSetting);
        }
    }

    /**
     * 查找当月的预约信息
     *
     * @param orderDate 指定年月
     * @return 返回指定年月的预约内容
     */
    @Override
    public Result findOrder(String orderDate) {
        List<Map> orderSettings = orderSettingDao.findOrder(orderDate);
        return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, orderSettings);
    }

    /**
     * 更新可预约人数
     *
     * @param orderSetting 可预约人数参数
     */
    @Override
    public void updateOrder(OrderSetting orderSetting) {
        orderSettingDao.updateOrder(orderSetting);
    }
}
