package com.yd93.service;

import com.yd93.entity.Result;

import java.util.Map;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-18 20:40:14
 **/
public interface OrderService {
    /**
     * 体检预约
     * @param map 预约参数
     * @return 返回预约结果
     */
    Result order(Map map) throws Exception;

    /**
     * 根据id查询订单预约信息
     * @param id 订单id
     * @return 返回封装有订单信息的map集合
     */
    Map<String, String> findById(Integer id);
}
