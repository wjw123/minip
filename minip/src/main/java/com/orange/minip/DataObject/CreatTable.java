package com.orange.minip.DataObject;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreatTable implements Serializable {
    private Integer tableId;
    private String tableTitle;
    private String tableDeadline;
    private String tableContent;
    private String tableCreatopenid;
}
