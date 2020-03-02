package com.yd93.service;

import com.yd93.pojo.Member;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-19 18:48:41
 **/
public interface LoginService {
    /**
     * 根据手机号查询会员信息是否存在
     *
     * @param telephone 手机号
     * @return 返回会员信息
     */
    Member findVip(String telephone);

    /**
     * 会员注册
     *
     * @param member 会员信息
     */
    void register(Member member);
}
