package com.yd93.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yd93.dao.MenuDao;
import com.yd93.pojo.Menu;
import com.yd93.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @description: ${DESCRIPTION}
 * @author: yuandian
 * @createTime: 2019-10-21 14:57:27
 **/
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    /**
     * 获取动态菜单列表的map集合
     *
     * @return 返回菜单列表
     */
    @Override
    public List<Map> getMenu() {
        List<Menu> menuList = menuDao.getMenu();
        return findMenu(menuList, null);
    }

    private List<Map> findMenu(List<Menu> menuList, Integer nextId) {
        List<Map> list = new ArrayList<>();
        for (Menu menu : menuList) {
            if (nextId == null) {
                nextId = 0;
            }
            if (menu.getParentMenuId() == null) {
                menu.setParentMenuId(0);
            }
            if (menu.getParentMenuId().equals(nextId)) {
                Map map = new HashMap();
                map.put("path", menu.getPath());
                map.put("title", menu.getName());
                map.put("icon", menu.getIcon());
                map.put("linkUrl", menu.getLinkUrl());
                map.put("children", findMenu(menuList, menu.getId()));
                list.add(map);
            }
        }
        return list;
    }

}
