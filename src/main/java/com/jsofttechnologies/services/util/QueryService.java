package com.jsofttechnologies.services.util;

import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.model.DataTables;
import com.jsofttechnologies.model.DataTablesColumn;
import com.jsofttechnologies.model.ResultDataModel;
import com.jsofttechnologies.report.generator.CSVReportGenerator;
import com.jsofttechnologies.report.utlil.Report;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;

import javax.annotation.security.PermitAll;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.util.*;
import java.util.logging.Level;


/**
 * Created by Jerico on 6/13/2014.
 *
 * @param <T>
 */
public abstract class QueryService<T extends FlowJpe> extends FlowService {

    /**
     *
     */
    private static final long serialVersionUID = 2361737015284852479L;

    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    protected EntityManager entityManager;


    @Context
    private HttpServletRequest httpServletRequest;

    protected Query query;
    protected Integer max;
    protected boolean hasNext;
    protected boolean hasPrevious;
    protected List<T> resultList;
    protected Integer resultCount = 0;
    protected Integer firstResult = 0;
    protected Integer maxResultCount;
    protected String namedQuery;
    protected Map<String, Object> param;
    protected Class<T> classType;

    public QueryService(Class<T> classType) {
        this.classType = classType;
    }

    public QueryService(Class<T> classType, String namedQuery) {
        this(classType);
        this.namedQuery = namedQuery;
    }

    public QueryService(Class<T> classType, String namedQuery,
                        Map<String, Object> param) {
        this(classType);
        this.namedQuery = namedQuery;
        this.param = param;
    }


    public void putParam(String name, Object value) {
        if (param == null) {
            param = new HashMap<>();
        }

        param.put(name, value);
    }

    public void setNamedQuery(String namedQuery) {
        if (param != null) param.clear();
        this.namedQuery = namedQuery;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public void clear() {
        entityManager.clear();
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public boolean isHasNext() {
        hasNext = resultCount < maxResultCount;
        return hasNext;
    }

    public boolean isHasPrevious() {
        hasPrevious = firstResult > 0;
        return hasPrevious;
    }

    public void next() {
        if (isHasNext()) {
            firstResult += resultCount;
        }
    }

    public void previous() {
        if (isHasPrevious()) {
            firstResult -= resultCount;
        }
    }

    public Integer getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    public Integer getMaxResultCount() {
        return maxResultCount;
    }

    @GET
    @Path("/list")
    @Produces(value = "application/json")
    public List<T> doGetResultList() {
        try {
            query = entityManager.createNamedQuery(namedQuery, classType);
            if (param != null) {
                for (String key : param.keySet()) {
                    query.setParameter(key, param.get(key));
                }

            }
            maxResultCount = query.getMaxResults();
            if (getFirstResult() != null) {
                query.setFirstResult(getFirstResult());
            }
            if (getMax() != null) {
                query.setMaxResults(getMax());
            }
            resultList = query.getResultList();
            resultCount = resultList.size();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), param);
        }
        return resultList;
    }


    @GET
    @PermitAll
    @Path("/report_list")
    @Produces(value = "application/json")
    @Report
    public List<T> reportList() {
        try {
            query = entityManager.createNamedQuery(namedQuery, classType);
            if (param != null) {
                for (String key : param.keySet()) {
                    query.setParameter(key, param.get(key));
                }

            }
            maxResultCount = query.getMaxResults();
            if (getFirstResult() != null) {
                query.setFirstResult(getFirstResult());
            }
            if (getMax() != null) {
                query.setMaxResults(getMax());
            }
            resultList = query.getResultList();
            resultCount = resultList.size();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), param);
        }
        return resultList;
    }

    @GET
    @PermitAll
    @Path("/csv_list")
    @Produces(value = "application/json")
    @Report(generator = CSVReportGenerator.class)
    public List<T> csvList() {
        try {
            query = entityManager.createNamedQuery(namedQuery, classType);
            if (param != null) {
                for (String key : param.keySet()) {
                    query.setParameter(key, param.get(key));
                }
            }
            maxResultCount = query.getMaxResults();
            if (getFirstResult() != null) {
                query.setFirstResult(getFirstResult());
            }
            if (getMax() != null) {
                query.setMaxResults(getMax());
            }
            resultList = query.getResultList();
            resultCount = resultList.size();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), param);
        }
        return resultList;
    }


    @POST
    @Path("/")
    @Produces("application/json")
    public ResultDataModel<T> queryList() {
        ResultDataModel<T> resultDataModel = new ResultDataModel<>();
        try {


            String queryString = httpServletRequest.getQueryString();

            DataTables dataTables = ProjectHelper.getDataTableFromQuery(request);

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaQuery<T> criteriaQuery = cb.createQuery(classType);

            Root<T> root = criteriaQuery.from(classType);


            if (dataTables.getSearchValue() != null && !dataTables.getSearchValue().isEmpty()) {
                List<Predicate> predicates = new ArrayList<>();

                Iterator<DataTablesColumn> columnIterator = dataTables.getColumns().iterator();
                while (columnIterator.hasNext()) {
                    DataTablesColumn column = columnIterator.next();
                    if (column.getSearchable()) {
                        if (column.getData() != null && column.getData().toString().contains(".")) {
                            String[] splittedData = column.getData().toString().split("\\.");
                            Join[] joins = new Join[splittedData.length - 1];
                            for (int i = 0; i < splittedData.length; i++) {
                                if (i == 0) {
                                    joins[i] = root.join(splittedData[i]);
                                } else if (i < joins.length) {
                                    joins[i] = joins[i - 1].join(splittedData[i]);
                                } else {
                                    predicates.add(cb.like(cb.lower(joins[i - 1].get(splittedData[i])), "%" + dataTables.getSearchValue().toLowerCase() + "%"));
                                }
                            }
                        } else {
                            predicates.add(cb.like(cb.lower(root.get(column.getData().toString())), "%" + dataTables.getSearchValue().toLowerCase() + "%"));
                        }
                    }
                }
                criteriaQuery.select(root).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
            }

            List<Order> orderList = new ArrayList<>();

            if (dataTables.getOrder() != null) {
                DataTablesColumn column = dataTables.getColumns().get(dataTables.getOrder());
                if (column.getOrderable()) {
                    if (dataTables.getDir().equalsIgnoreCase("asc")) {
                        orderList.add(cb.asc(root.get(column.getData().toString())));
                    } else {
                        orderList.add(cb.desc(root.get(column.getData().toString())));
                    }
                }
            }
            criteriaQuery.orderBy(orderList);


            TypedQuery<T> tTypedQuery = entityManager.createQuery(criteriaQuery);

            if (dataTables.getStart() != null) {
                tTypedQuery.setFirstResult(dataTables.getStart());

            }
            if (dataTables.getLength() != null) {
                tTypedQuery.setMaxResults(dataTables.getLength());
            }


            resultList = tTypedQuery.getResultList();

            resultCount = resultList.size();

            resultDataModel.setData(resultList);
            resultDataModel.setDraw(dataTables.getDraw());


            Integer recordsTotal = Integer.valueOf(0);


            CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
            countQuery.select(cb.count(countQuery.from(classType)));

            Long count = entityManager.createQuery(countQuery).getSingleResult();

            if (count != null) {
                recordsTotal = count.intValue();
            }

            resultDataModel.setRecordsTotal(recordsTotal);
            resultDataModel.setRecordsFiltered(recordsTotal);

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), param);
        }

        return resultDataModel;
    }

    public T getSingleResult() {
        T t = null;
        //logger //logger = //logger.get//logger(QueryService.class.getName());

        //logger.log(Level.INFO, "getSingleResult: " + namedQuery + " param: " + param);
        try {
            query = entityManager.createNamedQuery(namedQuery, classType);

            if (param != null) {
                for (String key : param.keySet()) {
                    query.setParameter(key, param.get(key));
                }
            }
            t = (T) query.getSingleResult();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), param);
        }
        return t;
    }

    @GET
    @Path("/getInstance")
    @Produces("application/json")
    public T getById(@QueryParam("id") Long id) {
        T t = null;

        try {
            t = entityManager.find(this.classType, id);
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        return t;
    }

    @GET
    @Path("/getInstance/{id:[0-9][0-9]*}")
    @Produces("application/json")
    public T getByIdPath(@PathParam("id") Long id) {
        T t = null;

        try {
            t = entityManager.find(this.classType, id);
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        return t;
    }
}
