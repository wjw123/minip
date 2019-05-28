package com.orange.minip.DataObject;

import lombok.Data;

import java.io.Serializable;

@Data
public class Information implements Serializable {
    private Long infoId;
    private String tableId;
    private String partOpenid;
    private String info;




}
