package com.yd93.controller;

import com.yd93.constant.MessageConstant;
import com.yd93.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-20 21:13:21
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/getUsername")
    public Result getUsername() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = user.getUsername();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, username);
        }catch (Exception e ){
            return new Result(false,MessageConstant.GET_USERNAME_FAIL);
        }
    }
}
