package com.jsofttechnologies.rexwar.services.report;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.report.generator.CSVReportGenerator;
import com.jsofttechnologies.report.generator.HtmlReportGenerator;
import com.jsofttechnologies.report.utlil.Report;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.model.management.WarAgentLight;
import com.jsofttechnologies.rexwar.model.reports.WarReportWeeklyAgentView;
import com.jsofttechnologies.rexwar.model.reports.WarReportWeeklyAgentViewCustomer;
import com.jsofttechnologies.rexwar.services.management.WarAgentLightQueryService;
import com.jsofttechnologies.rexwar.services.management.WarAgentQueryService;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.rexwar.util.contants.Month;
import com.jsofttechnologies.services.util.FlowPermissionService;
import com.jsofttechnologies.services.util.FlowService;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;
import com.jsofttechnologies.util.TableUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jerico on 2/6/2015.
 */
@Path("services/war/report_weekly_service")
@Stateless
public class WarReportWeeklyService extends FlowService {

    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;

    @EJB
    private FlowPermissionService flowPermissionService;

    @EJB
    private WarAgentQueryService warAgentQueryService;

    @EJB
    private WarAgentLightQueryService warAgentLightQueryService;

    @Path("agents")
    @GET
    @SkipCheck("action")
    public Response findWarReportWeeklyAgents(
            @QueryParam("isYear") @DefaultValue("false") Boolean isYear,
            @QueryParam("isMonth") @DefaultValue("false") Boolean isMonth,
            @QueryParam("isAgent") @DefaultValue("false") Boolean isAgent,
            @QueryParam("isRegion") @DefaultValue("false") Boolean isRegion,
            @QueryParam("year") Integer year, @QueryParam("month") Month month, @QueryParam("agentId") Long agentId,
            @QueryParam("region") String region, @QueryParam("size") @DefaultValue("25") Integer size,
            @QueryParam("start") @DefaultValue("0") Integer start, @QueryParam("tag") @DefaultValue("20") String tag
    ) {
        WarAgentLight warAgent = null;
        if (flowPermissionService.hasProfileEJB(WarConstants.AGENT_PROFILE)) {
            isAgent = Boolean.TRUE;
            isRegion = Boolean.TRUE;
            FlowSessionHelper.Promise session = getUserSession();

            WarAgent agent = warAgentQueryService.findAgentByUsername(session.getFlowUser().getUsername());
            warAgent = warAgentLightQueryService.getById(agent.getId());
            agentId = warAgent.getId();
            region = warAgent.getRegion();
        } else {
            if (isAgent) {
                warAgent = warAgentLightQueryService.getById(agentId);
            }
        }

        Response response = null;

        List<WarReportWeeklyAgentView> warReportWeeklyAgentViewList = new ArrayList<>();

        boolean complete = isYear && isMonth && isAgent && isRegion;

        String query = "select * from " + WarConstants.VIEW_WAR_REPORT_WEEKLY_AGENT + " w";

        try {
            if (!complete) {
                int count = 0;

                if (isYear) {
                    query += " where w.report_year =" + year;
                    count++;
                }

                if (isMonth) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "w.report_month = '" + month.toString() + "'";
                }

                if (isAgent) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "w.report_agent_id = " + agentId;
                }

                if (isRegion) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "lower(w.report_region) like '" + region.toLowerCase() + "'";
                }
            } else {
                query += " where w.report_year =" + year + " and w.report_month = '" + month.toString() + "' and w.report_agent_id = " + agentId + " and lower(w.report_region) ='" + region.toLowerCase() + "'";
            }


            switch (tag) {
                case "All":
                case "all":
                    query += " order by w.report_date asc";
                    break;
                case "20":
                    query += " order by w.report_total_actual desc";
                    break;
                case "50":
                    query += " order by w.report_total_actual desc";
                    break;
            }

            warReportWeeklyAgentViewList = entityManager.createNativeQuery(query, WarReportWeeklyAgentView.class)
                    /*.setFirstResult(start)
                    .setMaxResults(size)*/
                    .getResultList();


            int length = start + warReportWeeklyAgentViewList.size();


           /* List<WarReportWeeklyAgentView> nextBatch = entityManager.createNativeQuery(query, WarReportWeeklyAgentView.class)
                  *//*  .setFirstResult(length)
                    .setMaxResults(size)*//*
                    .getResultList();*/

            boolean hasNext = false;

            boolean hasPrevious = start > size;

            ProjectHelper projectHelper = TableUtil.createPaginationJson(size, start, hasNext, hasPrevious, start, length, 0)
                    .addField("tag", tag)
                    .addField("weeklyReports", new JSONArray(warReportWeeklyAgentViewList.toArray()))
                    .addField("isAgent", isAgent)
                    .addField("isYear", isYear)
                    .addField("isMonth", isMonth)
                    .addField("isRegion", isRegion)
                    .addField("year", year)
                    .addField("month", month)
                    .addField("region", region)
                    .addField("closed", Boolean.TRUE);
            if (warAgent != null) {
                projectHelper.addField("agent", new JSONObject(warAgent));
            }


            response = Response.ok(projectHelper.buildJsonString(), MediaType.APPLICATION_JSON_TYPE).build();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }


        return response;

    }

    @Path("agents_csv")
    @GET
    @SkipCheck("action")
    @Produces("application/json")
    @Report(generator = CSVReportGenerator.class)
    public List<WarReportWeeklyAgentView> exportWarReportWeeklyAgentsCSV(
            @QueryParam("isYear") @DefaultValue("false") Boolean isYear,
            @QueryParam("isMonth") @DefaultValue("false") Boolean isMonth,
            @QueryParam("isAgent") @DefaultValue("false") Boolean isAgent,
            @QueryParam("isRegion") @DefaultValue("false") Boolean isRegion,
            @QueryParam("year") Integer year, @QueryParam("month") Month month, @QueryParam("agentId") Long agentId,
            @QueryParam("region") String region, @QueryParam("size") @DefaultValue("25") Integer size,
            @QueryParam("start") @DefaultValue("0") Integer start, @QueryParam("tag") @DefaultValue("20") String tag
    ) {
        WarAgentLight warAgent = null;
        if (flowPermissionService.hasProfileEJB(WarConstants.AGENT_PROFILE)) {
            isAgent = Boolean.TRUE;
            isRegion = Boolean.TRUE;
            FlowSessionHelper.Promise session = getUserSession();

            WarAgent agent = warAgentQueryService.findAgentByUsername(session.getFlowUser().getUsername());
            warAgent = warAgentLightQueryService.getById(agent.getId());
            agentId = warAgent.getId();
            region = warAgent.getRegion();
        } else {
            if (isAgent) {
                warAgent = warAgentLightQueryService.getById(agentId);
            }
        }

        List<WarReportWeeklyAgentView> warReportWeeklyAgentViewList = new ArrayList<>();

        boolean complete = isYear && isMonth && isAgent && isRegion;

        String query = "select * from " + WarConstants.VIEW_WAR_REPORT_WEEKLY_AGENT + " w";

        try {
            if (!complete) {
                int count = 0;

                if (isYear) {
                    query += " where w.report_year =" + year;
                    count++;
                }

                if (isMonth) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "w.report_month = '" + month.toString() + "'";
                }

                if (isAgent) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "w.report_agent_id = " + agentId;
                }

                if (isRegion) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "lower(w.report_region) like '" + region.toLowerCase() + "'";
                }
            } else {
                query += " where w.report_year =" + year + " and w.report_month = '" + month.toString() + "' and w.report_agent_id = " + agentId + " and lower(w.report_region) ='" + region.toLowerCase() + "'";
            }


            switch (tag) {
                case "All":
                case "all":
                    query += " order by w.report_date asc";
                    break;
                case "20":
                    query += " order by w.report_total_actual desc";
                    break;
                case "50":
                    query += " order by w.report_total_actual desc";
                    break;
            }

            warReportWeeklyAgentViewList = entityManager.createNativeQuery(query, WarReportWeeklyAgentView.class)
                    /*.setFirstResult(start)
                    .setMaxResults(size)*/
                    .getResultList();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }


        return warReportWeeklyAgentViewList;
    }

    @Path("agents_html")
    @GET
    @SkipCheck("action")
    @Produces("application/json")
    @Report
    public List<WarReportWeeklyAgentView> exportWarReportWeeklyAgentsHTML(
            @QueryParam("isYear") @DefaultValue("false") Boolean isYear,
            @QueryParam("isMonth") @DefaultValue("false") Boolean isMonth,
            @QueryParam("isAgent") @DefaultValue("false") Boolean isAgent,
            @QueryParam("isRegion") @DefaultValue("false") Boolean isRegion,
            @QueryParam("year") Integer year, @QueryParam("month") Month month, @QueryParam("agentId") Long agentId,
            @QueryParam("region") String region, @QueryParam("size") @DefaultValue("25") Integer size,
            @QueryParam("start") @DefaultValue("0") Integer start, @QueryParam("tag") @DefaultValue("20") String tag
    ) {
        WarAgentLight warAgent = null;
        if (flowPermissionService.hasProfileEJB(WarConstants.AGENT_PROFILE)) {
            isAgent = Boolean.TRUE;
            isRegion = Boolean.TRUE;
            FlowSessionHelper.Promise session = getUserSession();

            WarAgent agent = warAgentQueryService.findAgentByUsername(session.getFlowUser().getUsername());
            warAgent = warAgentLightQueryService.getById(agent.getId());
            agentId = warAgent.getId();
            region = warAgent.getRegion();
        } else {
            if (isAgent) {
                warAgent = warAgentLightQueryService.getById(agentId);
            }
        }

        List<WarReportWeeklyAgentView> warReportWeeklyAgentViewList = new ArrayList<>();

        boolean complete = isYear && isMonth && isAgent && isRegion;

        String query = "select * from " + WarConstants.VIEW_WAR_REPORT_WEEKLY_AGENT + " w";

        try {
            if (!complete) {
                int count = 0;

                if (isYear) {
                    query += " where w.report_year =" + year;
                    count++;
                }

                if (isMonth) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "w.report_month = '" + month.toString() + "'";
                }

                if (isAgent) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "w.report_agent_id = " + agentId;
                }

                if (isRegion) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "lower(w.report_region) like '" + region.toLowerCase() + "'";
                }
            } else {
                query += " where w.report_year =" + year + " and w.report_month = '" + month.toString() + "' and w.report_agent_id = " + agentId + " and lower(w.report_region) ='" + region.toLowerCase() + "'";
            }


            switch (tag) {
                case "All":
                case "all":
                    query += " order by w.report_date asc";
                    break;
                case "20":
                    query += " order by w.report_total_actual desc";
                    break;
                case "50":
                    query += " order by w.report_total_actual desc";
                    break;
            }

            warReportWeeklyAgentViewList = entityManager.createNativeQuery(query, WarReportWeeklyAgentView.class)
                    /*.setFirstResult(start)
                    .setMaxResults(size)*/
                    .getResultList();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }


        return warReportWeeklyAgentViewList;
    }


    @Path("html_agents")
    @GET
    @SkipCheck("action")
    @Produces(value = "application/json")
    @Report(generator = HtmlReportGenerator.class)
    public List<WarReportWeeklyAgentView> printWarReportWeeklyAgents(
            @QueryParam("isYear") @DefaultValue("false") Boolean isYear,
            @QueryParam("isMonth") @DefaultValue("false") Boolean isMonth,
            @QueryParam("isAgent") @DefaultValue("false") Boolean isAgent,
            @QueryParam("isRegion") @DefaultValue("false") Boolean isRegion,
            @QueryParam("year") Integer year, @QueryParam("month") Month month, @QueryParam("agentId") Long agentId,
            @QueryParam("region") String region, @QueryParam("size") @DefaultValue("25") Integer size,
            @QueryParam("start") @DefaultValue("0") Integer start, @QueryParam("tag") @DefaultValue("20") String tag
    ) {
        WarAgentLight warAgent = null;
        if (flowPermissionService.hasProfileEJB(WarConstants.AGENT_PROFILE)) {
            isAgent = Boolean.TRUE;
            isRegion = Boolean.TRUE;
            FlowSessionHelper.Promise session = getUserSession();

            WarAgent agent = warAgentQueryService.findAgentByUsername(session.getFlowUser().getUsername());
            warAgent = warAgentLightQueryService.getById(agent.getId());
            agentId = warAgent.getId();
            region = warAgent.getRegion();
        } else {
            if (isAgent) {
                warAgent = warAgentLightQueryService.getById(agentId);
            }
        }

        Response response = null;

        List<WarReportWeeklyAgentView> warReportWeeklyAgentViewList = new ArrayList<>();

        boolean complete = isYear && isMonth && isAgent && isRegion;

        String query = "select * from " + WarConstants.VIEW_WAR_REPORT_WEEKLY_AGENT + " w";

        try {
            if (!complete) {
                int count = 0;

                if (isYear) {
                    query += " where w.report_year =" + year;
                    count++;
                }

                if (isMonth) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "w.report_month = '" + month.toString() + "'";
                }

                if (isAgent) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "w.report_agent_id = " + agentId;
                }

                if (isRegion) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "lower(w.report_region) like '" + region.toLowerCase() + "'";
                }
            } else {
                query += " where w.report_year =" + year + " and w.report_month = '" + month.toString() + "' and w.report_agent_id = " + agentId + " and lower(w.report_region) ='" + region.toLowerCase() + "'";
            }


            switch (tag) {
                case "All":
                case "all":
                    query += " order by w.report_date asc";
                    break;
                case "20":
                    query += " order by w.report_total_actual desc";
                    break;
                case "50":
                    query += " order by w.report_total_actual desc";
                    break;
            }

            warReportWeeklyAgentViewList = entityManager.createNativeQuery(query, WarReportWeeklyAgentView.class)
                    /*.setFirstResult(start)
                    .setMaxResults(size)*/
                    .getResultList();


        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }
        return warReportWeeklyAgentViewList;
    }


    @Path("agent_customer")
    @GET
    @SkipCheck("action")
    public Response findWarReportWeeklyAgentsCustomer(@QueryParam("month") Month month, @QueryParam("agent") Long agent, @QueryParam("year") Integer year, @QueryParam("week") Integer week, @QueryParam("region") String region) {

        Response response = null;
        List<WarReportWeeklyAgentViewCustomer> viewCustomers = new ArrayList<>();


        try {
            String query = "select * from " + WarConstants.VIEW_WAR_REPORT_WEEKLY_AGENT_CUSTOMER_VIEW + " w";
            int count = 0;

            if (month != null) {
                query += " where w.report_month = '" + month.toString() + "' ";
                count++;
            }

            if (agent != null) {
                if (count > 0) {
                    query += " and ";
                } else {
                    query += " where ";
                    count++;
                }
                query += " w.report_agent = " + agent;
            }

            if (year != null) {
                if (count > 0) {
                    query += " and ";
                } else {
                    query += " where ";
                    count++;
                }
                query += " w.report_year = " + year;
            }

            if (week != null) {
                if (count > 0) {
                    query += " and ";
                } else {
                    query += " where ";
                    count++;
                }
                query += " w.report_week = " + week;
            }


            if (region != null) {
                if (count > 0) {
                    query += " and ";
                } else {
                    query += " where ";
                    count++;
                }
                query += " lower(w.report_region) like '" + region.toLowerCase() + "'";
            }

            query += " order by w.report_date asc";

            viewCustomers = entityManager.createNativeQuery(query, WarReportWeeklyAgentViewCustomer.class).getResultList();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        response = Response.ok(viewCustomers, MediaType.APPLICATION_JSON_TYPE).build();

        return response;
    }

    @Path("print")
    @GET
    @SkipCheck("action")
    public Response printReports(@QueryParam("print") @DefaultValue("current") String print, @QueryParam("month") Month month, @QueryParam("agent") Long agent, @QueryParam("year") Integer year, @QueryParam("week") Integer week, @QueryParam("region") String region) {
        Response response = null;
        List<WarReportWeeklyAgentViewCustomer> viewCustomers = new ArrayList<>();

        try {
            String query = "select * from " + WarConstants.VIEW_WAR_REPORT_WEEKLY_AGENT_CUSTOMER_VIEW + " w";
            int count = 0;

            if (month != null) {
                query += " where w.report_month = '" + month.toString() + "' ";
                count++;
            }

            if (agent != null) {
                if (count > 0) {
                    query += " and ";
                } else {
                    query += " where ";
                    count++;
                }
                query += " w.report_agent = " + agent;
            }

            if (year != null) {
                if (count > 0) {
                    query += " and ";
                } else {
                    query += " where ";
                    count++;
                }
                query += " w.report_year = " + year;
            }

            if (week != null) {
                if (count > 0) {
                    query += " and ";
                } else {
                    query += " where ";
                    count++;
                }
                query += " w.report_week = " + week;
            }


            if (region != null) {
                if (count > 0) {
                    query += " and ";
                } else {
                    query += " where ";
                    count++;
                }
                query += " lower(w.report_region) like '" + region.toLowerCase() + "'";
            }

            query += " order by w.report_date asc";

            viewCustomers = entityManager.createNativeQuery(query, WarReportWeeklyAgentViewCustomer.class).getResultList();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }
        //Group by months

        HashMap<Month, List<WarReportWeeklyAgentViewCustomer>> monthGroup = new HashMap<>();


        for (WarReportWeeklyAgentViewCustomer warReportWeeklyAgentViewCustomer : viewCustomers) {
            if (monthGroup.containsKey(warReportWeeklyAgentViewCustomer.getMonth())) {
                monthGroup.get(warReportWeeklyAgentViewCustomer.getMonth()).add(warReportWeeklyAgentViewCustomer);
            } else {
                List<WarReportWeeklyAgentViewCustomer> newList = new ArrayList<>();
                newList.add(warReportWeeklyAgentViewCustomer);
                monthGroup.put(warReportWeeklyAgentViewCustomer.getMonth(), newList);
            }
        }


        StringBuilder builder = new StringBuilder();

        builder.append("<div>");

        if (print.equalsIgnoreCase("current")) {
            for (Month key : monthGroup.keySet()) {

            }
        }

        builder.append("</div>");

        return response;
    }

}
