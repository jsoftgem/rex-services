package com.jsofttechnologies.v2.services.resource;

import javax.persistence.Query;

/**
 * Created by Jerico on 12/08/2015.
 */
public interface ResourceHelper {

    /**
     * Provides null checks when getting first index from Query.resultList
     *
     * @param query
     * @param type
     * @param <T>
     * @return
     */
    public <T> T getFromList(Query query, Class<T> type);



    public <T> T nullChecked(T t, Class<T> type);

}
