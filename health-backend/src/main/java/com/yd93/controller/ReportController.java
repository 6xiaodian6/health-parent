package com.yd93.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yd93.constant.MessageConstant;
import com.yd93.entity.Result;
import com.yd93.service.ReportService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: ${DESCRIPTION}
 * @author: yuandian
 * @createTime: 2019-10-21 14:25:56
 **/
@RestController
@RequestMapping("/report")
public class ReportController {
    @Reference
    private ReportService reportService;

    @RequestMapping("/getMemberReport")
    public Result getMemberReport() {
        try {
            Map<String, List<Object>> map = reportService.getMemberReport();
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS, map);
        } catch (Exception e) {
            return new Result(false, MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }
    }

    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport() {
        try {
            Map<String, List<? extends Object>> map = reportService.getSetmealReport();
            return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS, map);
        } catch (Exception e) {
            return new Result(false, MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }
    }

    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData() {
        try {
            Map<String, ?> map = reportService.getBusinessReportData();
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, map);
        } catch (Exception e) {
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }

    @RequestMapping("/exportBusinessReport")
    public Result exportBusinessReport(HttpServletRequest request, HttpServletResponse response) {
        String realPath = request.getSession().getServletContext().getRealPath("template") + File.separator + "report_template.xlsx";
        try {
            Map<String, ?> map = reportService.getBusinessReportData();
            XSSFWorkbook workbook = new XSSFWorkbook(realPath);
            XSSFSheet sheet = workbook.getSheetAt(0);
            sheet.getRow(2).getCell(5).setCellValue(map.get("reportDate").toString());
            sheet.getRow(4).getCell(5).setCellValue(map.get("todayNewMember").toString());
            sheet.getRow(4).getCell(7).setCellValue(map.get("totalMember").toString());

            sheet.getRow(5).getCell(5).setCellValue(map.get("thisWeekNewMember").toString());
            sheet.getRow(5).getCell(7).setCellValue(map.get("thisMonthNewMember").toString());

            sheet.getRow(7).getCell(5).setCellValue(map.get("todayOrderNumber").toString());
            sheet.getRow(7).getCell(7).setCellValue(map.get("todayVisitsNumber").toString());

            sheet.getRow(8).getCell(5).setCellValue(map.get("thisWeekOrderNumber").toString());
            sheet.getRow(8).getCell(7).setCellValue(map.get("thisWeekVisitsNumber").toString());

            sheet.getRow(9).getCell(5).setCellValue(map.get("thisMonthOrderNumber").toString());
            sheet.getRow(9).getCell(7).setCellValue(map.get("thisMonthVisitsNumber").toString());

            List<Map> hotSetmeal = (List<Map>) map.get("hotSetmeal");
            int i = 12;
            for (Map map1 : hotSetmeal) {
                sheet.getRow(i).getCell(4).setCellValue(map1.get("name").toString());
                sheet.getRow(i).getCell(5).setCellValue(map1.get("setmeal_count").toString());
                sheet.getRow(i).getCell(6).setCellValue(new DecimalFormat("#.00").format((((BigDecimal)(map1.get("proportion"))).doubleValue()*100)) +"%");
                sheet.getRow(i).getCell(7).setCellValue(map1.get("remark").toString());
                i++;
            }
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename=report.xlsx");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
            workbook.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }
}
