package com.yd93.dao;


import com.github.pagehelper.Page;
import com.yd93.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

/**
 * @description 预约信息查询
 * @author: yuandian
 * @createTime: 2020-02-21 19:27:17
 */
public interface OrderSettingListDao {
    /**
     * 根据日期进行预约信息的条件查询
     * @param date 指定要查询的预约信息的日期
     * @return 返回分页查询的结果
     */
    Page<OrderSetting> findOrderSetting(@Param("date") String date);
}
