package com.yd93.service;

import com.yd93.entity.PageResult;
import com.yd93.entity.QueryPageBean;
import com.yd93.exception.CheckGroupException;
import com.yd93.pojo.CheckGroup;
import com.yd93.pojo.CheckItem;

import java.util.List;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-11 19:22:12
 **/
public interface CheckGroupService {
    /**
     * 获取所有检查项信息
     *
     * @return 返回检查项信息列表
     */
    List<CheckItem> findCheckItem();

    /**
     *
     * 添加检查组
     *
     * @param checkGroup   检查组信息
     * @param checkItemIds 检查项id数组
     * @throws CheckGroupException 自定义检查组异常
     */
    void add(CheckGroup checkGroup, Integer[] checkItemIds) throws CheckGroupException;

    /**
     * 分页查询检查组信息
     *
     * @param queryPageBean 分页查询组件内容
     * @return 返回分页查询结果
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 编辑检查组信息
     *
     * @param checkGroup 检查组信息
     */
    void edit(CheckGroup checkGroup);

    /**
     * 删除检查组信息
     *
     * @param checkGroupId 检查组ID
     * @exception CheckGroupException 检查组删除失败自定义异常
     */
    void delGroup(Integer checkGroupId) throws CheckGroupException;

}
