package com.jsofttechnologies.rexwar.services.report;

import com.jsofttechnologies.ejb.FlowUserManager;
import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.rexwar.model.activity.WarSchoolYear;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.model.reports.WarReportMonthlyCustomerView;
import com.jsofttechnologies.rexwar.services.activity.WarSchoolYearQueryService;
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
import java.util.*;

/**
 * Created by Jerico on 2/23/2015.
 */
@Path("services/war/report_monthly_service")
@Stateless
public class WarReportMonthlyService extends FlowService {

    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;

    @EJB
    private FlowPermissionService flowPermissionService;
    @EJB
    private WarAgentQueryService warAgentQueryService;
    @EJB
    private WarSchoolYearQueryService schoolYearQueryService;
    @EJB
    private FlowUserManager flowUserManager;


    @GET
    @SkipCheck("action")
    @Path("customer_summary/{school_year}")
    public Response findWarReportPerSchoolYear(@PathParam("school_year") Long schoolYear, @QueryParam("agent") Long agent, @QueryParam("regionCode") String regionCode, @QueryParam("tag") @DefaultValue("all") String tag) {
        Response response = null;
        List<WarReportMonthlyCustomerView> warReportMonthlyCustomerViewList = new ArrayList<>();

        try {
            if (flowPermissionService.hasProfileEJB(WarConstants.AGENT_PROFILE)) {
                FlowSessionHelper.Promise session = getUserSession();
                WarAgent warAgent = warAgentQueryService.findAgentByUsername(session.getFlowUser().getUsername());
                agent = warAgent.getId();
                regionCode = warAgent.getRegion();
            }


            String query = "select * from " + WarConstants.VIEW_WAR_REPORT_MONTHLY_CUSTOMER + " w";


            int count = 0;

            if (schoolYear != null && schoolYear > 0) {
                query += " where w.report_school_year =" + schoolYear;
                count++;
            }


            if (regionCode != null && !regionCode.isEmpty()) {
                if (count > 0) {
                    query += " and ";
                } else {
                    query += " where ";
                    count++;
                }
                query += "lower(w.report_region) like '" + regionCode.toLowerCase() + "'";
            }

            if (agent != null && agent > 0) {
                if (count > 0) {
                    query += " and ";
                } else {
                    query += " where ";
                    count++;
                }
                query += "w.report_agent = " + agent;
            }

            switch (tag) {
                case "All":
                case "all":
                    query += " order by w.report_customer asc";
                    break;
                case "20":
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                    }
                    query += "w.report_tag_index <= 20 order by w.report_tag_index asc";
                    break;
                case "50":
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                    }
                    query += "w.report_tag_index <= 20 order by w.report_tag_index asc";
                    break;
            }

            warReportMonthlyCustomerViewList = entityManager.createNativeQuery(query, WarReportMonthlyCustomerView.class)
                    .getResultList();


            if (schoolYear == 0) return Response.ok().build();

            WarSchoolYear reportSchoolYear = schoolYearQueryService.getById(schoolYear);
            ProjectHelper projectHelper = new ProjectHelper();
            projectHelper.createJson();

            List<String> labels = new ArrayList<>();
            labels.add("Number of visits");
 /*           labels.add("Field Work with sales/manager");*/

            List<Month> mons = new ArrayList<>();


            int calIndex = reportSchoolYear.getPeriodMonth().getCalendar();

            for (int i = 0; i < 16; i++) {
                if (calIndex > 15) calIndex = 0;
                mons.add(Month.getMonth(calIndex));
                calIndex++;
            }

            Map<Month, Integer> monthIndex = new HashMap<>();
            for (Month month : mons) {
                labels.add(month.getShortLabel());
                monthIndex.put(month, labels.indexOf(month.getShortLabel()));
            }
            //SCHOOL ACTIVITY
            labels.add("Exam Copies Distribution");
            labels.add("Invitation to Events");
            labels.add("Confirmation of Events");
            labels.add("Giveaways Distribution");
            labels.add("Delivery of Incentive/Donation");
            labels.add("Purchase Order");
            labels.add("Follow up payment");
            labels.add("Delivery of Add'l Order / TRM / Compli");
            labels.add("Booklist");
            labels.add("Updated Customer Info Sheet");
            labels.add("Implemented Ex-Sem");
            labels.add("Customer Specific Activity");
            labels.add("Bootcamp");
            labels.add("AECON");
            labels.add("CEAP");
            labels.add("Collection and PR");
            projectHelper.addField("labels", new JSONArray(labels.toArray(new String[labels.size()])));


            Map<String, Set<WarReportMonthlyCustomerView>> regionGroup = new HashMap<>();
            Map<AgentCustomerKey, List<WarReportMonthlyCustomerView>> customerGroup = new HashMap<>();

            for (WarReportMonthlyCustomerView reportMonthlyCustomerView : warReportMonthlyCustomerViewList) {
                if (!regionGroup.containsKey(reportMonthlyCustomerView.getRegion())) {
                    regionGroup.put(reportMonthlyCustomerView.getRegion(), new HashSet<>());
                }
                if (!customerGroup.containsKey(new AgentCustomerKey(reportMonthlyCustomerView.getAgent(), reportMonthlyCustomerView.getCustomerId(), null, null))) {
                    customerGroup.put(new AgentCustomerKey(reportMonthlyCustomerView.getAgent(), reportMonthlyCustomerView.getCustomerId(), reportMonthlyCustomerView.getCustomerName(), reportMonthlyCustomerView.getIndex()), new ArrayList<>());
                }
                regionGroup.get(reportMonthlyCustomerView.getRegion()).add(reportMonthlyCustomerView);
                customerGroup.get(new AgentCustomerKey(reportMonthlyCustomerView.getAgent(), reportMonthlyCustomerView.getCustomerId(), null, null)).add(reportMonthlyCustomerView);
            }
            JSONArray regionJsonArray = new JSONArray();
            for (String region : regionGroup.keySet()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("region", region);
                JSONArray agentJsonArray = new JSONArray();
                Map<Long, JSONObject> agentJSONMap = new HashMap<>();
                List<WarReportMonthlyCustomerView> agentCustomerViews = new ArrayList<>(regionGroup.get(region));
                if (!tag.equals("all")) {
                    Collections.sort(agentCustomerViews, new Comparator<WarReportMonthlyCustomerView>() {
                        @Override
                        public int compare(WarReportMonthlyCustomerView o1, WarReportMonthlyCustomerView o2) {
                            return o1.getIndex().compareTo(o2.getIndex());
                        }
                    });
                }

                for (WarReportMonthlyCustomerView agentCustomerView : agentCustomerViews) {
                    JSONObject agentJSONObject = null;
                    if (agentJSONMap.containsKey(agentCustomerView.getAgent())) {
                        agentJSONObject = agentJSONMap.get(agentCustomerView.getAgent());
                    } else {
                        agentJSONObject = new JSONObject();
                        agentJSONMap.put(agentCustomerView.getAgent(), agentJSONObject);
                        agentJSONObject.put("materialsAdvisor", agentCustomerView.getMaterialsAdvisor());
                        agentJSONObject.put("agentId", agentCustomerView.getAgent());
                        JSONArray customerJsonArray = new JSONArray();
                        agentJSONObject.put("customers", customerJsonArray);
                        agentJsonArray.put(agentJSONObject);
                    }

                    JSONArray customerJsonArray = agentJSONObject.getJSONArray("customers");

                    JSONObject customerJSONObject = new JSONObject();
                    Integer[] dataTemplate = new Integer[29];
                    for (int i = 0; i < dataTemplate.length; i++) {
                        dataTemplate[i] = 0;
                    }
                    //TODO: label colors
                    customerJSONObject.put("fillColor", "rgba(109,219,73,0.5)");
                    customerJSONObject.put("strokeColor", "rgba(109,219,73,0.8)");
                    customerJSONObject.put("highlightFill", "rgba(109,219,73,0.75)");
                    customerJSONObject.put("highlightStroke", "rgba(109,219,73,1)");
                    customerJSONObject.put("label", agentCustomerView.getCustomerName());
                    customerJSONObject.put("id", agentCustomerView.getCustomerId());
                    customerJSONObject.put("top", agentCustomerView.getIndex());

                    int totalVisited = 0;

                    for (WarReportMonthlyCustomerView warReportMonthlyCustomerView : customerGroup.get(new AgentCustomerKey(agentCustomerView.getAgent(), agentCustomerView.getCustomerId(), null, null))) {
                        totalVisited += warReportMonthlyCustomerView.getCustomerFrequency();
                        dataTemplate[monthIndex.get(warReportMonthlyCustomerView.getMonth())] = warReportMonthlyCustomerView.getCustomerFrequency();
                       /* dataTemplate[labels.indexOf("Field Work with sales/manager")] += warReportMonthlyCustomerView.getWorkedWith();*/
                        dataTemplate[labels.indexOf("Exam Copies Distribution")] += warReportMonthlyCustomerView.getEcd();
                        dataTemplate[labels.indexOf("Invitation to Events")] += warReportMonthlyCustomerView.getIte();
                        dataTemplate[labels.indexOf("Confirmation of Events")] += warReportMonthlyCustomerView.getCoe();
                        dataTemplate[labels.indexOf("Giveaways Distribution")] += warReportMonthlyCustomerView.getGd();
                        dataTemplate[labels.indexOf("Delivery of Incentive/Donation")] += warReportMonthlyCustomerView.getDoi();
                        dataTemplate[labels.indexOf("Purchase Order")] += warReportMonthlyCustomerView.getPo();
                        dataTemplate[labels.indexOf("Follow up payment")] += warReportMonthlyCustomerView.getFp();
                        dataTemplate[labels.indexOf("Delivery of Add'l Order / TRM / Compli")] += warReportMonthlyCustomerView.getDaotrc();
                        dataTemplate[labels.indexOf("Booklist")] += warReportMonthlyCustomerView.getBookList();
                        dataTemplate[labels.indexOf("Updated Customer Info Sheet")] += warReportMonthlyCustomerView.getUcis();
                        dataTemplate[labels.indexOf("Implemented Ex-Sem")] += warReportMonthlyCustomerView.getIes();
                        dataTemplate[labels.indexOf("Customer Specific Activity")] += warReportMonthlyCustomerView.getCustomerSpecificActivity();
                        dataTemplate[labels.indexOf("Bootcamp")] += warReportMonthlyCustomerView.getBootcamp();
                        dataTemplate[labels.indexOf("AECON")] += warReportMonthlyCustomerView.getAecon();
                        dataTemplate[labels.indexOf("CEAP")] += warReportMonthlyCustomerView.getCeap();
                        dataTemplate[labels.indexOf("Collection and PR")] += warReportMonthlyCustomerView.getCollectionAndPr();
                    }
                    dataTemplate[0] = totalVisited;
                    customerJSONObject.put("data", new JSONArray(dataTemplate));
                    customerJsonArray.put(customerJSONObject);
                }


                jsonObject.put("agents", agentJsonArray);
                regionJsonArray.put(jsonObject);
            }

            projectHelper.addField("regions", regionJsonArray);


            response = Response.ok(projectHelper.buildJsonString(), MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        return response;
    }


    private class AgentCustomerKey {
        private Long agent;
        private Long customerId;
        private String customerName;
        private Integer index;

        public AgentCustomerKey(Long agent, Long customerId, String customerName, Integer index) {
            this.agent = agent;
            this.customerId = customerId;
            this.customerName = customerName;
            this.index = index;
        }

        public Long getAgent() {
            return agent;
        }

        public void setAgent(Long agent) {
            this.agent = agent;
        }

        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AgentCustomerKey that = (AgentCustomerKey) o;

            if (agent != null ? !agent.equals(that.agent) : that.agent != null) return false;
            if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = agent != null ? agent.hashCode() : 0;
            result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
            return result;
        }
    }

    @GET
    @SkipCheck("action")
    @Path("customers")
    public Response findWarReportMonthlyCustomer(
            @QueryParam("isYear") @DefaultValue("false") Boolean isYear,
            @QueryParam("isMonth") @DefaultValue("false") Boolean isMonth,
            @QueryParam("isAgent") @DefaultValue("false") Boolean isAgent,
            @QueryParam("isRegion") @DefaultValue("false") Boolean isRegion,
            @QueryParam("isCustomer") @DefaultValue("false") Boolean isCustomer,
            @QueryParam("schoolYear") Long schoolYear, @QueryParam("month") Month month, @QueryParam("agentId") Long agentId,
            @QueryParam("region") String region, @QueryParam("size") @DefaultValue("25") Integer size, @QueryParam("customerId") Long customerId,
            @QueryParam("start") @DefaultValue("0") Integer start, @QueryParam("tag") @DefaultValue("20") String tag) {
        Response response = null;


        if (flowPermissionService.hasProfileEJB(WarConstants.AGENT_PROFILE)) {
            isAgent = Boolean.TRUE;
            isRegion = Boolean.TRUE;
            FlowSessionHelper.Promise session = getUserSession();
            WarAgent warAgent = warAgentQueryService.findAgentByUsername(session.getFlowUser().getUsername());
            agentId = warAgent.getId();
            region = warAgent.getRegion();
        }

        List<WarReportMonthlyCustomerView> warReportMonthlyCustomerViewList = new ArrayList<>();

        boolean complete = isYear && isMonth && isAgent && isRegion && isCustomer;

        String query = "select * from " + WarConstants.VIEW_WAR_REPORT_MONTHLY_CUSTOMER + " w";

        try {
            if (!complete) {
                int count = 0;

                if (isYear) {
                    query += " where w.report_school_year =" + schoolYear;
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
                    query += "w.report_agent = " + agentId;
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

                if (isCustomer) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "w.report_customer_id =" + customerId;
                }
            } else {
                query += " where w.report_school_year =" + schoolYear + " and w.report_month = '" + month.toString() + "' and w.report_agent_id = " + agentId + " and lower(w.report_region) ='" + region.toLowerCase() + "' and w.report_customer_id = " + customerId;
            }


            switch (tag) {
                case "All":
                case "all":
                    query += " order by w.report_customer asc";
                    break;
                case "20":
                    query += " order by w.report_tag_index asc";
                    break;
                case "50":
                    query += " order by w.report_tag_index asc";
                    break;
            }

            warReportMonthlyCustomerViewList = entityManager.createNativeQuery(query, WarReportMonthlyCustomerView.class)
                    .getResultList();


            int length = start + warReportMonthlyCustomerViewList.size();


         /*   List<WarReportMonthlyCustomerView> nextBatch = entityManager.createNativeQuery(query, WarReportMonthlyCustomerView.class)
                    .setFirstResult(length)
                    .setMaxResults(size)
                    .getResultList();
*/
            boolean hasNext = false;

            boolean hasPrevious = start > size;

            ProjectHelper projectHelper = TableUtil.createPaginationJson(size, start, hasNext, hasPrevious, start, length, 0)
                    .addField("tag", tag)
                    .addField("monthlyReports", warReportMonthlyCustomerViewList.toArray())
                    .addField("isAgent", isAgent)
                    .addField("isYear", isYear)
                    .addField("isMonth", isMonth)
                    .addField("isRegion", isRegion)
                    .addField("isCustomer", isCustomer)
                    .addField("schoolYear", schoolYear)
                    .addField("month", month != null ? month.toString() : null)
                    .addField("region", region)
                    .addField("agentId", agentId)
                    .addField("customerId", customerId)
                    .addField("length", length);


            response = Response.ok(projectHelper.buildJsonString(), MediaType.APPLICATION_JSON_TYPE).build();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }
        return response;
    }
}
