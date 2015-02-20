package com.jsofttechnologies.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Jerico on 7/11/2014.
 */
@XmlRootElement
public class QueryModel {

    private Integer max;
    private Integer firstResult;

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    @Override
    public String toString() {
        return "QueryModel{" +
                "max=" + max +
                ", firstResult=" + firstResult +
                '}';
    }
}
