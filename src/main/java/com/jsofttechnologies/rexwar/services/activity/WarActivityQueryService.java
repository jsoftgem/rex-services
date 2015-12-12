package com.jsofttechnologies.rexwar.services.activity;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.model.DataTables;
import com.jsofttechnologies.model.DataTablesColumn;
import com.jsofttechnologies.model.ResultDataModel;
import com.jsofttechnologies.rexwar.model.activity.WarActivity;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.services.management.WarAgentQueryService;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.rexwar.util.contants.Month;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.services.util.QueryService;
import com.jsofttechnologies.util.ProjectHelper;
import com.jsofttechnologies.util.TableUtil;
import org.json.JSONArray;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * Created by Jerico on 1/30/2015.
 */
@Path("services/war/activity_query")
@Stateless
public class WarActivityQueryService extends QueryService<WarActivity> {
    @EJB
    private WarAgentQueryService warAgentQueryService;

    public WarActivityQueryService() {
        super(WarActivity.class, WarActivity.FIND_ALL);
    }


    @Path("/day_activities")
    @GET
    @SkipCheck("authorization")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WarActivity> getActivityDay(@QueryParam("schoolYear") Long schoolYear, @QueryParam("agent") Long agent,
                                            @QueryParam("date") Date date) {
        List<WarActivity> activities = new ArrayList<>();

        if (schoolYear != null) {
            setNamedQuery(WarActivity.FIND_BY_EVENT_SCHOOL_YEAR_DATE);
            if (agent != null) {
                setNamedQuery(WarActivity.FIND_BY_EVENT_SCHOOL_YEAR_AGENT_DATE);
                putParam("agent", agent);
            }
            putParam("schoolYear", schoolYear);
            putParam("date", date);

            activities = doGetResultList();
        }

        return activities;
    }

    @Path("/events")
    @GET
    @SkipCheck("authorization")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WarActivity> getActivity(@QueryParam("schoolYear") Long schoolYear, @QueryParam("agent") Long agent,
                                         @QueryParam("start") String start, @QueryParam("end") String end, @QueryParam("_") Long _) {
        List<WarActivity> activities = new ArrayList<>();

        if (schoolYear != null) {
            setNamedQuery(WarActivity.FIND_BY_EVENT_SCHOOL_YEAR);
            if (agent != null) {
                setNamedQuery(WarActivity.FIND_BY_EVENT_SCHOOL_YEAR_AGENT);
                putParam("agent", agent);
            }

            putParam("schoolYear", schoolYear);
            String[] splitStart = start.split("-");
            String[] splitEnd = end.split("-");

            Calendar startCal = Calendar.getInstance();
            startCal.set(Calendar.YEAR, Integer.valueOf(splitStart[0]));
            startCal.set(Calendar.MONTH, Integer.valueOf(splitStart[1]) - 1);
            startCal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(splitStart[2]));

            Calendar endCal = Calendar.getInstance();
            endCal.set(Calendar.YEAR, Integer.valueOf(splitEnd[0]));
            endCal.set(Calendar.MONTH, Integer.valueOf(splitEnd[1]) - 1);
            endCal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(splitEnd[2]));

            Date startDt = startCal.getTime();
            Date endDt = endCal.getTime();

            putParam("start", startDt);
            putParam("end", endDt);

            activities = doGetResultList();
        }

        return activities;
    }

    @POST
    @Path("activities")
    @SkipCheck("action")
    public Response getCustomerActivity(@QueryParam("schoolYearId") Long schoolYear,
                                        @QueryParam("agentId") Long agent, @QueryParam("customerId") Long customerId,
                                        @QueryParam("month") Month month, @QueryParam("week") String week,
                                        @QueryParam("size") @DefaultValue("25") Integer size,
                                        @QueryParam("start") @DefaultValue("0") Integer start) {
        Response response = null;

        String query = "select a from WarActivity a";

        if (schoolYear != null) {
            query += " where a.schoolYear=" + schoolYear;
            if (agent != null) {
                query += "and a.agentId=" + agent;
            }

            if (customerId != null) {
                query += "and a.customerMarketId=" + customerId;
            }

            if (month != null) {
                query += "and a.warPlanner.month='" + month + "'";
            }

            if (week != null && !week.equalsIgnoreCase("all")) {
                query += "and a.week=" + Integer.valueOf(week.trim());
            }

            query += " order by a.startDt asc";

            List<WarActivity> activities = entityManager.createQuery(query, WarActivity.class)
                    .setMaxResults(size).setFirstResult(start).getResultList();

            if (activities != null) {

                int length = activities.size();

                int next = start + length;

                List<WarActivity> activitiesNext = entityManager.createQuery(query, WarActivity.class)
                        .setMaxResults(size).setFirstResult(next).getResultList();

                boolean hasNext = activitiesNext != null && !activitiesNext.isEmpty();

                boolean hasPrevious = start > size;

                if (!hasNext) next = 0;

                ProjectHelper projectHelper = TableUtil.createPaginationJson(size, start, hasNext, hasPrevious, start, next, 0)
                        .addField("activities", new JSONArray(activities.toArray(new WarActivity[activities.size()])));

                response = Response.ok(projectHelper.buildJsonString(), MediaType.APPLICATION_JSON_TYPE).build();

            }

        }

        return response;

    }


    @POST
    @Path("/find_by_user_level")
    @Produces("application/json")
    public ResultDataModel<WarActivity> queryList(@HeaderParam("Authorization") String authorization) {
        ResultDataModel<WarActivity> resultDataModel = new ResultDataModel<>();
        try {

            FlowSessionHelper.Promise promise = session.isAuthorized(authorization);

            if (promise.getOk()) {


                DataTables dataTables = ProjectHelper.getDataTableFromQuery(request);

                CriteriaBuilder cb = entityManager.getCriteriaBuilder();

                CriteriaQuery<WarActivity> criteriaQuery = cb.createQuery(WarActivity.class);

                Root<WarActivity> root = criteriaQuery.from(WarActivity.class);

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
                if (promise.getFlowUserGroup().getGroupName().equals(WarConstants.AGENT_GROUP)) {
                    WarAgent warAgent = warAgentQueryService.findAgentByUsername(promise.getFlowUser().getUsername());
                    criteriaQuery.where(cb.equal(cb.lower(root.get("agentId")), warAgent.getId()));
                } else if (promise.getFlowUserGroup().getGroupName().equals(WarConstants.AGENT_REGIONAL_MANAGER_GROUP)) {
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


                TypedQuery<WarActivity> tTypedQuery = entityManager.createQuery(criteriaQuery);

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
                if (promise.getFlowUserGroup().getGroupName().equals(WarConstants.AGENT_GROUP)) {
                    WarAgent warAgent = warAgentQueryService.findAgentByUsername(promise.getFlowUser().getUsername());
                    countQuery.where(cb.equal(cb.lower(root.get("agentId")), warAgent.getId()));
                } else if (promise.getFlowUserGroup().getGroupName().equals(WarConstants.AGENT_REGIONAL_MANAGER_GROUP)) {
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
