package com.yd93.dao;

import com.yd93.pojo.User;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-19 21:22:01
 **/
public interface UserDao {
    /**
     * 根据用户名查找用户信息
     *
     * @param username 用户名
     * @return 返回用户信息
     */
    User findByUsername(String username);
}
