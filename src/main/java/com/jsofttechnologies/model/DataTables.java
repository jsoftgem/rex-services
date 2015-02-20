package com.jsofttechnologies.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Jerico on 11/10/2014.
 */
@XmlRootElement
public class DataTables implements Serializable {

    private Integer draw;
    private Integer start;
    private Integer length;
    private Integer order;
    private String dir;
    private String searchValue;
    private Boolean searchRegex;
    private List<DataTablesColumn> columns;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Boolean getSearchRegex() {
        return searchRegex;
    }

    public void setSearchRegex(Boolean searchRegex) {
        this.searchRegex = searchRegex;
    }

    public List<DataTablesColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DataTablesColumn> columns) {
        this.columns = columns;
    }
}
