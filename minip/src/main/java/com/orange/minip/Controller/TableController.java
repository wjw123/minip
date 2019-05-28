package com.orange.minip.Controller;/*
 *@author orange
 *@version 1.0.0
 *@Description
 *@creatTime 2019年05月24日19:38:00
 */
import com.orange.minip.DataObject.CreatTable;
import com.orange.minip.DataObject.Response;
import com.orange.minip.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/table")
public class TableController {
    @Autowired
    private TableService tableService;

    /**
     * 保存table信息并返回tableId
     * @param creatTable
     * @return
     */
    @RequestMapping(value = "/creattable",method = RequestMethod.GET)
    public Response saveTable(CreatTable creatTable){
        Response response=new Response();
        Map<String,Object> map=new HashMap<>();
        map.put("tableId",tableService.saveTable(creatTable));
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
       response.setObject(tableService.getCount(tableId));
       return response;
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
        response.setObject(tableService.getAllCreatetable(openId));
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
        response.setObject(tableService.getAllParttable(openId));
        return response;
    }
}
