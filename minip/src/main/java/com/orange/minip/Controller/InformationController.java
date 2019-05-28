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

import java.util.HashMap;
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
}
