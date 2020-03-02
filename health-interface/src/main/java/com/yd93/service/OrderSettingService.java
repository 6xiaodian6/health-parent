package com.yd93.service;

import com.yd93.entity.Result;
import com.yd93.pojo.OrderSetting;

import java.util.List;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-16 21:27:16
 **/
public interface OrderSettingService {
    /**
     * 批量导入预约信息
     *
     * @param orderSettings 预约信息
     */
    void add(List<OrderSetting> orderSettings);

    /**
     * 单个设置预约信息
     * @param orderSetting
     * @return 返回值是受影响的行数
     */
    void addOne(OrderSetting orderSetting);

    /**
     * 查找当月的预约信息
     *
     * @param orderDate 指定年月
     * @return 返回指定年月的预约内容
     */
    Result findOrder(String orderDate);

    /**
     * 更新可预约人数
     * @param orderSetting 可预约人数参数
     */
    void updateOrder(OrderSetting orderSetting);
}
