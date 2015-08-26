package com.jsofttechnologies.v2.services.resource;

/**
 * Created by Jerico on 26/08/2015.
 */

import com.jsofttechnologies.ds.v2.FluidDatasource;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.v2.services.FluidPlatformService;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by rickz_000 on 5/10/2015.
 */
@Produces(MediaType.APPLICATION_JSON)
public abstract class PageResource<T, ID> extends FluidPlatformService implements PageResourceActions<T, ID>, UserResource, ResourceHelper {

    @EJB
    private FluidDatasource<T, ID> fluidDatasource;


    protected Class<T> classType;

    public PageResource(Class<T> classType) {
        this.classType = classType;
    }


    @Context
    private HttpServletRequest request;

    @GET
    @Path("name/{query}")
    public Response get(@PathParam("query") String query, @QueryParam("param") String[] params, @QueryParam("type") String[] types, @QueryParam("value") String[] values) {
        Response response = null;


        Object retValue = fluidDatasource.query(query, params, types, values, classType);


        if (retValue instanceof Collection) {
            List<T> listValue = (List<T>) retValue;


            if (!listValue.isEmpty()) {
                if (listValue.size() > 1) {
                    response = Response.ok(listValue).type(MediaType.APPLICATION_JSON_TYPE).build();
                } else {
                    response = Response.ok(listValue.get(0)).type(MediaType.APPLICATION_JSON_TYPE).build();
                }
            }

        } else {
            response = Response.ok(retValue).build();
        }


        return response;
    }

    @GET
    @Path("{id}")
    public T get(@PathParam("id") ID id) {

        try {
            if (id != null) {
                onView();
                return fluidDatasource.find(classType, id);
            }
        } catch (Exception e) {
            ejbExceptionHandler.handleException(e, classType);
        }
        return null;
    }

    @GET
    @Path("new")
    public T get() {
        try {
            onView();
            return classType.newInstance();
        } catch (InstantiationException e) {
            ejbExceptionHandler.handleException(e, classType);
        } catch (IllegalAccessException e) {
            ejbExceptionHandler.handleException(e, classType);
        } catch (Exception e) {
            ejbExceptionHandler.handleException(e, classType);
        }

        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(T t) {
        Response response = null;
        try {
            onSave(t);
            t = fluidDatasource.updateObject(t);
            response = Response.ok(t).type(MediaType.APPLICATION_JSON_TYPE).build();
            postSave(t);
        } catch (Exception e) {
            ejbExceptionHandler.handleException(e, getClass());
            response = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
        }

        return response;
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") ID id) {
        Response response = null;
        try {
            onDelete(id);
            fluidDatasource.deleteObject(classType, id);
            response = Response.ok().entity("Delete successful.").type(MediaType.TEXT_PLAIN_TYPE).build();
            postDelete();
        } catch (Exception e) {
            ejbExceptionHandler.handleException(e, getClass());
            response = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();

        }
        return response;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response remove(T t) {
        Response response = null;
        try {
            onRemove(t);
            fluidDatasource.deleteObject(t);
            response = Response.ok().entity("Delete successful.").type(MediaType.TEXT_PLAIN_TYPE).build();
            postDelete();
        } catch (Exception e) {
            ejbExceptionHandler.handleException(e, getClass());
            response = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();

        }
        return response;
    }

    @GET
    public List<T> query() {
        try {
            onView();
            List<T> returnList = fluidDatasource.query(classType);
            return returnList;
        } catch (Exception e) {
            ejbExceptionHandler.handleException(e, getClass());
        }
        return null;
    }

    public void onSave(T t) throws Exception {

    }

    public void postSave(T t) {

    }

    public void onDelete(ID id) throws Exception {

    }


    public void onRemove(T t) throws Exception {

    }

    public void postDelete() {

    }

    public void onView() throws Exception {

    }

    public static class QueryHelper {

        private List<String> queries;

        public QueryHelper() {
            queries = new ArrayList<>();
        }

        public QueryHelper(String... query) {
            queries = Arrays.asList(query);
        }

        public QueryHelper add(String query) {
            queries.add(query);
            return this;
        }

        public String[] get() {
            return queries.toArray(new String[queries.size()]);
        }


    }

    public List<T> getTypeList(Response response) {
        if (response != null && response.getEntity() != null && response.getEntity() instanceof Collection) {
            return (List<T>) response.getEntity();
        }
        return null;
    }


    public T getType(Response response) {

        try {
            if (response != null && response.getEntity() != null && response.getEntity() != null) {
                return (T) response.getEntity();
            }

        } catch (ClassCastException e) {
            ejbExceptionHandler.handleException(e, getClass());
        }


        return null;
    }


    @Override
    public HttpServletRequest getRequest() {
        return request;
    }


    @Override
    public void setInfo(FlowUser user) {

    }

    @Override
    public void setToken(String token) {

    }

    @Override
    public void setAuthenticated(Boolean authenticated) {

    }

    protected EntityManager getEntityManager() {
        return fluidDatasource.getEntityManager();
    }

    @Override
    public <T> T getFromList(Query query, Class<T> type) {

        List<T> result = query.getResultList();

        if (result != null && !result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }


    @Override
    public <T> T nullChecked(T t, Class<T> type) {
        if (t != null) {
            return t;
        } else {
            return null;
        }
    }
}
