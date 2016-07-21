package com.dsoft.bean;

/**
 * Created with IntelliJ IDEA.
 * User: amjad
 * Date: 11/29/13
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Cell {
    private long id;
    private Object cell;

    public Object getCell() {
        return cell;
    }

    public void setCell(Object cell) {
        this.cell = cell;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
