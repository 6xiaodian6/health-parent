package com.yd93.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.yd93.constant.MessageConstant;
import com.yd93.constant.RedisMessageConstant;
import com.yd93.entity.Result;
import com.yd93.pojo.Order;
import com.yd93.service.OrderService;
import com.yd93.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-18 20:36:41
 **/
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;

    /**
     * 体检预约
     *
     * @param map
     * @return
     */
    @RequestMapping("/submit")
    public Result submitOrder(@RequestBody Map map) {
        String telephone = (String) map.get("telephone");
        //从Redis中获取缓存的验证码，key为手机号+RedisConstant.SENDTYPE_ORDER
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");
        //校验手机验证码
        if (codeInRedis == null || !codeInRedis.equals(validateCode)) {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Result result = null;
        //调用体检预约服务
        try {
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            result = orderService.order(map);
        } catch (Exception e) {
            e.printStackTrace();
            //预约失败
            return null;
        }
        if (result.isFlag()) {
            //预约成功，发送短信通知
            String orderDate = (String) map.get("orderDate");
            /*try {
                SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE, telephone, orderDate);
            } catch (ClientException e) {
                e.printStackTrace();
            }*/
            System.out.println("预约成功");
        }
        return result;
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Map<String, String> orderDetail = orderService.findById(id);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, orderDetail);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
