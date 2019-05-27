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

@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationMapper  informationMapper;
    @Override
    public int saveInformation(Integer tableId,String partOpenid) {
        return informationMapper.savaInfomation(tableId,partOpenid);
    }

    @Override
    public void saveInfo(String info, Long infoId) {
        informationMapper.saveInfo(info,infoId);
    }
}
