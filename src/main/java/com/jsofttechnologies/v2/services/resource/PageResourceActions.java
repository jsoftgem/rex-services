package com.jsofttechnologies.v2.services.resource;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by rickz_000 on 5/10/2015.
 */
public interface PageResourceActions<T, ID> {


    public T get(ID id);

    public T get();

    public Response get(String query, String[] param, String[] type, String[] values);

    public Response save(T t);

    public Response delete(ID id);

    public Response remove(T t);

    public List<T> query();
}
