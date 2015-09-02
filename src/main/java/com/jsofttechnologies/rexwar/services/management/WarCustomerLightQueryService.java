package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.model.DataTables;
import com.jsofttechnologies.model.DataTablesColumn;
import com.jsofttechnologies.model.ResultDataModel;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.model.management.WarCustomer;
import com.jsofttechnologies.rexwar.model.management.WarCustomerLight;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.services.util.FlowPermissionService;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.services.util.QueryService;
import com.jsofttechnologies.util.ProjectHelper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jerico on 1/28/2015.
 */
@Path("services/war/customer_light_query")
@Stateless
public class WarCustomerLightQueryService extends QueryService<WarCustomerLight> {
    public WarCustomerLightQueryService() {
        super(WarCustomerLight.class, WarCustomerLight.FIND_ALL);
    }

    @EJB
    private FlowPermissionService flowPermissionService;
    @EJB
    private WarAgentQueryService warAgentQueryService;

    @POST
    @SkipCheck("action")
    @Path("find_by_assigned_agent")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WarCustomerLight> getByAssignedAgent(@QueryParam("agentId") Long agentId) {
        if (agentId == null && flowPermissionService.hasProfileEJB(WarConstants.AGENT_PROFILE)) {
            agentId = warAgentQueryService.findAgentByUsername(getUserSession().getFlowUser().getUsername()).getId();
        }
        List<WarCustomerLight> warCustomerLights = new ArrayList<>();
        try {
            setNamedQuery(WarCustomerLight.FIND_BY_ASSIGNED_AGENT);
            putParam("agentId", agentId);
            warCustomerLights = doGetResultList();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), agentId);
        }

        return warCustomerLights;
    }

    @POST
    @Path("/find_by_user_level")
    @Produces("application/json")
    public ResultDataModel<WarCustomerLight> queryList(@HeaderParam("Authorization") String authorization) {
        ResultDataModel<WarCustomerLight> resultDataModel = new ResultDataModel<>();
        try {

            FlowSessionHelper.Promise promise = session.isAuthorized(authorization);

            if (promise.getOk()) {

                DataTables dataTables = ProjectHelper.getDataTableFromQuery(request);

                CriteriaBuilder cb = entityManager.getCriteriaBuilder();

                CriteriaQuery<WarCustomerLight> criteriaQuery = cb.createQuery(WarCustomerLight.class);

                Root<WarCustomerLight> root = criteriaQuery.from(WarCustomerLight.class);

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

                if (promise.getFlowUserGroup().getGroupName().equals(WarConstants.AGENT_REGIONAL_MANAGER_GROUP)) {
                    WarAgent warAgent = warAgentQueryService.findAgentByUsername(promise.getFlowUser().getUsername());
                    criteriaQuery.where(cb.equal(cb.lower(root.get("regionCode")), warAgent.getRegion()));
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


                TypedQuery<WarCustomerLight> tTypedQuery = entityManager.createQuery(criteriaQuery);

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
                if (promise.getFlowUserGroup().getGroupName().equals(WarConstants.AGENT_REGIONAL_MANAGER_GROUP)) {
                    WarAgent warAgent = warAgentQueryService.findAgentByUsername(promise.getFlowUser().getUsername());
                    countQuery.where(cb.equal(cb.lower(root.get("regionCode")), warAgent.getRegion()));
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
