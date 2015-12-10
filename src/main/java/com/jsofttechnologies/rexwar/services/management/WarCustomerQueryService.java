package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.model.DataTables;
import com.jsofttechnologies.model.DataTablesColumn;
import com.jsofttechnologies.model.ResultDataModel;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.model.management.WarCustomer;
import com.jsofttechnologies.rexwar.util.WarConstants;
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
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jerico on 1/13/2015.
 */
@Path("services/war/customer_query")
@Stateless
public class WarCustomerQueryService extends QueryService<WarCustomer> {

    @EJB
    private WarAgentQueryService warAgentQueryService;

    public WarCustomerQueryService() {
        super(WarCustomer.class, WarCustomer.FIND_ALL);
    }


    public WarCustomer findByCustomerCode(String customerCode) {
        List<WarCustomer> customers = null;
        try {
            setNamedQuery(WarCustomer.FIND_BY_CUSTOMER_CODE);
            putParam("customerCode", customerCode);
            customers = doGetResultList();
            if (customers != null && !customers.isEmpty()) {
                return customers.get(0);
            }

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), customerCode);
        }


        return null;

    }

    @GET
    @Path("find_customers_by_user_level")
    public Response findCustomersByUserLevel(@HeaderParam("Authorization") String authorization) {
        Response response = null;

        try {

            FlowSessionHelper.Promise authorized = session.isAuthorized(authorization);

            if (authorized.getOk()) {

                FlowUserGroup flowUserGroup = authorized.getFlowUserGroup();

                switch (flowUserGroup.getGroupName()) {

                    case WarConstants.AGENT_GROUP:
                        break;
                    case WarConstants.AGENT_GENERAL_MANAGER_GROUP:
                        setNamedQuery(WarAgent.FIND_ALL);
                        response = Response.ok(doGetResultList(), MediaType.APPLICATION_JSON_TYPE).build();
                        break;
                    case WarConstants.AGENT_REGIONAL_MANAGER_GROUP:
                        WarAgent warAgent = warAgentQueryService.findAgentByUsername(authorized.getFlowUser().getUsername());
                        setNamedQuery(WarCustomer.FIND_BY_CUSTOMER_REGION);
                        putParam("region", warAgent.getRegion());
                        response = Response.ok(doGetResultList(), MediaType.APPLICATION_JSON_TYPE).build();
                        break;
                    default:
                        setNamedQuery(WarAgent.FIND_ALL);
                        response = Response.ok(doGetResultList(), MediaType.APPLICATION_JSON_TYPE).build();
                        break;
                }
            } else {
                response = authorized.getResponse();
            }


        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), authorization);
        }

        return response;
    }

    //TODO: find_customers_by_user_level
    @POST
    @Path("/")
    @Produces("application/json")
    public ResultDataModel<WarCustomer> queryList(@HeaderParam("Authorization") String authorization) {
        ResultDataModel<WarCustomer> resultDataModel = new ResultDataModel<>();
        try {

            DataTables dataTables = ProjectHelper.getDataTableFromQuery(request);

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaQuery<WarCustomer> criteriaQuery = cb.createQuery(classType);

            Root<WarCustomer> root = criteriaQuery.from(classType);


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


            TypedQuery<WarCustomer> tTypedQuery = entityManager.createQuery(criteriaQuery);

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


    public Boolean isExists(String customerCode) {
        WarCustomer customer = findByCustomerCode(customerCode);
        return customer != null;
    }


}
