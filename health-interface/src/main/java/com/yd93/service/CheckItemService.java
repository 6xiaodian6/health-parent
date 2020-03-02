package com.yd93.service;

import com.yd93.entity.PageResult;
import com.yd93.entity.QueryPageBean;
import com.yd93.entity.Result;
import com.yd93.pojo.CheckItem;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-08 14:19:04
 **/
public interface CheckItemService {
    /**
     * 添加检查项
     *
     * @param checkItem 检查项信息
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
     * @param queryPageBean 分页项内容
     * @return 返回查询的结果集
     */
    PageResult findPage(QueryPageBean queryPageBean);

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
     * 保存更新检查项信息
     *
     * @param checkItem 检查项信息
     * @return 返回保存结果
     */
    Result edit(CheckItem checkItem);

    /**
     * 删除检查项记录
     *
     * @param checkItemId
     */
    void delItem(Integer checkItemId);
}
