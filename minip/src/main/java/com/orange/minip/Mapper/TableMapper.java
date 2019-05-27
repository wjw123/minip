package com.orange.minip.Mapper;

import com.orange.minip.DataObject.CreatTable;
import org.apache.ibatis.annotations.*;

/*
 *@author orange
 *@version 1.0.0
 *@Description 将表格相关信息保存到数据库
 *@creatTime 2019年05月23日19:52:00
 */
@Mapper
@CacheNamespace(size=512)//定义在该命名空间内使用内置缓存，最大值为512个对象引用，
//缓存内省刷新时间为默认3600000ms，线程安全
public interface TableMapper {
    /**
     * 保存table相关数据:标题，截止日期，发起人openid，
     * @param creatTable
     * @return
     */
    @Insert("insert into creattable(table_title,table_deadline,table_creatopenid) " +
            "values(#{tableTitle},#{tableDeadline},#{tableCreatopenid})")
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

}
