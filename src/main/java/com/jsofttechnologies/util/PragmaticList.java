package dk.topdanmark.tr.common;

import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TRRestList<T> {

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

    public TRRestList() {
        this.firstResult = Integer.valueOf(0);
    }

    public TRRestList(String unitName, String jpaName) {
        this();
        this.unitName = unitName;
        this.jpaName = jpaName;
    }

    public TRRestList(String unitName, String jpaName, Integer firstResult, Integer maxResults, String[] fields, String[] sorts, String query) {
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
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(jpaClass);
            Root<T> from = criteriaQuery.from(jpaClass);
            CriteriaQuery select = criteriaQuery.select(from);
            setCriteriaFields(criteriaBuilder, select, from);
            setCriteriaQuery(criteriaBuilder, select, from);
            setCriteriaSorts(criteriaBuilder, select, from);
            TypedQuery<T> typedQuery = entityManager.createQuery(select);
            if (maxResults != null) {
                typedQuery.setMaxResults(maxResults);
            }
            resultList = typedQuery.getResultList();
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
        if (query != null) {
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
                if (difference != null && !difference.isEmpty() && difference.contains(queryField)) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(from.get(queryField)), query));
                } else {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(from.get(queryField)), query));
                }
            }
            if (!predicates.isEmpty()) {
                select.where(criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])));
            }
        }
    }

    protected void setCriteriaFields(CriteriaBuilder criteriaBuilder, CriteriaQuery select, Root from) {
        if (fields != null) {

            for (String query : fields) {
                String value = getValue(query);
                String field = getField(query);
                Class type = getFieldType(field);
                Predicate predicate = null;
                boolean isAnd = false;

                if (value.charAt(value.length() - 1) == '*') {
                    value = value.substring(0, value.length() - 2);
                    isAnd = true;
                }
                if (type.equals(String.class)) {
                    if (value.charAt(0) == '%') {
                        predicate = criteriaBuilder.like(criteriaBuilder.lower(from.get(field)), value);
                    } else if (value.charAt(0) == '^') {
                        predicate = criteriaBuilder.notLike(criteriaBuilder.lower(from.get(field)), value);
                    } else if (value.charAt(0) == '!') {
                        predicate = criteriaBuilder.notEqual(from.get(field), value);
                    } else {
                        predicate = criteriaBuilder.equal(from.get(field), value);
                    }
                } else if (type.equals(Boolean.class)) {
                    predicate = criteriaBuilder.equal(from.get(field), Boolean.valueOf(value));
                } else if (type.equals(Integer.class)) {
                    predicate = criteriaBuilder.equal(from.get(field), Integer.valueOf(value));
                } else if (type.equals(Long.class)) {
                    predicate = criteriaBuilder.equal(from.get(field), Long.valueOf(value));
                } else if (type.equals(Short.class)) {
                    predicate = criteriaBuilder.equal(from.get(field), Short.valueOf(value));
                } else if (type.equals(Float.class)) {
                    predicate = criteriaBuilder.equal(from.get(field), Float.valueOf(value));
                } else if (type.equals(Double.class)) {
                    predicate = criteriaBuilder.equal(from.get(field), Double.valueOf(value));
                } else if (type.equals(Date.class)) {
                    DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
                    predicate = criteriaBuilder.equal(from.get(field), dateFormat.format(value));
                }

                if (isAnd) {
                    select.where(criteriaBuilder.and(predicate));
                } else {
                    select.where(criteriaBuilder.or(predicate));
                }
            }
        }
    }

    private Predicate getFieldCriteria(CriteriaBuilder criteriaBuilder, Path path, String value, Class type) {
        Predicate predicate = null;

        if (value.contains("<")) {
            int nextChar = value.indexOf("<") + 1;
            if (nextChar < value.length() && value.charAt(nextChar) == '^') {
                value = value.replaceAll("<^", "");
                criteriaBuilder.lessThanOrEqualTo(path, value);
            } else {
                value = value.replaceAll("<", "");
                criteriaBuilder.lessThan(path, value);
            }

        } else if (value.contains(">")) {
            int nextChar = value.indexOf(">") + 1;
            if (nextChar < value.length() && value.charAt(nextChar) == '^') {
                value = value.replaceAll(">^", "");
                criteriaBuilder.greaterThanOrEqualTo(path, value);
            } else {
                value = value.replaceAll(">", "");
                criteriaBuilder.greaterThan(path, value);
            }
        } else {
            criteriaBuilder.equal(path, getValue(value, type));
        }

        return predicate;
    }

    private Object getValue(String value, Class type) {
        Object oValue = null;
        if (type.equals(Integer.class)) {
            oValue = Integer.valueOf(value);
        } else if (type.equals(Long.class)) {
            oValue = Integer.valueOf(value);
        } else if (type.equals(Short.class)) {
            oValue = Integer.valueOf(value);
        } else if (type.equals(Float.class)) {
            oValue = Integer.valueOf(value);
        } else if (type.equals(Double.class)) {
            oValue = Integer.valueOf(value);
        } else if (type.equals(Date.class)) {
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
            oValue = dateFormat.format(value);
        }
        return oValue;
    }

    private void setAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            setAllFields(fields, type.getSuperclass());
        }

    }

    private Class getFieldType(String field) {
        List<Field> fields = new ArrayList<Field>();
        setAllFields(fields, jpaClass);
        Class type = null;
        for (Field fd : fields) {
            if (fd.getName().equals(field)) {
                type = fd.getType();
            }
        }
        return type;
    }

    protected void setCriteriaSorts(CriteriaBuilder criteriaBuilder, CriteriaQuery select, Root from) {
        if (sorts != null) {
            List<Order> orderList = new ArrayList<Order>();
            for (String sort : sorts) {
                if (sort.charAt(0) == '-') {
                    sort = sort.substring(1);
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

    public TRRestList<T> open() {
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

    public TRRestList<T> setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public String getJpaName() {
        return jpaName;
    }

    public TRRestList<T> setJpaName(String jpaName) {
        this.jpaName = jpaName;
        return this;
    }

    public Integer getFirstResult() {
        return firstResult;
    }

    public TRRestList<T> setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
        return this;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public TRRestList<T> setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    public String[] getFields() {
        return fields;
    }

    public TRRestList<T> setFields(String[] fields) {
        this.fields = fields;
        return this;
    }

    public String[] getSorts() {
        return sorts;
    }

    public TRRestList<T> setSorts(String[] sorts) {
        this.sorts = sorts;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public TRRestList<T> setQuery(String query) {
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

    public TRRestList<T> setEntityManager(EntityManager entityManager) {
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

    protected TRRestList<T> setJpaClass(Class<T> jpaClass) {
        this.jpaClass = jpaClass;
        return this;
    }

    public Class<T> getJpaClass() {
        return jpaClass;
    }
}
