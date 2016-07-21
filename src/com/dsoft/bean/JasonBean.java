package com.dsoft.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: amjad
 * Date: 11/29/13
 * Time: 4:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class JasonBean {

    private int page;
    private int total;
    private List<Cell> rows = new ArrayList<Cell>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Cell> getRows() {
        return rows;
    }

    public void setRows(List<Cell> rows) {
        this.rows = rows;
    }
}
