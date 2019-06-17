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

import java.util.List;

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

    @Override

    public CreatTable getCreateTableById(Integer tableId) {
        CreatTable creatTable=creatTableMapper.getCreateTableById(tableId);
        creatTable.setTableContent(creatTable.getTableContent().substring(1,creatTable.getTableContent().length()-1));
        return creatTable;
    }

    @Override
    public int updateTable(CreatTable creatTable) {
        return creatTableMapper.updateTable(creatTable);
    }

    @Override
    public int deleteTable(Integer tableId) {
        return creatTableMapper.deleteTable(tableId);
    }

    /***
     * 获取用户所创建的所有表信息
     * @param tableCreatopenid
     * @return
     */
    @Override
    public List<CreatTable> getAllCreatetable(String tableCreatopenid){
        List<CreatTable> creatTable=creatTableMapper.getAllCreateTable(tableCreatopenid);
        for (CreatTable creatTable1:creatTable){
            creatTable1.setTableContent(creatTable1.getTableContent().substring(1,creatTable1.getTableContent().length()-1));
        }
        return creatTable;
    }

    @Override
    /***
     * 获取用户所参与的所有表信息
     * @param tableCreatopenid
     * @return
     */
    public List<CreatTable> getAllParttable(String tableCreatopenid) {
        List<CreatTable> creatTable=creatTableMapper.getAllPartTable(tableCreatopenid);
        for (CreatTable creatTable1:creatTable) {
            creatTable1.setTableContent(creatTable1.getTableContent().substring(1,creatTable1.getTableContent().length()-1));
        }
        return creatTable;
    }
}
