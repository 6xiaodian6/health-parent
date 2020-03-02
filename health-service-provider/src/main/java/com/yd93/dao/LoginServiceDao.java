package com.yd93.dao;

import com.yd93.pojo.Member;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-19 18:53:07
 **/
public interface LoginServiceDao {
    /**
     * 根据手机号查询会员信息
     *
     * @param telephone 手机号
     * @return 返回会员
     */
    Member findVip(String telephone);

    /**
     * 注册会员
     *
     * @param member 会员信息
     */
    void register(Member member);
}
