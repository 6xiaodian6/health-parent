package com.yd93.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yd93.constant.RedisConstant;
import com.yd93.dao.SetmealDao;
import com.yd93.entity.PageResult;
import com.yd93.entity.QueryPageBean;
import com.yd93.pojo.CheckGroup;
import com.yd93.pojo.Setmeal;
import com.yd93.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @description: 套餐管理
 * @author: yd93
 * @createTime: 2019-10-15 11:23:14
 **/
@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;

    /**
     * 添加套餐
     *
     * @param checkGroupIds 检查组Id数组
     * @param setmeal       添加套餐信息
     */
    @Transactional(rollbackFor = {})
    @Override
    public void add(Integer[] checkGroupIds, Setmeal setmeal) {
        //先查询该套餐是否存在，不存在再进行添加
        if (setmealDao.findCodeAndName(setmeal.getCode(), setmeal.getName()) > 0) {
            throw new RuntimeException("套餐在或名称已经存在");
        } else {
            setmealDao.add(setmeal);
            for (Integer checkGroupId : checkGroupIds) {
                setmealDao.addGroup(setmeal.getId(), checkGroupId);
            }
        }
        //将图片保存到redis中
        savePic2Redis(setmeal.getImg());
    }

    /**
     * 分页查询
     *
     * @param queryPageBean 分页查询信息
     * @return 返回分页展示信息
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<Setmeal> page = setmealDao.findPage();
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 查询检查组信息
     *
     * @return 返回检查组列表
     */
    @Override
    public List<CheckGroup> findGroup() {
        return setmealDao.findGroup();
    }

    /**
     * 手机端获取所有套餐
     *
     * @return 返回所有套餐信息
     */
    @Override
    public List<Setmeal> getSetmeal() {
        return setmealDao.getSetmeal();
    }

    /**
     * 根据id查询套餐信息
     * @param id 套餐id
     * @return 返回查询结果集
     */
    @Override
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }

    @Override
    public Setmeal findSetmealById(Integer id) {
        return setmealDao.findSetmealById(id);
    }

    @Autowired
    private JedisPool jedisPool;

    /**
     * 将图片保存到redis中
     *
     * @param pic 图片信息
     */
    private void savePic2Redis(String pic) {
        if (pic != null) {
            //使用redis进行数据存储之前需要判断存入的值是不是为空，如果为空就会失败报错
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, pic);
        }
    }
}
