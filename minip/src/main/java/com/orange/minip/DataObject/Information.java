package com.orange.minip.DataObject;

import lombok.Data;

import java.io.Serializable;

@Data
public class Information implements Serializable {
    private Long infoId;
    private Integer tableId;
    private String partOpenid;
    private String info;

    public Information(Integer tableId, String partOpenid, String info){
        this.tableId = tableId;
        this.partOpenid = partOpenid;
        this.info = info;
    }
}
