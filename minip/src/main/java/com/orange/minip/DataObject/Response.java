package com.orange.minip.DataObject;/*
 *@author orange
 *@version 1.0.0
 *@Description TODO
 *@creatTime 2019年05月24日20:58:00
 */

import lombok.Data;

@Data
public class Response {
    private Integer code;
    private String msg;
    private Object object;
}
