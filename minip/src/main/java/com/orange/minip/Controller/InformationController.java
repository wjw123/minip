package com.orange.minip.Controller;/*
 *@author orange
 *@version 1.0.0
 *@Description TODO
 *@creatTime 2019年05月24日21:38:00
 */

import com.alibaba.fastjson.JSON;
import com.orange.minip.DataObject.Response;
import com.orange.minip.Service.InformationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/information")
public class InformationController {
    @Autowired
    private InformationService informationService;

    /**
     * 保存除info之外的信息
     * @param tableId
     * @param partOpenid
     * @return info_id
     */
    @RequestMapping(value="/save information",method = RequestMethod.POST)
    public Response saveInformation(Integer tableId, String partOpenid){
        Response response=new Response();
        response.setCode(0);
        response.setMsg("保存成功");
        Map<String,Object> map=new HashMap<>();
        map.put("info_id",informationService.saveInformation(tableId,partOpenid));
        response.setObject(map);
        return response;
    }

    /**
     * 保存info
     * @param jsonObject
     * @param infoId
     * @return
     */
    @RequestMapping(value = "/saveinfo",method = RequestMethod.POST)
    public Response saveInfo(com.alibaba.fastjson.JSONObject jsonObject,Long infoId){
        String info= JSON.toJSONString(jsonObject);
        informationService.saveInfo(info,infoId);
        Response response=new Response();
        response.setCode(0);
        response.setMsg("info保存成功");
        return response;
    }
}
