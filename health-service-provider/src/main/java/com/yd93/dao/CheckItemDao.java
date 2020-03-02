package com.yd93.dao;

import com.github.pagehelper.Page;
import com.yd93.entity.QueryPageBean;
import com.yd93.pojo.CheckItem;

import java.util.List;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-07 21:25:15
 **/
public interface CheckItemDao {

    /**
     * 添加检查项
     *
     * @param checkItem 检查项内容
     */
    void add(CheckItem checkItem);

    /**
     * 通过项目编码检验是否存在
     *
     * @param code 项目编码
     * @return 返回已存在数量
     */
    int selectByCode(String code);

    /**
     * 通过项目名称检验是否存在
     *
     * @param name 项目名称
     * @return 返回已存在数量
     */
    int selectByName(String name);

    /**
     * 分页查询
     *
     * @param queryString 查询条件
     * @return 返回查询的结果集
     */
    Page<CheckItem> findPage(String queryString);

    /**
     * 根据id查询检查项
     *
     * @param checkItemId 检查项Id
     * @return 返回检查项信息
     */
    CheckItem findById(Integer checkItemId);

    /**
     * 查询检查项名字是否重复
     *
     * @param checkItem 检查项
     * @return 返回存在的个数
     */
    Integer findByIdAndName(CheckItem checkItem);

    /**
     * 保存更新的检查项内容
     *
     * @param checkItem 检查项信息
     */
    void edit(CheckItem checkItem);

    Integer delItem(Integer checkItemId);
}
