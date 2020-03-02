package com.yd93.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yd93.dao.LoginServiceDao;
import com.yd93.pojo.Member;
import com.yd93.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-19 18:51:01
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginServiceDao loginServiceDao;
    /**
     * 根据手机号查询会员信息
     *
     * @param telephone 手机号
     * @return 返回会员信息
     */
    @Override
    public Member findVip(String telephone) {
        return loginServiceDao.findVip(telephone) ;
    }

    /**
     * 注册会员
     *
     * @param member 会员信息
     */
    @Override
    public void register(Member member) {
        loginServiceDao.register(member);
    }
}
