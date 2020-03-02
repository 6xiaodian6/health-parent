package com.yd93.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yd93.constant.MessageConstant;
import com.yd93.entity.PageResult;
import com.yd93.entity.QueryPageBean;
import com.yd93.entity.Result;
import com.yd93.pojo.CheckItem;
import com.yd93.service.CheckItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-09 09:21:41
 **/
@Controller
@RequestMapping("/checkItem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;

    /**
     * 添加数据
     *
     * @param checkItem 数据项内容
     * @return 返回添加结果字符串
     */
    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")
    @RequestMapping("/add")
    @ResponseBody
    public Result add(@RequestBody CheckItem checkItem) {
        // 添加的步骤是先判断添加的内容是不是符合逻辑，比如唯一约束、重复值等等,都符合的时候再进行添加操作，别忘了还有事务的处理
        // 数据在数据库中进行校验，分别是编号和名字

        int codeCount = checkItemService.selectByCode(checkItem.getCode());
        int nameCount = checkItemService.selectByName(checkItem.getName());
        if (codeCount > 0 || nameCount > 0) {
            // 如果数据库中已经存在，就直接不符合条件了
            if (codeCount > 0 && nameCount > 0) {
                // 设置错误信息为名字和编码都错误
                return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL + "[项目编码和项目名称已存在]");
            }
            if (codeCount > 0) {
                // 设置错误信息为编码错误
                return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL + "[项目编码已存在]");
            }
            if (nameCount > 0) {
                // 设置错误信息为名字错误
                return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL + "[项目名称已存在]");
            }
        } else {
            // 符合插入的条件，执行数据插入,
            // 添加的数据没有进行合法性校验，比如年龄是一个数字-数字的形式，但这里是直接插入的字符串，后续在处理，不想处理就算了
            checkItemService.add(checkItem);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 分页查询
     *
     * @param queryPageBean 分页项内容
     * @return 返回查询的结果集
     */
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkItemService.findPage(queryPageBean);
    }

    /**
     * 通过id查询检查项内容
     *
     * @param checkItemId 检查项Id
     * @return 返回检查项内容
     */
    @RequestMapping("/findById")
    @ResponseBody
    public CheckItem findById(@RequestParam("id") Integer checkItemId) {
        return checkItemService.findById(checkItemId);
    }

    /**
     * 保存检查项信息
     *
     * @param checkItem 检查项信息
     * @return 返回保存结果
     */
    @PreAuthorize("hasAuthority('CHECKITEM_EDIT')")
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.edit(checkItem);
        } catch (RuntimeException e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    /**
     * 删除检查项信息
     *
     * @param checkItemId 检查项id
     * @return 返回删除结果
     */
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")
    @RequestMapping("/delItem")
    @ResponseBody
    public Result delItem(@RequestParam("id") Integer checkItemId) {
        try {
            checkItemService.delItem(checkItemId);
        } catch (RuntimeException e) {
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL+"["+e.getMessage()+"]");
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }
}
