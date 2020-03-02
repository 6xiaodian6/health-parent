package com.yd93.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yd93.constant.MessageConstant;
import com.yd93.entity.Result;
import com.yd93.service.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description: ${DESCRIPTION}
 * @author: yuandian
 * @createTime: 2019-10-21 14:54:00
 **/
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Reference
    private MenuService menuService;
    @RequestMapping("/findMenu")
    public Result getMenu(){
       try{
           List<Map> map = menuService.getMenu();
           return new Result(true,MessageConstant.GET_MENU_SUCCESS,map);
       }catch (Exception e){
           return new Result(false, MessageConstant.GET_MENU_FAIL);
       }
    }
}
