package com.cicada.yuanxiaobao.attendance.model;

import java.util.List;

/**
 * Created by tanglei on 16/7/23.
 */
public class ReceiveStickTrackModel {

    private int page;
    private int pagesize;
    private int records;
    private int total;
    private List<SickItemModel> rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<SickItemModel> getRows() {
        return rows;
    }

    public void setRows(List<SickItemModel> rows) {
        this.rows = rows;
    }

}
