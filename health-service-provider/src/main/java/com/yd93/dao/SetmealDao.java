package com.yd93.dao;

import com.github.pagehelper.Page;
import com.yd93.pojo.CheckGroup;
import com.yd93.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-15 14:40:19
 **/
public interface SetmealDao {
    /**
     * 根据名字或编码查找套餐是否存在
     *
     * @param setmealCode 套餐编码
     * @param setmealName 套餐名称
     * @return 返回查询到的数量
     */
    Integer findCodeAndName(@Param("setmealCode") String setmealCode, @Param("setmealName") String setmealName);

    /**
     * 添加套餐
     *
     * @param setmeal 套餐参数
     */
    void add(Setmeal setmeal);

    /**
     * 添加套餐包含的检查组
     *
     * @param id           套餐id
     * @param checkGroupId 检查组id
     */
    void addGroup(@Param("id") Integer id, @Param("checkGroupId") Integer checkGroupId);

    /**
     * 分页查询
     *
     * @return 返回分页查询信息
     */
    Page<Setmeal> findPage();

    /**
     * 查询检查组
     *
     * @return 返回检查组信息
     */
    List<CheckGroup> findGroup();

    /**
     * 手机端获取所有套餐
     *
     * @return 返回套餐信息
     */
    List<Setmeal> getSetmeal();

    /**
     * 根据id查询套餐
     *
     * @param id 套餐id
     * @return 返回查询结果集
     */
    Setmeal findById(Integer id);

    /**
     * 根据id查询套餐信息
     * @param id 套餐id
     * @return 返回查询的套餐对象信息
     */
    Setmeal findSetmealById(Integer id);
}
