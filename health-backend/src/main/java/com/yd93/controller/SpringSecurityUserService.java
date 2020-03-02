package com.yd93.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yd93.pojo.Permission;
import com.yd93.pojo.Role;
import com.yd93.pojo.User;
import com.yd93.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-19 20:42:12
 **/
@Component
public class SpringSecurityUserService implements UserDetailsService {
    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户
        User user = userService.findByUsername(username);
        //获取角色
        Set<Role> roles = user.getRoles();
        //添加角色到授权里面
        List<GrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority(role.getKeyword()));
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }

        //参数里面的密码是验证的密码，也就是存入数据库的密码，这是和页面提交过来的用户名和密码进行比较的数据库密码，页面信息不在这里展示
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,user.getPassword(),list);
        return userDetails;
    }
}
