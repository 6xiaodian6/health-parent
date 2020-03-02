package com.yd93.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yd93.constant.MessageConstant;
import com.yd93.entity.Result;
import com.yd93.pojo.OrderSetting;
import com.yd93.service.OrderSettingService;
import com.yd93.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: poi文件上传
 * @author: yd93
 * @createTime: 2019-10-16 20:02:51
 **/
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;

    /**
     * 批量导入预约设置信息
     *
     * @param multipartFile 上传的文件
     * @return 返回处理结果
     */
    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile multipartFile) {
        //获取上传的文件，然后通过list集合接收，在转为list<Ordersetting>集合进行后台数据的传输和存储
        try {
            List<String[]> excel = POIUtils.readExcel(multipartFile);
            List<OrderSetting> list = new ArrayList<>();
            for (String[] strings : excel) {
                list.add(new OrderSetting(new Date(strings[0]), Integer.parseInt(strings[1])));
            }
            orderSettingService.add(list);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS,list.get(list.size()-1));
        } catch (IOException e) {
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    /**
     * 查询当月的预约信息
     *
     * @param orderDate 指定年月
     * @return 返回当月的预约信息
     */
    @RequestMapping("/findOrder")
    public Result findOrder(String orderDate) {
        return orderSettingService.findOrder(orderDate);
    }

    /**
     * 更新可预约人数
     *
     * @param orderSetting 可预约人参数，包含日期和数量
     * @return 返回设置结果
     */
    @RequestMapping("/updateOrder")
    public Result updateOrder(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.addOne(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }
}

