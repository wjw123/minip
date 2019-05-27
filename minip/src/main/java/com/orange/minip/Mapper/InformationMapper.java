package com.orange.minip.Mapper;/*
 *@author orange
 *@version 1.0.0
 *@Description 与information相关的mapper
 *@creatTime 2019年05月24日11:45:00
 */

import com.alibaba.fastjson.JSONObject;
import com.orange.minip.DataObject.Information;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface InformationMapper {
    /**
     * 将除了info之外的信息存入information表中
     * @param tableId
     * @param partOpenid
     */
    @Insert("insert into information(table_id,part_openid) values (#{tableId},#{partOpenid})")
    @Options(flushCache=Options.FlushCachePolicy.TRUE,timeout = 10000,
            useGeneratedKeys =true,keyProperty = "infoId",keyColumn = "info_id")
    int savaInfomation(Integer tableId,String partOpenid);

    @Insert("update information set info=#{info} where info_id=#{infoId}")
    @Options(flushCache =Options.FlushCachePolicy.TRUE,timeout = 10000)
    void saveInfo(String info,Long infoId);
}
