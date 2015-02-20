package com.jsofttechnologies.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Jerico on 11/10/2014.
 */
@XmlRootElement
public class DataTablesColumn implements Serializable {


    private Integer index;
    private Object data;
    private String name;
    private Boolean searchable;
    private Boolean orderable;
    private String searchValue;
    private Boolean searchRegex;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSearchable() {
        return searchable;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    public Boolean getOrderable() {
        return orderable;
    }

    public void setOrderable(Boolean orderable) {
        this.orderable = orderable;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataTablesColumn)) return false;

        DataTablesColumn column = (DataTablesColumn) o;

        if (!index.equals(column.index)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return index.hashCode();
    }
}


