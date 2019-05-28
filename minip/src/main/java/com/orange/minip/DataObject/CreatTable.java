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

    public CreatTable(String tableTitle, String tableDeadline, String tableCreatopenid, String tableContent) {
        this.tableTitle = tableTitle;
        this.tableDeadline = tableDeadline;
        this.tableCreatopenid = tableCreatopenid;
        this.tableContent = tableContent;
    }
    public CreatTable(){

    }

    public CreatTable(Integer tableId, String tableTitle, String tableDeadline, String tableCreatopenid, String tableContent) {
        this.tableId = tableId;
        this.tableTitle = tableTitle;
        this.tableDeadline = tableDeadline;
        this.tableCreatopenid = tableCreatopenid;
        this.tableContent = tableContent;
    }
}
