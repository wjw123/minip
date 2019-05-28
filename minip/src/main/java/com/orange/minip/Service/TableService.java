package com.orange.minip.Service;/*
 *@author orange
 *@version 1.0.0
 *@Description table的service层相关操作
 *@creatTime 2019年05月24日17:32:00
 */

import com.orange.minip.DataObject.CreatTable;

import java.util.List;

public interface TableService {
    /**
     * 保存creattable相关数据
     * @param creatTable
     * @return
     */
    int saveTable(CreatTable creatTable);

    /**
     * 获取填写table总人数
     * @param tableId
     * @return
     */
    int getCount(Integer tableId);

    /***
     *  获取用户创建的所有的表信息
     * @param openId
     * @return
     */
    List<CreatTable>getAllCreatetable(Integer openId);


    /***
     * 获取用户所参与的所有表格
     * @param openId
     * @return
     */
    List<CreatTable>getAllParttable(Integer openId);
}
