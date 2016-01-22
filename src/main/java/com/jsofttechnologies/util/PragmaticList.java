package com.jsofttechnologies.util;


import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PragmaticList<T> {

    private String unitName;
    private String jpaName;
    private Integer firstResult;
    private Integer maxResults;
    private String[] fields;
    private String[] sorts;
    private String query;
    private Long totalCount;

    protected Class<T> jpaClass;
    protected EntityManager entityManager;

    public PragmaticList() {
        this.firstResult = Integer.valueOf(0);
    }

    public PragmaticList(String unitName, String jpaName) {
        this();
        this.unitName = unitName;
        this.jpaName = jpaName;
    }

    public PragmaticList(String unitName, String jpaName, Integer firstResult, Integer maxResults, String[] fields, String[] sorts, String query) {
        this(unitName, jpaName);
        this.firstResult = firstResult;
        this.maxResults = maxResults;
        this.fields = fields;
        this.sorts = sorts;
        this.query = query;
    }

    public List<T> getResultList() {
        List<T> resultList = null;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        if (jpaClass == null) {
            try {
                jpaClass = (Class<T>) Class.forName(jpaName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (jpaClass != null) {
            Integer firstResult = (this.firstResult += maxResults) - 1;
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(jpaClass);
            Root<T> from = criteriaQuery.from(jpaClass);
            CriteriaQuery select = criteriaQuery.select(from);
            setCriteriaFields(criteriaBuilder, select, from);
            setCriteriaQuery(criteriaBuilder, select, from);
            setCriteriaSorts(criteriaBuilder, select, from);
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
                throw new MalformedFieldMappingException(map);
            } catch (MalformedFieldMappingException e) {
                e.printStackTrace();
            }
        }
        return map.split("=")[1];
    }

    private String getField(String map) {
        if (!map.contains("=")) {
            try {
                throw new MalformedFieldMappingException(map);
            } catch (MalformedFieldMappingException e) {
                e.printStackTrace();
            }
        }
        return map.split("=")[0];
    }

    protected void setCriteriaQuery(CriteriaBuilder criteriaBuilder, CriteriaQuery select, Root from) {
        Field[] classFields = jpaClass.getDeclaredFields();
        List<String> queryFields = new ArrayList<String>();
        List<String> difference = new ArrayList<String>();
        List<Predicate> predicates = new ArrayList<Predicate>();

        for (Field field : classFields) {
            if (field.isAnnotationPresent(Column.class) && field.getType().equals(String.class)) {
                queryFields.add(field.getName());
            }
        }

        if (this.fields != null) {
            difference = Difference.getDifferenceList(queryFields, getKeys());
        }

        for (String queryField : queryFields) {
            if (difference.contains(queryField)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(from.get(queryField)), "%" + query + "%"));
            }
        }

        select.where(criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])));
    }

    protected void setCriteriaFields(CriteriaBuilder criteriaBuilder, CriteriaQuery select, Root from) {
        if (fields != null) {
            List<Predicate> predicates = new ArrayList<Predicate>();
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

    protected void setCriteriaSorts(CriteriaBuilder criteriaBuilder, CriteriaQuery select, Root from) {
        if (sorts != null) {
            List<Order> orderList = new ArrayList<Order>();
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


    public static class MalformedFieldMappingException extends Exception {
        public MalformedFieldMappingException(String where) {
            super(where + " must have field=value format.");
        }
    }

    public PragmaticList<T> open() {
        entityManager = Persistence.createEntityManagerFactory(unitName).createEntityManager();
        return this;
    }

    public void close() {
        entityManager.close();
        entityManager = null;
        this.totalCount = null;
        this.firstResult = Integer.valueOf(0);
        this.maxResults = null;
    }

    public String getUnitName() {
        return unitName;
    }

    public PragmaticList<T> setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public String getJpaName() {
        return jpaName;
    }

    public PragmaticList<T> setJpaName(String jpaName) {
        this.jpaName = jpaName;
        return this;
    }

    public Integer getFirstResult() {
        return firstResult;
    }

    public PragmaticList<T> setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
        return this;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public PragmaticList<T> setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    public String[] getFields() {
        return fields;
    }

    public PragmaticList<T> setFields(String[] fields) {
        this.fields = fields;
        return this;
    }

    public String[] getSorts() {
        return sorts;
    }

    public PragmaticList<T> setSorts(String[] sorts) {
        this.sorts = sorts;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public PragmaticList<T> setQuery(String query) {
        this.query = query;
        return this;
    }

    public Long getTotalCount() {
        if (totalCount == null) {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
            if (jpaClass == null) {
                try {
                    jpaClass = (Class<T>) Class.forName(jpaName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            countQuery.select(criteriaBuilder.count(countQuery.from(jpaClass)));
            totalCount = entityManager.createQuery(countQuery).getSingleResult();
        }
        return totalCount;
    }

    public static class Difference {

        public static <T> Set<T> getDifferenceSet(List<T> initList1, List<T> initList2) {
            Set<T> symmetricDiff = new HashSet<T>(initList1);
            symmetricDiff.addAll(initList2);
            Set<T> tmp = new HashSet<T>(initList1);
            tmp.retainAll(initList2);
            symmetricDiff.removeAll(tmp);
            return symmetricDiff;
        }

        public static <T> Set<T> getDifferenceSet(Set<T> initList1, Set<T> initList2) {
            Set<T> symmetricDiff = new HashSet<T>(initList1);
            symmetricDiff.addAll(initList2);
            Set<T> tmp = new HashSet<T>(initList1);
            tmp.retainAll(initList2);
            symmetricDiff.removeAll(tmp);
            return symmetricDiff;
        }

        public static <T> List<T> getDifferenceList(List<T> initList1, List<T> initList2) {
            Set<T> symmetricDiff = new HashSet<T>(initList1);
            symmetricDiff.addAll(initList2);
            Set<T> tmp = new HashSet<T>(initList1);
            tmp.retainAll(initList2);
            symmetricDiff.removeAll(tmp);

            List<T> tempList = new ArrayList<T>(symmetricDiff);
            return tempList;
        }

        public static <T> Set<T> getContainingSet(Set<T> initList1, Set<T> initList2) {
            Set<T> tmp = new HashSet<T>(initList2);
            tmp.retainAll(initList1);
            return tmp;
        }
    }

    public PragmaticList<T> setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        return this;
    }

    private List<String> getKeys() {
        List<String> keys = new ArrayList<String>();
        for (String field : fields) {
            keys.add(getField(field));
        }
        return keys;
    }

    protected PragmaticList<T> setJpaClass(Class<T> jpaClass) {
        this.jpaClass = jpaClass;
        return this;
    }

    public Class<T> getJpaClass() {
        return jpaClass;
    }
}
