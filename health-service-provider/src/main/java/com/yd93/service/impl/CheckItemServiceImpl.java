package com.yd93.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yd93.constant.MessageConstant;
import com.yd93.dao.CheckItemDao;
import com.yd93.entity.PageResult;
import com.yd93.entity.QueryPageBean;
import com.yd93.entity.Result;
import com.yd93.pojo.CheckItem;
import com.yd93.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-08 14:23:05
 **/
//测试一下看看不加interfaceClass = CheckItemService.class在处理事务的时候会出现什么问题
@Service
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 添加检查项
     *
     * @param checkItem 检查项
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    /**
     * 通过项目编码检验是否存在
     *
     * @param code 项目编码
     * @return 返回已存在数量
     */
    @Override
    public int selectByCode(String code) {
        return checkItemDao.selectByCode(code);
    }

    /**
     * 通过项目名称检验是否存在
     *
     * @param name 项目名称
     * @return 返回已存在数量
     */
    @Override
    public int selectByName(String name) {
        return checkItemDao.selectByName(name);
    }

    /**
     * 分页查询
     *
     * @param queryPageBean 分页项内容
     * @return 返回查询的结果集
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //用分页助手实现分页查询
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<CheckItem> page;
        if (queryPageBean.getQueryString() != null) {
            page = checkItemDao.findPage("%" + queryPageBean.getQueryString() + "%");
        } else {
            page = checkItemDao.findPage("%%");
        }
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根据id查询检查项
     *
     * @param checkItemId 检查项Id
     * @return 返回检查项信息
     */
    @Override
    public CheckItem findById(Integer checkItemId) {
        return checkItemDao.findById(checkItemId);
    }

    /**
     * 查询检查项名字是否重复
     *
     * @param checkItem 检查项
     * @return 返回存在的个数
     */
    @Override
    public Integer findByIdAndName(CheckItem checkItem) {
        return checkItemDao.findByIdAndName(checkItem);
    }

    /**
     * @param checkItem 检查项信息
     * @return 返回保存的结果
     */
    @Override
    public Result edit(CheckItem checkItem) {
        //先判断检查项id和检查项名字是否存在重复的，如果存在就抛出异常，否则就执行保存操作，并返回结果
        if (findByIdAndName(checkItem) > 0) {
            throw new RuntimeException("检查项名字已经存在");
        } else {
            checkItemDao.edit(checkItem);
            return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
        }
    }

    /**
     * 删除检查项记录
     * delete语句没有resultType属性，但是可以直接返回影响的行数，只需要在方法返回值进行接收即可
     *
     * @param checkItemId 检查项id
     */
    @Override
    public void delItem(Integer checkItemId) {
        //页面长时间未刷新，中途其他人操作删除之后，在执行该操作就会出现问题，虽然结果都一样但还是写一下

        try {
            int count = checkItemDao.delItem(checkItemId);
            if (count <= 0) {
                //这个异常最好设置为自定义异常，捕捉的时候比较好捕捉，否则异常信息直接就成了最下面的那个异常了
                throw new RuntimeException("当前数据不存在或已经被删除");
            }
        } catch (Exception e) {
            throw new RuntimeException("当前数据和检查组有关联，无法删除");
        }

    }
}
