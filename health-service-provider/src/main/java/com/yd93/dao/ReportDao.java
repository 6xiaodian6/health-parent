package com.yd93.dao;

import com.yd93.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Required;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: ${DESCRIPTION}
 * @author: yuandian
 * @createTime: 2019-10-21 14:43:25
 **/
public interface ReportDao {
    /**
     * 会员统计信息
     *
     * @return 返回统计的map集合
     */
    List<Map> getMemberReport();

    /**
     * 套餐占比统计数据
     *
     * @return 返回套餐占比数据集合
     */
    List<Map> getSetmealReport();

    /**
     * 统计会员数据
     *
     * @param date 统计日期
     * @return 返回统计结果
     */
    Integer getMemberCount(String date);

    /**
     * 统计预约信息
     *
     * @param date 统计日期
     * @return 返回统计结果
     */
    Integer getOrderCount(String date);

    /**
     * 获取热门套餐信息
     *
     * @return 返回热门套餐集合
     */
    List<Map> getHotSetmeal();

    /**
     * 预约到诊数
     *
     * @param date 统计日期
     * @return 返回到诊数
     */
    Integer getVisitsNumber(String date);
}
