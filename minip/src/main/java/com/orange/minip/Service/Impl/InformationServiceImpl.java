package com.orange.minip.Service.Impl;/*
 *@author orange
 *@version 1.0.0
 *@Description TODO
 *@creatTime 2019年05月24日21:34:00
 */

import com.orange.minip.DataObject.Information;
import com.orange.minip.Mapper.InformationMapper;
import com.orange.minip.Service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationMapper  informationMapper;
    @Override
    public int saveInformation(Information information ) {
        return informationMapper.savaInfomation(information);
    }

    @Override
    public String getInfo(Integer tableId, String partOpenid) {
        return informationMapper.getInfo(tableId,partOpenid);
    }

    @Override
    public List<String> getInfos(Integer tableId) {
        return informationMapper.getInfos(tableId);
    }

}
