package com.orange.minip.Mapper;

import com.orange.minip.DataObject.CreatTable;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
 *@author orange
 *@version 1.0.0
 *@Description 将表格相关信息保存到数据库
 *@creatTime 2019年05月23日19:52:00
 */
@Mapper
@CacheNamespace(size=512)//定义在该命名空间内使用内置缓存，最大值为512个对象引用，
//缓存内省刷新时间为默认3600000ms，线程安全
public interface CreatTableMapper {

    /**
     * 保存table相关数据:标题，截止日期，发起人openid,表格信息，
     * @param creatTable
     * @return
     */
    @Insert("insert into creat_table(table_title,table_deadline,table_creatopenid,table_content) " +
            "values(#{tableTitle},#{tableDeadline},#{tableCreatopenid},#{tableContent})")
    @Options(flushCache = Options.FlushCachePolicy.TRUE,timeout =10000,
            useGeneratedKeys = true, keyProperty = "tableId",keyColumn="table_id")
    int savaTable(CreatTable creatTable);

    /**
     * 获取某一个table填写的总人数
     * @param tableId
     * @return
     */
    @Select("select count(*) from information where table_id=#{tableId}")
    @Options(flushCache = Options.FlushCachePolicy.TRUE,timeout=10000)
    int getCount(Integer tableId);

    /***
     * 获取某一个用户所创建的所有表信息
     * @param tableCreatopenid 创建用户信息
     * @return
     */
    @Select("select * from creattable where table_creatopenid=#{openId}")
    @Options(flushCache = Options.FlushCachePolicy.TRUE,timeout=10000)
    //需要设置Results注解来实现字段名和属性名的一一对应
    @Results({@Result(property = "tableId", column = "table_id", javaType=Integer.class),
            @Result(property = "tableTitle", column = "table_title",javaType = String.class),
            @Result(property = "tableDeadline", column = "table_deadline",javaType = String.class),
            @Result(property = "tableCreatopenid", column = "table_creatopenid",javaType = String.class),
            @Result(property = "tableContent", column = "table_content",javaType = String.class)})
    List<CreatTable>getAllCreateTable(String tableCreatopenid);


    /***
     * 获取某一用户所参与所有的表的信息
     * @param tableCreatopenid 参与用户的OpenId
     * @return
     */
    @Select("select * from creattable where table_id in (select table_id from information where part_openid=#{openid})")
    @Options(flushCache = Options.FlushCachePolicy.TRUE,timeout=10000)
    //需要设置Results注解来实现字段名和属性名的一一对应
    @Results({@Result(property = "tableId", column = "table_id", javaType=Integer.class),
            @Result(property = "tableTitle", column = "table_title",javaType = String.class),
            @Result(property = "tableDeadline", column = "table_deadline",javaType = String.class),
            @Result(property = "tableCreatopenid", column = "table_creatopenid",javaType = String.class),
            @Result(property = "tableContent", column = "table_content",javaType = String.class)})
    List<CreatTable>getAllPartTable(String tableCreatopenid);


    @Select("select table_content as 'tableContent' from creat_table where table_id=#{tableId}")
    String getTable(Integer tableId);


}
