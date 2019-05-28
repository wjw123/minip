package com.orange.minip.DataObject;

import lombok.Data;

@Data
public class CreatTable {
    private Integer tableId;
    private String tableTitle;
    private String tableDeadline;
    private String tableCreatopenid;
    private String tableContent;

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
