package com.yd93.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.yd93.constant.MessageConstant;
import com.yd93.constant.RedisMessageConstant;
import com.yd93.entity.Result;
import com.yd93.pojo.Member;
import com.yd93.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-19 14:26:53
 **/
@RestController
@RequestMapping("/login")
public class LoginController {
    @Reference
    private LoginService loginService;
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/check")
    public Result check(HttpServletResponse response, @RequestBody Map map) {
        String telephone = (String) map.get("telephone");
        //校验验证码是否正确
        String validate = (String) map.get("validateCode");
        String validateCode = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        if (validate == null || !validateCode.equals(validate)) {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        //因为需要用到response请求去处理cookie，所以只能在controller里面进行部分业务的处理
        Member member = loginService.findVip(telephone);
        if (member == null) {
            //如果不是会员则直接注册为会员
            member = new Member();
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            loginService.register(member);
        }
        Cookie cookie = new Cookie("login_member_telephone", telephone);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
        String memberJson = JSON.toJSONString(member);
        jedisPool.getResource().setex("login_member_telephone", 60 * 30, memberJson);
        return new Result(true, MessageConstant.LOGIN_SUCCESS);

    }
}
