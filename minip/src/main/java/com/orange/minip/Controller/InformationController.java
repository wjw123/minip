package com.orange.minip.Controller;/*
 *@author orange
 *@version 1.0.0
 *@Description TODO
 *@creatTime 2019年05月24日21:38:00
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orange.minip.DataObject.CreatTable;
import com.orange.minip.DataObject.Information;
import com.orange.minip.DataObject.Response;
import com.orange.minip.Service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/information")
public class InformationController {
    @Autowired
    private InformationService informationService;

    /**
     * 保存信息
     * @param informationJson
     * @return
     */
    @RequestMapping(value="/saveinformation",method = RequestMethod.POST)
    public Response saveInformation(@RequestBody JSONObject informationJson){
        Integer tableId=informationJson.getInteger("tableId");
        String partOpenid=informationJson.getString("partOpenid");
        String info=informationJson.getString("info");
        Information information=new Information(tableId,partOpenid,info);
        informationService.saveInformation(information);
        Response response=new Response();
        response.setCode(0);
        response.setMsg("保存成功");
        return response;
    }

    /***
     * 获取用户填表信息
     * @param tableId
     * @param partOpenid
     * @return
     */
    @RequestMapping(value = "/getinformation",method = RequestMethod.GET)
    public Response getInformation(Integer tableId,String partOpenid){
        String Info=informationService.getInfo(tableId,partOpenid).substring(1,informationService.getInfo(tableId,partOpenid).length()-1);
        String[] Infos=Info.split(",");
        JSONObject js=new JSONObject();
        for(String info:Infos){
            String[]infos=info.split("=");
            js.put(infos[0],infos[1]);
        }
        Response response=new Response();
        response.setCode(0);
        response.setMsg("成功获取到所填写的表格信息");
        response.setObject(js);
        return response;
    }

    /***
     * 获取所有的填表信息
     * @param tableId 表格ID
     * @return
     */
    @RequestMapping(value = "/getinformations",method = RequestMethod.GET)
    public Response getInformations(Integer tableId){
        List<String> allInfo=informationService.getAllInfo(tableId);
        List<Map<String,Object>>lists=new ArrayList<>();
        for(String Info:allInfo){
            Info=Info.substring(1,Info.length()-1);
            String[]Infos=Info.split(",");
            Map<String,Object>map=new HashMap<>();
            for (String info:Infos){
                String[] infos=info.split("=");
                map.put(infos[0],infos[1]);
            }
            lists.add(map);
        }
        Response response=new Response();
        response.setCode(0);
        response.setMsg("成功获取到所有填表信息");
        response.setObject(lists);
        return  response;
    }
}
