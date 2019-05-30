package com.orange.minip.Mapper;/*
 *@author orange
 *@version 1.0.0
 *@Description 与information相关的mapper
 *@creatTime 2019年05月24日11:45:00
 */

import com.alibaba.fastjson.JSONObject;
import com.orange.minip.DataObject.Information;
import com.orange.minip.DataObject.ResponseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
@CacheNamespace(size = 512)
public interface InformationMapper {
    /**
     * 将信息存入information表中
     * @param information
     * @return
     */
    @Insert("insert into information(table_id,part_openid,info) values (#{tableId},#{partOpenid},#{info})")
    @Options(flushCache=Options.FlushCachePolicy.TRUE,timeout = 10000,
            useGeneratedKeys =true,keyProperty = "infoId",keyColumn = "info_id")
    int savaInfomation(Information information);


    /***
     * 获取用户的填表信息
     * @param tableId 表格ID
     * @param partOpenid 用户ID
     * @return
     */
    @Select("select info from information where table_id=#{tableId} and part_openid=#{partOpenid}")
    String getInfo(Integer tableId,String partOpenid);

    /***
     * 获取所有用户的填表信息
     * @param tableId 表格ID
     * @return
     */
    @Select("select info from information where table_id=#{tableId}")
    List<String> getInfos(Integer tableId);
}
