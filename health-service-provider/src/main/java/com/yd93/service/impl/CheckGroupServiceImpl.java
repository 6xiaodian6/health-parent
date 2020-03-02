package com.yd93.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yd93.dao.CheckGroupDao;
import com.yd93.entity.PageResult;
import com.yd93.entity.QueryPageBean;
import com.yd93.exception.CheckGroupException;
import com.yd93.pojo.CheckGroup;
import com.yd93.pojo.CheckItem;
import com.yd93.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-11 19:22:43
 **/
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    CheckGroupDao checkGroupDao;

    /**
     * 获取所有检查项信息
     *
     * @return 返回检查项信息列表
     */
    @Override
    public List<CheckItem> findCheckItem() {
        return checkGroupDao.findCheckItem();
    }

    /**
     * 添加检查组，添加和删除语句执行之后的返回结果是影响的行数，而不是生成的主键，
     * 对于插入语句来说，想要获取插入之后的主键值，步骤有两步，一、先配置mapper文件，第二、根据传进来的实体类参数获取主键就可以了
     *
     * @param checkGroup 检查组信息
     */
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkItemIds) throws CheckGroupException {
        //添加检查组信息需要先判断是否已经存在，如果存在就不能添加,这里可以尝试着去写一个异常类，处理不同的异常问题
        if (checkGroupDao.findCheckGroupByCodeAndName(checkGroup) > 0) {
            throw new CheckGroupException("检查组名称或编号已经存在");
        }
        //抛出异常之后代码就不会再继续向下走了，如果不抛异常就继续向下走
        //如果检查组的code和name都不存在可以进行添加
        checkGroupDao.add(checkGroup);
        Integer checkGroupId = checkGroup.getId();
        for (Integer checkItemId : checkItemIds) {
            checkGroupDao.addGroupAndItem(checkGroupId, checkItemId);
        }

    }

    /**
     * 分页查询检查组信息
     *
     * @return 返回分页查询结果
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //首先调用分页插件里面的方法进行分页就不用在mybatis里面写分页
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<CheckGroup> page = checkGroupDao.findPage(queryPageBean.getQueryString() == null ? "%" : "%" + queryPageBean.getQueryString() + "%");
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 编辑检查组信息
     *
     * @param checkGroup 检查组信息
     */
    @Override
    public void edit(CheckGroup checkGroup) {
        //保存编辑信息之前先进行检查组信息判断是否存在，不存在才可以进行保存
        Integer nameCount = checkGroupDao.findCheckGroupByCode(checkGroup);
        if (nameCount > 0) {
            try {
                throw new CheckGroupException("检查组名称已存在，请换一个");
            } catch (CheckGroupException e) {
                e.printStackTrace();
            }
        }
        checkGroupDao.edit(checkGroup);
    }

    /**
     * 删除检查组信息
     *
     * @param checkGroupId 检查组ID
     */
    @Override
    public void delGroup(Integer checkGroupId) throws CheckGroupException {
        try {
            checkGroupDao.delGroup(checkGroupId);
        } catch (Exception e) {
            throw new CheckGroupException("删除失败，检查组和检查项存在关联");
        }
    }
}
