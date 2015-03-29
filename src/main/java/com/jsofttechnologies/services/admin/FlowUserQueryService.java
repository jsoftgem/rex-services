package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.model.DataTables;
import com.jsofttechnologies.model.DataTablesColumn;
import com.jsofttechnologies.model.ResultDataModel;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.services.util.QueryService;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import java.util.*;

@Stateless
@Path("services/flow_user_query")
public class FlowUserQueryService extends QueryService<FlowUser> {

    /**
     *
     */
    private static final long serialVersionUID = -1985993914358013730L;

    private MergeExceptionSummary mergeExceptionSummary;


    public FlowUserQueryService() {
        super(FlowUser.class, FlowUser.FIND_ALL);
    }

    @GET
    @Path("find_by_id/{id:[0-9][0-9]*}")
    @Produces("application/json")
    public FlowUser getFlowUserById(@PathParam("id") Long id) {
        setNamedQuery(FlowUser.FIND_BY_ID);
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        setParam(param);
        return getSingleResult();
    }


    public FlowUser getFlowUserByUsername(String username) {
        try {
            setNamedQuery(FlowUser.FIND_BY_USERNAME);
            putParam("username", username);
            List<FlowUser> userList = doGetResultList();
            if (userList != null && !userList.isEmpty()) {
                return userList.get(0);
            }
        } catch (Exception e) {
            mergeExceptionSummary.handleException(e, getClass());
        }

        return null;
    }

    public FlowUser getFlowUserByEmail(String email) {
        try {
            setNamedQuery(FlowUser.FIND_BY_EMAIL);
            putParam("email", email);
            List<FlowUser> userList = doGetResultList();
            if (userList != null && !userList.isEmpty()) {
                return userList.get(0);
            }
        } catch (Exception e) {
            mergeExceptionSummary.handleException(e, getClass());
        }

        return null;
    }

    @GET
    @Path("/find_by_user_level")
    @Produces("application/json")
    public ResultDataModel<FlowUser> queryList(@HeaderParam("Authorization") String authorization) {
        ResultDataModel<FlowUser> resultDataModel = new ResultDataModel<>();
        try {

            FlowSessionHelper.Promise promise = session.isAuthorized(authorization);

            if (promise.getOk()) {

                String queryString = request.getQueryString();

                DataTables dataTables = ProjectHelper.getDataTableFromQuery(queryString);

                CriteriaBuilder cb = entityManager.getCriteriaBuilder();

                CriteriaQuery<FlowUser> criteriaQuery = cb.createQuery(FlowUser.class);

                Root<FlowUser> root = criteriaQuery.from(FlowUser.class);

                if (dataTables.getSearchValue() != null && !dataTables.getSearchValue().isEmpty()) {
                    List<Predicate> predicates = new ArrayList<>();
                    Iterator<DataTablesColumn> columnIterator = dataTables.getColumns().iterator();
                    while (columnIterator.hasNext()) {
                        DataTablesColumn column = columnIterator.next();
                        if (column.getSearchable()) {
                            predicates.add(cb.like(cb.lower(root.get(column.getData().toString())), dataTables.getSearchValue().toLowerCase()));
                        }
                    }
                    criteriaQuery.select(root).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
                }

                if (promise.getFlowUserGroup().getGroupName().equals(WarConstants.WAR_ADMIN_GROUP)) {
                    criteriaQuery.where(cb.notEqual(cb.lower(root.get("flowUserGroup").get("groupName")), ProjectConstants.GROUP_ADMIN));
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


                TypedQuery<FlowUser> tTypedQuery = entityManager.createQuery(criteriaQuery);

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
                if (promise.getFlowUserGroup().getGroupName().equals(WarConstants.WAR_ADMIN_GROUP)) {
                    countQuery.where(cb.notEqual(cb.lower(root.get("flowUserGroup.groupName")), ProjectConstants.GROUP_ADMIN));
                }
                Long count = entityManager.createQuery(countQuery).getSingleResult();


                if (count != null) {
                    recordsTotal = count.intValue();
                }

                resultDataModel.setRecordsTotal(recordsTotal);
                resultDataModel.setRecordsFiltered(recordsTotal);
            }

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), param);
        }

        return resultDataModel;
    }

}
