package com.yd93.service;

import java.util.List;
import java.util.Map;

/**
 * @description: ${DESCRIPTION}
 * @author: yuandian
 * @createTime: 2019-10-21 14:56:39
 **/
public interface MenuService {
    /**
     * 获取动态菜单列表
     *
     * @return 返回菜单列表的map集合
     */
    List<Map> getMenu();

}
