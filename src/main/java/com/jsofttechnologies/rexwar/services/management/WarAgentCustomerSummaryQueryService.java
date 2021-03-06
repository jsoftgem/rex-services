package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.rexwar.model.activity.WarSchoolYear;
import com.jsofttechnologies.rexwar.model.management.WarCustomer;
import com.jsofttechnologies.rexwar.model.reports.WarAgentActivitySummary;
import com.jsofttechnologies.rexwar.model.reports.WarAgentCustomerSummary;
import com.jsofttechnologies.rexwar.services.activity.WarSchoolYearQueryService;
import com.jsofttechnologies.services.util.FlowService;
import com.jsofttechnologies.util.ProjectHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerico on 3/11/2015.
 */
@Path("services/war/agent_customer_summary_query")
@Stateless
public class WarAgentCustomerSummaryQueryService extends FlowService {

    @EJB
    private WarCustomerQueryService warCustomerQueryService;

    @EJB
    private WarSchoolYearQueryService schoolYearQueryService;

    @GET
    @Path("customer_summary/{customer}")
    public Response getCustomerSummary(@QueryParam("schoolYear") Long schoolYearId, @PathParam("customer") Long customer) {
        Response response = null;

        try {
            JSONObject responseJSON = new JSONObject();
            if (schoolYearId != null) {
                List<WarAgentCustomerSummary> warAgentCustomerSummaryList = storedProcedures.callAgentCustomerSummary(customer, schoolYearId);
                responseJSON.put("chart", new JSONObject(creatChart(warAgentCustomerSummaryList)));
                WarSchoolYear schoolYear = schoolYearQueryService.getById(schoolYearId);
                responseJSON.put("schoolYear", new JSONObject(schoolYear));

            }

            WarCustomer warCustomer = warCustomerQueryService.getById(customer);
            responseJSON.put("customer", new JSONObject(warCustomer));
            if (warCustomer.getBuyingProcess() != null) {
                ((JSONObject) responseJSON.get("customer")).put("buyingProcess", warCustomer.getBuyingProcess().toString());
            } else {
                ((JSONObject) responseJSON.get("customer")).put("buyingProcess", "N/A");
            }
            if (warCustomer.getNatureOfPurchase() != null) {
                ((JSONObject) responseJSON.get("customer")).put("natureOfPurchase", warCustomer.getNatureOfPurchase().toString());
            } else {
                ((JSONObject) responseJSON.get("customer")).put("natureOfPurchase", "N/A");
            }
            if (warCustomer.getOwnership() != null) {
                ((JSONObject) responseJSON.get("customer")).put("ownership", warCustomer.getOwnership().toString());
            } else {
                ((JSONObject) responseJSON.get("customer")).put("ownership", "N/A");
            }
            List<WarAgentActivitySummary> warAgentActivitySummaryList = storedProcedures.callAgentActivitySummary(customer, schoolYearId);
            JSONObject activityJSON = new JSONObject();
            ArrayList<String> labels = new ArrayList<>();
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
            activityJSON.put("labels", new JSONArray(labels.toArray(new String[labels.size()])));
            activityJSON.put("activities", new JSONArray(warAgentActivitySummaryList.toArray(new WarAgentActivitySummary[warAgentActivitySummaryList.size()])));
            responseJSON.put("activitySummary", activityJSON);

            response = Response.ok(responseJSON.toString(), MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
            response = ProjectHelper.error(e.getMessage());
            exceptionSummary.handleException(e, getClass());
        }


        return response;
    }


    private String creatChart(List<WarAgentCustomerSummary> customerSummaryList) {
        ProjectHelper chart = new ProjectHelper().createJson();

        Map<String, List<WarAgentCustomerSummary>> chartMap = new HashMap<>();

        ArrayList<String> labels = new ArrayList<>();

        for (WarAgentCustomerSummary customerSummary : customerSummaryList) {
            List<WarAgentCustomerSummary> customerSummariesWeekly = null;
            if (chartMap.containsKey(customerSummary.getMonth())) {
                customerSummariesWeekly = chartMap.get(customerSummary.getMonth());
                customerSummariesWeekly.add(customerSummary);
            } else {
                customerSummariesWeekly = new ArrayList<>();
                customerSummariesWeekly.add(customerSummary);
                chartMap.put(customerSummary.getMonth(), customerSummariesWeekly);
                labels.add(customerSummary.getMonth());
            }
        }


        ArrayList<Integer> set1 = new ArrayList<>();
        ArrayList<Integer> set2 = new ArrayList<>();
        ArrayList<Integer> set3 = new ArrayList<>();
        ArrayList<Integer> set4 = new ArrayList<>();
        ArrayList<Integer> set5 = new ArrayList<>();


        chart.addField("labels", new JSONArray(labels.toArray(new String[labels.size()])));

        for (String month : chartMap.keySet()) {
            //weekly
            List<WarAgentCustomerSummary> weekly = chartMap.get(month);
            for (WarAgentCustomerSummary warAgentCustomerSummary : weekly) {
                int week = Integer.valueOf(warAgentCustomerSummary.getWeek());
                switch (week) {
                    case 1:
                        set1.add(warAgentCustomerSummary.getTotalActual() != null ? warAgentCustomerSummary.getTotalActual() : 0);
                        break;
                    case 2:
                        set2.add(warAgentCustomerSummary.getTotalActual() != null ? warAgentCustomerSummary.getTotalActual() : 0);
                        break;
                    case 3:
                        set3.add(warAgentCustomerSummary.getTotalActual() != null ? warAgentCustomerSummary.getTotalActual() : 0);
                        break;
                    case 4:
                        set4.add(warAgentCustomerSummary.getTotalActual() != null ? warAgentCustomerSummary.getTotalActual() : 0);
                        break;
                    default:
                        set5.add(warAgentCustomerSummary.getTotalActual() != null ? warAgentCustomerSummary.getTotalActual() : 0);
                        break;
                }
            }
        }

        JSONArray dataSets = new JSONArray();
        for (int i = 1; i <= 5; i++) {
            JSONObject jsonObject = new JSONObject();
            switch (i) {
                case 1:
                    jsonObject.put("fillColor", "rgba(109,182,255,0.5)");
                    jsonObject.put("strokeColor", "rgba(109,182,255,0.8)");
                    jsonObject.put("highlightFill", "rgba(109,182,255,0.75)");
                    jsonObject.put("highlightStroke", "rgba(109,182,255,1)");
                    jsonObject.put("label", "Week 1");
                    jsonObject.put("data", set1);
                    break;
                case 2:
                    jsonObject.put("fillColor", "rgba(109,219,73,0.5)");
                    jsonObject.put("strokeColor", "rgba(109,219,73,0.8)");
                    jsonObject.put("highlightFill", "rgba(109,219,73,0.75)");
                    jsonObject.put("highlightStroke", "rgba(109,219,73,1)");
                    jsonObject.put("label", "Week 2");
                    jsonObject.put("data", set2);
                    break;
                case 3:
                    jsonObject.put("fillColor", "rgba(59,89,152,0.5)");
                    jsonObject.put("strokeColor", "rgba(59,89,152,0.8)");
                    jsonObject.put("highlightFill", "rgba(59,89,152,0.75)");
                    jsonObject.put("highlightStroke", "rgba(59,89,152,1)");
                    jsonObject.put("label", "Week 3");
                    jsonObject.put("data", set3);
                    break;
                case 4:
                    jsonObject.put("fillColor", "rgba(99,85,74,0.5)");
                    jsonObject.put("strokeColor", "rgba(99,85,74,0.8)");
                    jsonObject.put("highlightFill", "rgba(99,85,74,0.75)");
                    jsonObject.put("highlightStroke", "rgba(99,85,74,1)");
                    jsonObject.put("label", "Week 4");
                    jsonObject.put("data", set4);
                    break;
                default:
                    jsonObject.put("fillColor", "rgba(255,167,0,0.5)");
                    jsonObject.put("strokeColor", "rgba(255,167,0,0.8)");
                    jsonObject.put("highlightFill", "rgba(255,167,0,0.75)");
                    jsonObject.put("highlightStroke", "rgba(255,167,0,1)");
                    jsonObject.put("label", "Week 5");
                    jsonObject.put("data", set5);
                    break;
            }

            dataSets.put(jsonObject);
        }

        chart.addField("datasets", dataSets);

        return chart.buildJsonString();
    }

    private String creatActivityChart(List<WarAgentActivitySummary> activitySummaryList) {
        ProjectHelper chart = new ProjectHelper().createJson();

        Map<String, List<WarAgentCustomerSummary>> chartMap = new HashMap<>();

        for (WarAgentActivitySummary customerSummary : activitySummaryList) {

        }

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Exam Copies Distribution");
        labels.add("Invitation to Events");
        labels.add("Confirmation of Events");
        labels.add("Giveaways Distribution");
        labels.add("Delivery of Incentive/Donation");
        labels.add("Purchase Order");
        labels.add("Delivery of Add'l Order / TRM / Compli");
        labels.add("Booklist");
        labels.add("Updated Customer Info Sheet");
        labels.add("Implemented Ex-Sem");

        chart.addField("labels", new JSONArray(labels.toArray(new String[labels.size()])));


        return chart.buildJsonString();
    }

}
