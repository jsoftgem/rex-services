package com.jsofttechnologies.util;


import com.jsofttechnologies.model.DataTablesColumn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerico on 20/01/2016.
 */
public class PragmaticList<T> {

    private String unitName;
    private String jpaName;
    private Integer firstResult;
    private Integer maxResults;
    private String[] fields;
    private String[] sorts;
    private String query;

    protected EntityManager entityManager;

    public PragmaticList() {
        this.firstResult = Integer.valueOf(0);
    }

    public PragmaticList(String unitName, String jpaName) {
        this.unitName = unitName;
        this.jpaName = jpaName;
    }

    public PragmaticList(String unitName, String jpaName, Integer firstResult, Integer maxResults, String[] fields, String[] sorts, String query) {
        this.unitName = unitName;
        this.jpaName = jpaName;
        this.firstResult = firstResult;
        this.maxResults = maxResults;
        this.fields = fields;
        this.sorts = sorts;
        this.query = query;
    }


    public List<T> getResultList() {
        List<T> resultList = null;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Class jpaClass = null;

        try {
            jpaClass = Class.forName(jpaName);
            countQuery.select(criteriaBuilder.count(countQuery.from(jpaClass)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        if (jpaClass != null) {
            Integer firstResult = (this.firstResult += maxResults) - 1;
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(jpaClass);
            Root<T> from = criteriaQuery.from(jpaClass);
            CriteriaQuery select = criteriaQuery.select(from);
            TypedQuery<T> typedQuery = entityManager.createQuery(select);

            typedQuery.setFirstResult(firstResult);

            if (maxResults != null) {
                typedQuery.setMaxResults(maxResults);
            }

        }

        return resultList;
    }

    private String getValue(String map) {
        if (!map.contains("=")) {
            try {
                throw new MalformedQueryMappingException(map);
            } catch (MalformedQueryMappingException e) {
                e.printStackTrace();
            }
        }
        return map.split("=")[1];
    }

    private String getField(String map) {
        if (!map.contains("=")) {
            try {
                throw new MalformedQueryMappingException(map);
            } catch (MalformedQueryMappingException e) {
                e.printStackTrace();
            }
        }
        return map.split("=")[0];
    }


    private void setCriteriaQuery(Class<T> jpaClass, CriteriaBuilder criteriaBuilder, CriteriaQuery select, Root from) {
        Field[] fields = jpaClass.getDeclaredFields();

    }

    private void setCriteriaFields(CriteriaBuilder criteriaBuilder, CriteriaQuery select, Root from) {
        if (fields != null) {
            List<Predicate> predicates = new ArrayList<>();
            for (String query : fields) {
                String value = getValue(query);
                String field = getField(query);
                if (value.charAt(0) == '%') {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(from.get(field)), value));
                } else if (value.charAt(0) == '^') {
                    predicates.add(criteriaBuilder.notLike(criteriaBuilder.lower(from.get(field)), value));
                } else if (value.charAt(0) == '!') {
                    predicates.add(criteriaBuilder.notEqual(from.get(field), value));
                } else {
                    predicates.add(criteriaBuilder.equal(from.get(field), value));
                }
            }

            select.where(criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])));
        }
    }

    private void setCriteriaSorts(CriteriaBuilder criteriaBuilder, CriteriaQuery select, Root from) {
        if (sorts != null) {
            List<Order> orderList = new ArrayList<>();
            for (String sort : sorts) {
                if (sort.charAt(0) == '-') {
                    orderList.add(criteriaBuilder.desc(from.get(sort)));
                } else {
                    orderList.add(criteriaBuilder.asc(from.get(sort)));
                }
            }
            select.orderBy(orderList);
        }
    }


    public class MalformedQueryMappingException extends Throwable {
        public MalformedQueryMappingException(String where) {
            super(where + " must have field=value format.");
        }
    }

    public PragmaticList open() {
        entityManager = Persistence.createEntityManagerFactory(unitName).createEntityManager();
        return this;
    }

    public void close() {
        entityManager.close();
        entityManager = null;
    }

    public String getUnitName() {
        return unitName;
    }

    public PragmaticList setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public String getJpaName() {
        return jpaName;
    }

    public PragmaticList setJpaName(String jpaName) {
        this.jpaName = jpaName;
        return this;
    }

    public Integer getFirstResult() {
        return firstResult;
    }

    public PragmaticList setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
        return this;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public PragmaticList setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    public String[] getFields() {
        return fields;
    }

    public PragmaticList setFields(String[] fields) {
        this.fields = fields;
        return this;
    }

    public String[] getSorts() {
        return sorts;
    }

    public PragmaticList setSorts(String[] sorts) {
        this.sorts = sorts;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public PragmaticList setQuery(String query) {
        this.query = query;
        return this;
    }
}
