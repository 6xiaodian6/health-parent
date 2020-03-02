package com.yd93.dao;

import com.yd93.pojo.Menu;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-19 15:50:55
 **/
public interface MenuDao {
    /**
     * 返回动态菜单的列表集合
     * @return
     */
    List<Menu> getMenu();
}
