package com.yd93.dao;

import com.yd93.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-17 08:27:02
 **/
public interface OrderSettingDao {
    /**
     * 通过日期查询预约是否存在
     *
     * @param orderDate 预约日期
     * @return 返回查询到的数量
     */
    Integer findByDate(Date orderDate);

    /**
     * 更新预约信息
     *
     * @param orderSetting 预约信息
     * @return 返回影响的行数
     */
    Integer update(OrderSetting orderSetting);

    /**
     * 添加预约信息
     *
     * @param orderSetting 预约内容
     * @return 返回影响的行数
     */
    void add(OrderSetting orderSetting);

    /**
     * 查询指定年月的预约信息
     *
     * @param orderDate 指定预约年月
     * @return 返回预约信息列表
     */
    List<Map> findOrder(String orderDate);

    /**
     * 更新可预约人数
     *
     * @param orderSetting 可预约人数参数
     * @return 返回受影响的行数
     */
    void updateOrder(OrderSetting orderSetting);

    /**
     * 根据日期查询预约设置信息
     *
     * @param date 日期
     * @return 返回查询结果
     */
    OrderSetting findByOrderDate(Date date);

    /**
     * 更新已预约人数
     *
     * @param orderSetting 预约信息
     */
    void editReservationsByOrderDate(OrderSetting orderSetting);
}
