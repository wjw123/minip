package com.orange.minip.Mapper;/*
 *@author orange
 *@version 1.0.0
 *@Description 与information相关的mapper
 *@creatTime 2019年05月24日11:45:00
 */


import com.orange.minip.DataObject.Information;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
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

    /****
     * 修改Information中的信息
     * @param information
     * @return
     */
    @Update("update information set info=#{info} where table_id=#{tableId} and part_openid=#{partOpenid}")
    int updateInfomation(Information information);


    /**
     * 获取一个表中所有条的information
     * @param tableId
     * @return
     */
    @Select("select info from information where table_id=#{tableId}")
    ArrayList<String> findInfoByTableId(Integer tableId);


    /***
     * 获取某一个用户的填表信息
     * @param tableId 表格ID
     * @param partOpenid 用户ID
     * @return
     */
    @Select("select info from information where table_id=#{tableId} and part_openid=#{partOpenid}")
    String getInfo(Integer tableId,String partOpenid);

    /***
     * 根据表格ID删除对应填写的信息
     * @param tableId
     * @return
     */
    @Delete("delete from information where table_id=#{tableId}")
    int deleteInfoByTable(Integer tableId);

    /***
     * 获取是否有数据
     * @param tableId
     * @param partOpenid
     * @return
     */
    @Select("select count(*) from information where table_id=#{tableId} and part_openid=#{partOpenid}")
    int getCount(Integer tableId,String partOpenid);

}
