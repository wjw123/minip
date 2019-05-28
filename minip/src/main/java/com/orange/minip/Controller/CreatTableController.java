package com.orange.minip.Controller;/*
 *@author orange
 *@version 1.0.0
 *@Description
 *@creatTime 2019年05月24日19:38:00
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.orange.minip.DataObject.CreatTable;
import com.orange.minip.DataObject.Response;
import com.orange.minip.Service.CreatTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/table")
public class CreatTableController {
    @Autowired
    private CreatTableService creatTableService;

    /**
     *  保存table信息并返回tableId
     * @param tableJson
     * @return
     */
    @RequestMapping(value = "/creattable",method = RequestMethod.POST)
    public Response saveTable(@RequestBody JSONObject tableJson){
        Response response=new Response();
        Map<String,Object>map=new HashMap<>();
        String tableTitle=tableJson.getString("tableTitle");
        String tableDeadline=tableJson.getString("tableDeadline");
        String tableCreatopenid=tableJson.getString("tableCreatopenid");
        String tableContent=tableJson.getString("tableContent");
        CreatTable creatTable=new CreatTable(tableTitle,tableDeadline,tableCreatopenid,tableContent);
        creatTableService.saveTable(creatTable);
        map.put("tableId",creatTable.getTableId());
        response.setCode(0);
        response.setMsg("创建成功");
        response.setObject(map);
        return response;
    }

    /**
     * 获取填写table的总人数
     * @param tableId
     * @return
     */
    @RequestMapping(value="/getCount",method = RequestMethod.GET)
    public Response getCount(Integer tableId){
       Response response=new Response();
       response.setCode(0);
       response.setMsg("获取成功");
       response.setObject(creatTableService.getCount(tableId));
       return response;
    }

    /**
     * 请求表格字段，此处可设置浏览量增加
     * @param tableId
     * @return
     */
    @RequestMapping(value = "/getContent",method = RequestMethod.POST)
    public Response getContent(Integer tableId){
        String contentString=creatTableService.getContent(tableId).substring(1,creatTableService.getContent(tableId).length()-1);
        String[] strings=contentString.split(",");
        JSONArray jsonArray=JSONArray.parseArray(JSON.toJSONString(Arrays.asList(strings)));
        Response response=new Response();
        response.setMsg("获取成功");
        response.setCode(0);
        response.setObject(jsonArray);
        return  response;
    }


    /***
     * 获取用户所创建的所有表信息
     * @param openId 用户的ID
     * @return
     */
    @RequestMapping(value = "/getAllCreattable",method = RequestMethod.GET)
    public Response getAllCreattable(Integer openId){
        Response response=new Response();
        response.setCode(0);
        response.setMsg("成功获取到用户所创建的所有表信息");
        response.setObject(creatTableService.getAllCreatetable(openId));
        return response;
    }

    /***
     * 获取到用户所参与的所有表信息
     * @param openId
     * @return
     */
    @RequestMapping(value = "/getAllParttable",method = RequestMethod.GET)
    public Response getpartTable(Integer openId){
        Response response=new Response();
        response.setCode(0);
        response.setMsg("成功获取到用户所参与的所有表信息");
        response.setObject(creatTableService.getAllParttable(openId));
        return response;
    }
}
