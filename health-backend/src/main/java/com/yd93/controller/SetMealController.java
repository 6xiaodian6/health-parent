package com.yd93.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yd93.constant.MessageConstant;
import com.yd93.constant.RedisConstant;
import com.yd93.entity.PageResult;
import com.yd93.entity.QueryPageBean;
import com.yd93.entity.Result;
import com.yd93.pojo.CheckGroup;
import com.yd93.pojo.Setmeal;
import com.yd93.service.SetmealService;
import com.yd93.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.UUID;

/**
 * @description: 套餐管理
 * @author: yd93
 * @createTime: 2019-10-15 09:00:22
 **/
@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    @Reference
    private SetmealService setmealService;
    @Autowired
    private JedisPool jedisPool;

    /**
     * 添加套餐信息,注意一件事：在post请求中必须先出现@RequestBody，然后在出现@RequestParam，否则就会出现不稳定的情况，就是参数不对，从而报400错误
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, @RequestParam("id") Integer[] checkGroupIds) {
        if (setmeal != null && checkGroupIds != null && checkGroupIds.length > 0) {
            try {
                setmealService.add(checkGroupIds, setmeal);
                return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
            } catch (Exception e) {
                return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
            }
        } else {
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL + "[套餐名称和编码以及分组不能为空]");
        }
    }

    /**
     * 分页查询
     *
     * @param queryPageBean 查询参数
     * @return 返回分页结果
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return setmealService.findPage(queryPageBean);
    }

    /**
     * 检查组查询
     *
     * @return 返回检查组结果
     */
    @RequestMapping("/findGroup")
    public List<CheckGroup> findGroup() {
        return setmealService.findGroup();
    }

    /**
     * 图片上传到七牛云
     *
     * @param imgFile 图片名称
     * @return 返回上传结果
     */

    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        try {
            String originalFilename = imgFile.getOriginalFilename();
            assert originalFilename != null;
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取文件后缀
            String suffix = originalFilename.substring(lastIndexOf - 1);
            //产生随机文件，防止文件名称相同被覆盖
            String fileName = UUID.randomUUID().toString() + suffix;
            QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
            //图片上传成功
            Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);
            //将上传的图片名称存入redis中，基于redis的Set集合存储
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
            return result;
        } catch (Exception e) {
            //图片上传失败
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }
}
