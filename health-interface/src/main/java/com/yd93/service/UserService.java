package com.yd93.service;

import com.yd93.pojo.User;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-19 21:17:36
 **/
public interface UserService {
    /**
     * 根据用户名查找用户包含角色权限信息
     *
     * @param username 用户名
     * @return 返回查询结果
     */
    User findByUsername(String username);
}
