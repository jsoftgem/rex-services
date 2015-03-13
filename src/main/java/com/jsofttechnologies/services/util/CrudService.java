package com.jsofttechnologies.services.util;

import com.jsofttechnologies.ds.EntityManagerDAO;
import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jerico on 6/13/2014.
 *
 * @param <T>
 * @param <ID>
 */
public abstract class CrudService<T extends FlowJpe, ID extends Number> extends FlowService {

    /**
     *
     */
    private static final long serialVersionUID = -6718219051830994180L;

    @EJB
    private EntityManagerDAO<T, ID> entityManagerDao;


    private ID id;
    private T instance;
    private Class<T> classType;

    public CrudService(Class<T> classType) {
        this.classType = classType;
    }

    public Class<T> getClassType() {
        return classType;
    }

    public T getInstance() {
        if (instance == null) {
            if (id != null) {
                instance = loadInstance();
            } else {
                instance = createInstance();
            }
        }
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public T loadInstance() {
        try {
            return entityManagerDao.find(getClassType(), getId());
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }
        return null;
    }

    public T createInstance() {
        try {
            instance = getClassType().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            exceptionSummary.handleException(e, getClass());
        }
        return instance;
    }

    public boolean clear() {

        instance = null;

        return instance == null;
    }

    public boolean isManaged() {
        return getInstance() != null && getId() != null;
    }

    public String save() throws Exception {

        String save = "saved";

        if (isManaged()) {
            T t2 = null;
            t2 = preUpdateValidation(getInstance());
            entityManagerDao.updateObject(t2);
            postUpdateValidation(t2);
        } else {
            T t2 = null;
            t2 = preCreateValidation(getInstance());
            entityManagerDao.createObject(t2);
            postCreateValidation(t2);
        }
        return save;
    }

    public String cancel() {
        return "cancelled";
    }

    public String delete() {
        try {
            T t = preDeleteValidation(getInstance());
            entityManagerDao.deleteObject(t);
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), getInstance());
        }
        return "deleted";
    }


    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response delete(@PathParam("id") ID id) {
        Response response = null;
        try {
            setId(id);
            if (delete().equalsIgnoreCase("deleted")) {
                ProjectHelper projectHelper = new ProjectHelper();

                response = Response.ok(projectHelper.createJson().addField("id", this.id).addField("msg", deleteSuccessMessage()).buildJsonString(),
                        MediaType.APPLICATION_JSON_TYPE).header(ProjectConstants.HEADER_AUTHORIZATION, request.getHeader(ProjectConstants.HEADER_AUTHORIZATION)).build();
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
            response = ProjectHelper.error(deleteErrorMessage());
        }

        return response;
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(T t) {
        Response response = null;
        try {
            setInstance(t);
            save();
            Long id = (Long) getInstance().getId();
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("id", id);
            jsonMap.put("msg", createSuccessMessage(t));
            response = Response.ok(ProjectHelper.json(jsonMap)).header(ProjectConstants.HEADER_AUTHORIZATION, request.getHeader(ProjectConstants.HEADER_AUTHORIZATION))
                    .type(MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), t);
            response = ProjectHelper.error(e.getMessage());
        }
        return response;
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(T t, @PathParam("id") ID id) {
        Response response = null;
        try {
            setInstance(t);
            T currentInstance = getInstance();
            setId(id);
            save();
            ProjectHelper projectHelper = new ProjectHelper();
            response = Response.ok(projectHelper.createJson().addField("id", this.id).addField("msg", updateSuccessMessage(t)).buildJsonString()).header(ProjectConstants.HEADER_AUTHORIZATION, request.getHeader(ProjectConstants.HEADER_AUTHORIZATION))
                    .type(MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), t);
            response = ProjectHelper.error(e.getMessage());
        }
        return response;
    }

    protected abstract T preCreateValidation(T t) throws Exception;

    protected abstract T preUpdateValidation(T t) throws Exception;

    protected T preDeleteValidation(T t) {
        return t;
    }

    protected void postCreateValidation(T t) {

    }

    protected void postUpdateValidation(T t) {

    }


    public String createErrorMessage() {
        return messageService.getMessage(ProjectConstants.MSG_SAVE_FAILED);
    }

    public String createErrorMessage(T t) {
        return createErrorMessage();
    }

    public String createSuccessMessage() {
        return messageService.getMessage(ProjectConstants.MSG_SAVE_SUCCESS);
    }

    public String createSuccessMessage(T t) {
        return createSuccessMessage();
    }

    public String updateErrorMessage() {
        return messageService.getMessage(ProjectConstants.MSG_UPDATE_FAILED);
    }

    public String updateErrorMessage(T t) {
        return updateErrorMessage();
    }

    public String updateSuccessMessage() {
        return messageService.getMessage(ProjectConstants.MSG_UPDATE_SUCCESS);
    }

    public String updateSuccessMessage(T t) {
        return updateSuccessMessage();
    }


    public String deleteErrorMessage() {
        return messageService.getMessage(ProjectConstants.MSG_DELETE_FAIlED);
    }

    public String deleteSuccessMessage() {
        return messageService.getMessage(ProjectConstants.MSG_DELETE_SUCCESS);
    }

    public Exception throwException(String messageKey) {
        return new Exception(messageService.getMessage(messageKey));
    }

    public Exception throwException(String messageKey, String... messages) {
        return new Exception(MessageFormat.format(messageService.getMessage(messageKey), messages));
    }


    public String getMessage(String key) {
        return messageService.getMessage(key);
    }

}
