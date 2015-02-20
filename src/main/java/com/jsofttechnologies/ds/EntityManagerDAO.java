package com.jsofttechnologies.ds;


import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.util.ProjectConstants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Jerico on 6/13/2014.
 *
 * @param <T>
 * @param <ID>
 */
@Stateless
public class EntityManagerDAO<T extends FlowJpe, ID> {

    /**
     *
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -1599871562131454183L;
    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;

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

    public T find(Class<T> clazz, ID id) throws Exception {
        return entityManager.find(clazz, id);
    }

    public void clear() {
        if (entityManager != null) {
            entityManager.clear();
        }
    }

}
