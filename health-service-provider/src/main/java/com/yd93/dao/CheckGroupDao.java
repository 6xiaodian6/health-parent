package com.yd93.dao;

import com.github.pagehelper.Page;
import com.yd93.pojo.CheckGroup;
import com.yd93.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-11 19:23:50
 **/
public interface CheckGroupDao {
    /**
     * 根据检查组编码和名称查询已经存在的数量
     *
     * @param checkGroup 检查组信息
     * @return 返回检查组符合条件的数量
     */
    Integer findCheckGroupByCodeAndName(CheckGroup checkGroup);

    /**
     * 添加检查组
     *
     * @param checkGroup 检查组信息
     * @return 返回检查组id
     */
    void add(CheckGroup checkGroup);

    /**
     * 添加检查组和检查项关联
     *
     * @param checkGroupId 检查组id
     * @param checkItemId 检查项id数组
     */
    void addGroupAndItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkItemId") Integer checkItemId);

    /**
     * 分页查询检查组
     *
     * @param queryString 查询条件
     * @return 返回分页查询结果
     */
    Page<CheckGroup> findPage(String queryString);

    /**
     * 根据检查组编码查询检查组存在的数量
     *
     * @param checkGroup 检查组信息
     * @return 返回符合条件的数量
     */
    Integer findCheckGroupByCode(CheckGroup checkGroup);

    /**
     * 更新检查组信息
     *
     * @param checkGroup 检查组信息
     */
    void edit(CheckGroup checkGroup);

    /**
     * 删除指定id的检查组
     *
     * @param checkGroupId 检查组id
     */
    void delGroup(Integer checkGroupId);

    /**
     * 查询所有检查项
     *
     * @return 返回检查项列表
     */
    List<CheckItem> findCheckItem();

}
