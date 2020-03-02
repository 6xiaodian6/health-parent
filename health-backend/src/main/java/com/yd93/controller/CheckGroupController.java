package com.yd93.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yd93.constant.MessageConstant;
import com.yd93.entity.PageResult;
import com.yd93.entity.QueryPageBean;
import com.yd93.entity.Result;
import com.yd93.exception.CheckGroupException;
import com.yd93.pojo.CheckGroup;
import com.yd93.pojo.CheckItem;
import com.yd93.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 检查组控制
 * @author: yd93
 * @createTime: 2019-10-11 19:21:33
 **/
@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/findCheckItem")
    public Result findCheckItem() {
        List<CheckItem> itemList = checkGroupService.findCheckItem();
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS,itemList);
    }

    /**
     * 添加检查组
     *
     * @param checkGroup 检查组信息
     * @return 返回添加结果
     */
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,@RequestParam("checkitemIds") Integer[] checkItemIds) {
        try {
            if (checkGroup.getCode() != null && checkGroup.getName() != null) {
                checkGroupService.add(checkGroup,checkItemIds);
                return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
            } else {
                return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL + "[检查组编码或名称不能为空]");
            }
        } catch (CheckGroupException e) {
            e.printStackTrace();
            //远程服务调用失败或添加数据失败，详细捕获异常可以自定义异常类进行单个的捕捉
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL + "[" + e.getMessage() + "]");
        }
    }

    /**
     * 分页查询检查组结果
     *
     * @return 返回检查组信息
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkGroupService.findPage(queryPageBean);
    }

    /**
     * 编辑检查组信息
     *
     * @param checkGroup 检查组信息
     * @return 返回编辑检查组结果
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup) {
        try {
            checkGroupService.edit(checkGroup);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL + "[" + e.getMessage() + "]");
        }
    }

    /**
     * 删除检查组记录
     *
     * @param checkGroupId 检查组ID
     * @return 返回删除结果
     */
    @RequestMapping("/delGroup")
    public Result delGroup(@RequestParam("id") Integer checkGroupId) {
        try {
            checkGroupService.delGroup(checkGroupId);
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (CheckGroupException e) {
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL + "[" + e.getMessage() + "]");
        }
    }
}
