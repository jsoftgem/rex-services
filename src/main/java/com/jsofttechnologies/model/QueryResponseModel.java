package com.jsofttechnologies.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Jerico on 7/11/2014.
 */
@XmlRootElement
public class QueryResponseModel<T> {

    private Integer firstResult;
    private Integer resultCount;
    private Integer maxResultCount;
    private List<T> resultList;

    public Integer getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public void setMaxResultCount(Integer maxResultCount) {
        this.maxResultCount = maxResultCount;
    }

    public Integer getMaxResultCount() {
        return maxResultCount;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }



}
