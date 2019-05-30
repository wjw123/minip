package com.orange.minip.Service;/*
 *@author orange
 *@version 1.0.0
 *@Description information相关service
 *@creatTime 2019年05月24日21:28:00
 */

import com.orange.minip.DataObject.Information;

import java.util.List;

public interface InformationService {
    /**
     * 保存information
     * @param information
     * @return
     */
    int saveInformation(Information information);


    /***
     * 获取用户的填表信息
     * @param tableId 表格ID
     * @param partOpenid 用户ID
     * @return
     */
    String getInfo(Integer tableId,String partOpenid);

    /***
     * 获取所有用户的填表信息
     * @param tableId 表格ID
     * @return
     */
    List<String> getInfos(Integer tableId);
}
