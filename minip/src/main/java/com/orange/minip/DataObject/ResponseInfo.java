package com.orange.minip.DataObject;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class ResponseInfo {
    //返回状态码
    private Integer code;
    //返回文字信息
    private String msg;
    //返回JSONObject
    private JSONObject info;
    //返回活动的标题
    private String title;
    //返回活动的截止日期
    private String deadline;
}
