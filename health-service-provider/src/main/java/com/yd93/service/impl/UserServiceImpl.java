package com.yd93.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yd93.dao.UserDao;
import com.yd93.pojo.User;
import com.yd93.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-19 21:19:43
 **/
@Service(interfaceClass = UserService.class)
@Transactional(rollbackFor = {})
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        if (username != null) {
            return userDao.findByUsername(username);
        }
        return null;
    }
}
