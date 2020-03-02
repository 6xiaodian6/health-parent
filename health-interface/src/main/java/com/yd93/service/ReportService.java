package com.yd93.service;

import java.util.List;
import java.util.Map;

/**
 * @description: ${DESCRIPTION}
 * @author: yuandian
 * @createTime: 2019-10-21 14:35:29
 **/
public interface ReportService {
    /**
     * 查询会员统计数据
     *
     * @return 返回会员数据
     */
    Map<String, List<Object>> getMemberReport();

    /**
     * 查询套餐统计信息
     *
     * @return 返回套餐统计信息
     */
    Map<String, List<? extends Object>> getSetmealReport();

    /**
     * 查询运营统计数据
     *
     * @return 返回list集合对象
     */
    Map<String, ?> getBusinessReportData();
}
