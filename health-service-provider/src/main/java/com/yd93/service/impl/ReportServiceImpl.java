package com.yd93.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yd93.dao.ReportDao;
import com.yd93.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: ${DESCRIPTION}
 * @author: yuandian
 * @createTime: 2019-10-21 14:40:54
 **/
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportDao reportDao;

    /**
     * 统计会员信息
     *
     * @return 返回统计结果的map集合
     */
    @Override
    public Map<String, List<Object>> getMemberReport() {
        //查询出来结果
        List<Map> maps = reportDao.getMemberReport();
        // 对结果进行封装，变成前台页面可以解析的内容
        List<Object> months = new ArrayList<>();
        List<Object> memberCount = new ArrayList<>();
        for (Map map : maps) {
            Object months1 = map.get("months");
            Object memberCount1 = map.get("memberCount");
            months.add(months1);
            memberCount.add(memberCount1);
        }
        Map<String, List<Object>> report = new HashMap<>();
        report.put("months", months);
        report.put("memberCount", memberCount);
        return report;
    }

    /**
     * 统计预约套餐占比
     *
     * @return
     */
    @Override
    public Map<String, List<? extends Object>> getSetmealReport() {
        List<Map> maps = reportDao.getSetmealReport();
        List<Object> setmealNames = new ArrayList<>();
        for (Map map : maps) {
            Object setmealNames1 = map.get("name");
            setmealNames.add(setmealNames1);
        }
        Map<String, List<? extends Object>> report = new HashMap<>();
        report.put("setmealNames", setmealNames);
        report.put("setmealCount", maps);
        return report;
    }

    /**
     * 查询运营统计数据
     *
     * @return 返回统计结果
     */

    @Override
    public Map<String, ?> getBusinessReportData() {
//        List<Map<String, ? extends Object>> report = new ArrayList<>();

        Calendar instance = Calendar.getInstance();
        //获取当天的日期
        String today = new SimpleDateFormat("yyyy-MM-dd").format(instance.getTime());
        //获取本周一的日期
        instance.set(Calendar.DAY_OF_WEEK, 2);
        String week = new SimpleDateFormat("yyyy-MM-dd").format(instance.getTime());
        //获取本月一号的日期
        instance.set(Calendar.DAY_OF_MONTH, 1);
        String month = new SimpleDateFormat("yyyy-MM-dd").format(instance.getTime());

        // 统计日期
        Map map = new HashMap();
        map.put("reportDate", today);
        //获取会员的统计数据
        map.put("todayNewMember", reportDao.getMemberCount(today));
        map.put("totalMember", reportDao.getMemberCount(null));
        map.put("thisWeekNewMember", reportDao.getMemberCount(week));
        map.put("thisMonthNewMember", reportDao.getMemberCount(month));
        // 预约数量（今日、本周、本月预约数），到诊数量（今日、本周、本月到诊数量），
        map.put("todayOrderNumber", reportDao.getOrderCount(today));
        map.put("todayVisitsNumber", reportDao.getVisitsNumber(today));
        map.put("thisWeekOrderNumber", reportDao.getOrderCount(week));
        map.put("thisWeekVisitsNumber", reportDao.getVisitsNumber(week));
        map.put("thisMonthOrderNumber", reportDao.getOrderCount(month));
        map.put("thisMonthVisitsNumber", reportDao.getVisitsNumber(month));

        // 热门套餐
        //将查询出来的热门套餐取出来，然后转换为json字符串，最后在添加进返回值结果的list集合中
        List<Map> setmealCount = reportDao.getHotSetmeal();
        map.put("hotSetmeal", setmealCount);
        return map;
    }
}
