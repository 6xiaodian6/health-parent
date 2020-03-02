package com.yd93.dao;

import com.yd93.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    public void add(Order order);
    public List<Order> findByCondition(Order order);
    public Map findById4Detail(Integer id);
    public Integer findOrderCountByDate(String date);
    public Integer findOrderCountAfterDate(String date);
    public Integer findVisitsCountByDate(String date);
    public Integer findVisitsCountAfterDate(String date);
    public List<Map> findHotSetmeal();

    /**
     * 根据id查询订单信息
     * @param id 订单id
     * @return 返回订单信息map集合
     */
    Map<String, String> findById(Integer id);
}
