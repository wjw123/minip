package com.orange.minip.Service.Impl;/*
 *@author orange
 *@version 1.0.0
 *@Description tableservice相关
 *@creatTime 2019年05月24日19:39:00
 */

import com.orange.minip.DataObject.CreatTable;
import com.orange.minip.Mapper.CreatTableMapper;
import com.orange.minip.Service.CreatTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatTableServiceImpl implements CreatTableService {

    @Autowired
    private CreatTableMapper creatTableMapper;

    /**
     * 保存table信息并返回tableId
     * @param creatTable
     * @return
     */
    @Override
    public int saveTable(CreatTable creatTable) {
        return creatTableMapper.savaTable(creatTable);
    }

    /**
     * 获取填写table的总人数
     * @param tableId
     * @return
     */
    @Override
    public int getCount(Integer tableId) {
        return creatTableMapper.getCount(tableId);
    }

    @Override
    public String getContent(Integer tableId) {
       return creatTableMapper.getTable(tableId);
    }
}
