package com.orange.minip.Util;/*
 *@author orange
 *@version 1.0.0
 *@Description 导出excel文件
 *@creatTime 2019年05月29日19:46:00
 */

import com.sun.deploy.net.URLEncoder;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ExportExcelUtil {
    public static <T> HSSFWorkbook exportExcel(HttpServletResponse response, String fileName,
                                          String[] excelHeader, ArrayList<ArrayList<String>> excelDataList) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(fileName+".xls","UTF-8"));
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFCellStyle titleStyle=workbook.createCellStyle();
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font titleFont=workbook.createFont();
        titleFont.setFontHeightInPoints((short)14);
        titleFont.setFontName("黑体");
        titleStyle.setFont(titleFont);

        HSSFSheet sheet=workbook.createSheet(fileName);
        HSSFRow row=sheet.createRow(0);
        HSSFCell cell00=row.createCell(0);
        cell00.setCellValue("序号");
        cell00.setCellStyle(titleStyle);
        sheet.autoSizeColumn(0);

        for(int i=0;i<excelHeader.length;i++){
            HSSFCell cell0=row.createCell(i+1);
            cell0.setCellValue(excelHeader[i]);
            cell0.setCellStyle(titleStyle);
            sheet.autoSizeColumn(i+1);
        }

        HSSFCellStyle dataStyle=workbook.createCellStyle();
        dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font dataFont=workbook.createFont();
        dataFont.setFontName("宋体");
        dataFont.setFontHeightInPoints((short)12);
        dataStyle.setFont(dataFont);

        for(int i=0;i<excelDataList.size();i++){
            row=sheet.createRow(i+1);
            HSSFCell sequenceCell=row.createCell(0);
            sequenceCell.setCellValue(i+1);
            sequenceCell.setCellStyle(titleStyle);
            sheet.autoSizeColumn(0);
            for(int j=0;j<excelDataList.get(i).size();j++){
                HSSFCell dataCell=row.createCell(j+1);
                dataCell.setCellValue(excelDataList.get(i).get(j));
                dataCell.setCellStyle(dataStyle);
                sheet.autoSizeColumn(j+1);
            }
        }
        OutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
        outputStream.close();
        return workbook;
    }
}
