package com.yd93.jobs;

import com.yd93.constant.RedisConstant;
import com.yd93.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @description: 自动清理图片
 * @author: yd93
 * @createTime: 2019-10-16 14:19:45
 **/
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;

    public void clearImg() {
        //根据Redis中保存的两个set集合进行差值计算，获得垃圾图片集合名称
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if (set!=null){
            for (String picName : set) {
                //删除七牛云服务器上的图片
                QiniuUtils.deleteFileFromQiniu(picName);
                //从Redis集合中删除图片名称
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,picName);
            }
        }

    }
}
