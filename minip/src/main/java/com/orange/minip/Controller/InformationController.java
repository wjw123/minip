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
        JSONArray content=informationJson.getJSONArray("content");

        List<String>newContent=new ArrayList<>();

        String[] tablecontent=creatTableService.getContent(tableId).substring(1,creatTableService.getContent(tableId).length()-1).split(",");
        int i=0;
        for(Object contentmsg:content){
            newContent.add(tablecontent[i]+"="+contentmsg);
            i++;
        }

        Information information=new Information(tableId,partOpenid,String.valueOf(newContent).replace(" ",""));
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
        String[] Infos=Info.split(",");
        //存储键名
        List<Map<String,String>>titleArray=new ArrayList<>();
        //存储值名
        List<String>contentArray=new ArrayList<>();
        for(String info:Infos){
            Map<String,String>title=new HashMap<>();
            String[]infos=info.split("=");
            title.put("name",infos[0]);
            //存储值
            contentArray.add(infos[1]);
            //存储键名
            titleArray.add(title);
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
        List<String> allInfo=informationService.getAllInfo(tableId);
        JSONObject js=new JSONObject();

        //设立初次
        int i=0;
        //存储键名
        List<Map<String,String>>titleArray=new ArrayList<>();
        //存储值名
        List<List<String>>contentArray=new ArrayList<>();

        for(String Info:allInfo){
            List<String>content=new ArrayList<>();
            Info=Info.substring(1,Info.length()-1);
            String[]Infos=Info.split(",");

            for (String info:Infos){
                String[] infos=info.split("=");
                //加入键信息
                if(i==0) {
                    Map<String,String>title=new HashMap<>();
                    title.put("name",infos[0]);
                    titleArray.add(title);
                }
                //加入值信息
                content.add(infos[1]);
            }

            contentArray.add(content);
            //只在第一次的时候加上键名称，后面则直接给值
            i++;
        }
        js.put("titleArray",titleArray);
        js.put("contentArray",contentArray);
        Response response=new Response();
        response.setCode(0);
        response.setMsg("成功获取到所有填表信息");
        response.setObject(js);
        return  response;
    }
}
