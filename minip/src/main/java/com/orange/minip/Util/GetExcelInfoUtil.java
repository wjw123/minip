package com.orange.minip.Util;/*
 *@author orange
 *@version 1.0.0
 *@Description 将多个info的信息合成一个嵌套list
 *@creatTime 2019年05月24日12:01:00
 */

import java.util.ArrayList;
import java.util.List;

public class GetExcelInfoUtil {
    public static ArrayList<ArrayList<String>> getExcelDataList(List<String> stringList){
        ArrayList<ArrayList<String>> allInfoList=new ArrayList<ArrayList<String>>(){};
        for(int i=0;i<stringList.size();i++){
            String string=stringList.get(i).substring(1,stringList.get(i).length()-1);
            String[] infoArray=string.split(",");
            ArrayList<String> infoList=new ArrayList<>();
            for(int j=0;j<infoArray.length;j++){
                infoList.add(infoArray[j]);
            }
            allInfoList.add(infoList);
        }
        return allInfoList;
    }
    public static String[] getExcelHeader(String stringHeader){
        String string=stringHeader.substring(1,stringHeader.length()-1);
        String[] excelHeader=string.split(",");
        return excelHeader;
    }
}
