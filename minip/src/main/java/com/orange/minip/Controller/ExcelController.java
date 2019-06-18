package com.orange.minip.Controller;/*
 *@author orange
 *@version 1.0.0
 *@Description
 *@creatTime 2019年05月30日13:22:00
 */

import com.orange.minip.DataObject.Response;
import com.orange.minip.Service.CreatTableService;
import com.orange.minip.Service.InformationService;
import com.orange.minip.Util.ExportExcelUtil;
import com.orange.minip.Util.GetExcelInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/excel")
public class ExcelController{
    @Autowired
    InformationService informationService;
    @Autowired
    CreatTableService creatTableService;
    @RequestMapping(value = "/getexcel",method = RequestMethod.GET)
    public Response getExcel(HttpServletResponse httpServletResponse,Integer tableId){
        List<String> stringList=informationService.getAllInfo(tableId);
        String excelHeader=creatTableService.getContent(tableId);
        TimeZone timeZone=TimeZone.getTimeZone("GMT+8:00");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmm");
        simpleDateFormat.setTimeZone(timeZone);
        String fileName=simpleDateFormat.format(new Date()).toString();
        Response response=new Response();
        try {
            ExportExcelUtil.exportExcel(httpServletResponse,fileName,
                    GetExcelInfoUtil.getExcelHeader(excelHeader),GetExcelInfoUtil.getExcelDataList(stringList));
        } catch (Exception e) {
            response.setCode(1);
            response.setMsg("生成excel失败");
            return  response;
        }
        response.setCode(0);
        response.setMsg("生成excel成功");
        return response;
    }
}