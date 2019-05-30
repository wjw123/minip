package com.orange.minip.Service;/*
 *@author orange
 *@version 1.0.0
 *@Description information相关service
 *@creatTime 2019年05月24日21:28:00
 */

import com.orange.minip.DataObject.Information;

import java.util.ArrayList;
import java.util.List;

public interface InformationService {
    /**
     * 保存information
     * @param information
     * @return
     */
    int saveInformation(Information information);

    /**
     * 获取一个表中的所有条information
     * @param tableId
     * @return
     */
    ArrayList<String> getAllInfo(Integer tableId);


}
