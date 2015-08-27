package com.jsofttechnologies.ds.v2;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.services.util.MessageService;
import com.jsofttechnologies.util.ProjectConstants;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Jerico on 7/9/2015.
 */
@Stateless
public class FluidDatasource<T, ID> {

    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;

    @EJB
    private MergeExceptionSummary ejbExceptionHandler;
    @EJB
    private MessageService messageService;

    public T updateObject(T object) {
        return entityManager.merge(object);
    }

    public void createObject(T object) throws Exception {
        entityManager.persist(object);
    }

    public void refresh(T object) throws Exception {
        entityManager.refresh(object);
    }

    public void deleteObject(T object) throws Exception {
        entityManager.remove(object);
    }

    public void deleteObject(Class<T> clazz, ID id) throws Exception {
        T t = find(clazz, id);
        entityManager.remove(t);
    }

    public void removeObject(T t) throws Exception {
        entityManager.remove(t);
    }


    public T find(Class<T> clazz, ID id) throws Exception {
        return entityManager.find(clazz, id);
    }

    public List<T> query(Class<T> classType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(classType);
        Root<T> root = criteriaQuery.from(classType);
        criteriaQuery.select(root);
        TypedQuery<T> tTypedQuery = entityManager.createQuery(criteriaQuery);
        return tTypedQuery.getResultList();
    }


    public Object query(String namedQuery, String[] params, String[] types, String[] values, Class<T> classType) {
        try {

            if (params == null || types == null || values == null) {
                return messageService.getMessage("PARAM_REQUIRED");
            } else {

                boolean sameSize = params.length == types.length && params.length == values.length;

                if (!sameSize) {
                    return messageService.getMessage("PARAM_REQUIRED");
                }
            }


            Query query = entityManager.createNamedQuery(namedQuery, classType);

            for (int i = 0; i < params.length; i++) {
                String param = params[i];
                String value = values[i];
                String type = types[i];


                switch (type) {
                    case "boolean":
                        query.setParameter(param, Boolean.valueOf(value));
                        break;
                    case "int":
                        String trimmedValue = value.trim();

                        if (trimmedValue.length() > 5) {
                            query.setParameter(param, Long.valueOf(trimmedValue));
                        } else {
                            query.setParameter(param, Integer.valueOf(trimmedValue));
                        }


                    default:
                        query.setParameter(param, value);
                        break;
                }

            }

            return query.getResultList();

        } catch (Exception e) {
            ejbExceptionHandler.handleException(e, getClass());
        }
        return messageService.getMessage("QUERY_NOT_FOUND", namedQuery);
    }

    public void clear() {
        if (entityManager != null) {
            entityManager.clear();
        }
    }


    public EntityManager getEntityManager() {
        return entityManager;
    }
}
