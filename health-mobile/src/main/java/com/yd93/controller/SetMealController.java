package com.yd93.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yd93.constant.MessageConstant;
import com.yd93.entity.Result;
import com.yd93.pojo.Setmeal;
import com.yd93.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-17 20:40:49
 **/
@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    @Reference
    SetmealService setmealService;

    /**
     * 查询所有套餐
     *
     * @return 返回套餐
     */
    @RequestMapping("/getSetmeal")
    public Result getSetmeal() {
        try {
            List<Setmeal> list = setmealService.getSetmeal();
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, list);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    /**
     * 根据ID查询套餐信息包括检查组和检查项
     *
     * @param id 套餐id
     * @return 返回套餐信息对象
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            if (id != null && id > 0) {
                return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmealService.findById(id));
            } else {
                return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
            }
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    /**
     * 根据id查询简单套餐
     *
     * @param id 套餐id
     * @return 返回查询结果对象
     */
    @RequestMapping("/findSetmealById")
    public Result findSetmealById(Integer id) {
        try {
            if (id != null && id > 0) {
                return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmealService.findSetmealById(id));
            } else {
                return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
            }
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
}
