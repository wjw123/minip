package com.orange.minip.Controller;/*
 *@author orange
 *@version 1.0.0
 *@Description TODO
 *@creatTime 2019年05月24日21:38:00
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.orange.minip.DataObject.CreatTable;
import com.orange.minip.DataObject.Information;
import com.orange.minip.DataObject.Response;
import com.orange.minip.Service.CreatTableService;
import com.orange.minip.Service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/information")
public class InformationController {
    @Autowired
    private InformationService informationService;

    @Autowired
    private CreatTableService creatTableService;

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
     * 修改填表信息
     * @param informationJson
     * @return
     */
    @RequestMapping(value = "/updateinformation",method = RequestMethod.POST)
    public Response updateInformation(@RequestBody JSONObject informationJson){
        Integer tableId=Integer.valueOf(informationJson.getString("tableid"));
        String partOpenid=informationJson.getString("openId");

        String content=informationJson.getString("content");


        Information information=new Information(tableId,partOpenid,content.replace(" ",""));
        int result=informationService.updateInformation(information);

        Response response=new Response();
        response.setCode(0);
        response.setMsg("成功修改填表信息");
        response.setObject(Integer.valueOf(result));
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
        String Info=informationService.getInfo(tableId,partOpenid)==null?null:informationService.getInfo(tableId,partOpenid).substring(1,informationService.getInfo(tableId,partOpenid).length()-1);
        JSONObject js=new JSONObject(true);
        if(Info!=null){
            String []contentArray=Info.split(",");
            String content=creatTableService.getContent(tableId)==null?null:creatTableService.getContent(tableId).substring(1,creatTableService.getContent(tableId).length()-1);
            String[]contents=content.split(",");
            List<Map<String,String>>titleArray=new ArrayList<>();
            for(String con:contents){
                Map<String,String>map=new HashMap<>();
                map.put("name",con);
                titleArray.add(map);
            }
            js.put("titleArray",titleArray);
            js.put("contentArray",contentArray);
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
        JSONObject js=new JSONObject(true);

        //从数据库查值
        List<String> allInfo=informationService.getAllInfo(tableId);

        //从数据库查键
        String content=creatTableService.getContent(tableId)==null?null:creatTableService.getContent(tableId).substring(1,creatTableService.getContent(tableId).length()-1);

        //所有的键信息
        List<Map<String,String>>titleArray=new ArrayList<>();

        //所有的值信息
        List<List<String>>contentArray=new ArrayList<>();

        if(content!=null){
            //获取所有的值信息
            for(String info:allInfo){
                String[]infos=info.substring(1,info.length()-1).split(",");
                List<String>infosList=Arrays.asList(infos);
                contentArray.add(infosList);
            }

            //获取所有键信息
            String[]contents=content.split(",");
            for(String con:contents){
                Map<String,String>map=new HashMap<>();
                map.put("name",con);
                titleArray.add(map);
            }

            js.put("titleArray",titleArray);
            js.put("contentArray",contentArray);
        }

        Response response=new Response();
        response.setCode(0);
        response.setMsg("成功获取到所有填表信息");
        response.setObject(js);
        return  response;
    }
}
