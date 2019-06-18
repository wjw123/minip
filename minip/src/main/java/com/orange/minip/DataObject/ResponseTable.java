package com.orange.minip.DataObject;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ResponseTable{
    private Integer tableId;
    private String tableTitle;
    private String tableDeadline;
    private List<Map<String,String>> tableContent;
    private String tableCreatopenid;

    public ResponseTable(String tableTitle, String tableDeadline, String tableCreatopenid, List<Map<String,String>> tableContent) {
        this.tableTitle = tableTitle;
        this.tableDeadline = tableDeadline;
        this.tableCreatopenid = tableCreatopenid;
        this.tableContent = tableContent;
    }

    public ResponseTable(String tableTitle, String tableDeadline, Integer tableId, List<Map<String,String>> tableContent) {
        this.tableTitle = tableTitle;
        this.tableDeadline = tableDeadline;
        this.tableId=tableId;
        this.tableContent = tableContent;
    }

    public ResponseTable(){

    }

    public ResponseTable(Integer tableId, String tableTitle, String tableDeadline, String tableCreatopenid, List<Map<String,String>> tableContent) {
        this.tableId = tableId;
        this.tableTitle = tableTitle;
        this.tableDeadline = tableDeadline;
        this.tableCreatopenid = tableCreatopenid;
        this.tableContent = tableContent;
    }
}
