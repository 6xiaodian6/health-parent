package com.yd93.service;

import com.yd93.entity.PageResult;
import com.yd93.entity.QueryPageBean;
import com.yd93.pojo.CheckGroup;
import com.yd93.pojo.Setmeal;

import java.util.List;

/**
 * @description: 套餐管理
 * @author: yd93
 * @createTime: 2019-10-15 11:20:59
 **/
public interface SetmealService {
    /**
     * 添加套餐
     *
     * @param checkGroupIds 检查组Id数组
     * @param setmeal       添加套餐信息
     */
    void add(Integer[] checkGroupIds, Setmeal setmeal);

    /**
     * 分页查询
     *
     * @param queryPageBean 分页查询信息
     * @return 返回分页展示信息
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 查询检查组
     *
     * @return 返回检查组信息
     */
    List<CheckGroup> findGroup();

    /**
     * 获取所有套餐
     *
     * @return 返回所有套餐
     */
    List<Setmeal> getSetmeal();

    /**
     * 根据套餐id查询套餐信息包括检查组的检查项
     *
     * @param id 套餐id
     * @return 返回查询结果对象
     */
    Setmeal findById(Integer id);

    /**
     * 根据id查询套餐
     *
     * @param id 套餐id
     * @return 返回查询结果对象
     */
    Setmeal findSetmealById(Integer id);
}
