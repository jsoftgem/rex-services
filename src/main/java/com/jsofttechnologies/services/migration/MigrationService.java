package com.jsofttechnologies.services.migration;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserDetail;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.model.management.WarCustomer;
import com.jsofttechnologies.rexwar.model.management.WarCustomerRegion;
import com.jsofttechnologies.rexwar.model.tables.School;
import com.jsofttechnologies.rexwar.services.data.WarRegionCrudService;
import com.jsofttechnologies.rexwar.services.data.WarRegionQueryService;
import com.jsofttechnologies.rexwar.services.data.WarSchoolCrudService;
import com.jsofttechnologies.rexwar.services.data.WarSchoolQueryService;
import com.jsofttechnologies.rexwar.services.management.WarAgentCrudService;
import com.jsofttechnologies.rexwar.services.management.WarAgentQueryService;
import com.jsofttechnologies.rexwar.services.management.WarCustomerCrudService;
import com.jsofttechnologies.rexwar.services.management.WarCustomerQueryService;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.services.admin.FlowUserProfileQueryService;
import com.jsofttechnologies.services.util.FlowService;
import com.jsofttechnologies.services.util.LoginService;
import com.jsofttechnologies.util.FileUtil;
import com.jsofttechnologies.util.PasswordHash;
import com.jsofttechnologies.util.ProjectConstants;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.WordUtils;
import org.json.JSONObject;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Jerico on 3/20/2015.
 */
@Path("services/migration_service")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class MigrationService extends FlowService {

    @Resource
    private UserTransaction userTransaction;

    @EJB
    private LoginService loginService;
    @EJB
    private WarCustomerQueryService warCustomerQueryService;
    @EJB
    private WarCustomerCrudService warCustomerCrudService;
    @EJB
    private WarAgentQueryService warAgentQueryService;
    @EJB
    private WarRegionQueryService warRegionQueryService;
    @EJB
    private WarRegionCrudService warRegionCrudService;
    @EJB
    private WarAgentCrudService warAgentCrudService;
    @EJB
    private FlowUserProfileQueryService flowUserProfileQueryService;
    @EJB
    private WarSchoolCrudService warSchoolCrudService;
    @EJB
    private WarSchoolQueryService warSchoolQueryService;

    @SkipCheck("authorization")
    @Path("csv/{folder}")
    @GET
    public Response migrate(@PathParam("folder") String folder, @QueryParam("username") String username, @QueryParam("password") String password) {
        Response response = loginService.basicAuth(username, password, request);

        if (response.getStatus() == 200) {

            JSONObject auth = new JSONObject(response.getEntity().toString());

            String authorization = ProjectConstants.AUTHENTICATION_SCHEME + " " + auth.getString("bs64auth");

            serviceMap("migration", authorization, String.class, true);

            File root = FileUtil.createFolder(ProjectConstants.FILE_SERVER_PATH);

            File migration = FileUtil.createFolder(root, ProjectConstants.FILE_MIGRATION_PATH);


            File selectedFolder = FileUtil.createFolder(migration, folder);


            if (selectedFolder.listFiles() != null && selectedFolder.listFiles().length > 0) {

                try {

                    File[] agents = selectedFolder.listFiles((dir, name) -> {
                        return name.toLowerCase().contains("agents.csv");
                    });

                    if (agents.length > 0) {
                        File agentResult = new File(selectedFolder, "agent-result.csv");
                        if (!agentResult.exists()) {
                            agentResult.createNewFile();
                        }
                        File agent = agents[0];

                        CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(agentResult), CSVFormat.EXCEL);

                        if (agent != null) {
                            CSVParser parser = CSVParser.parse(agent, Charset.defaultCharset(), CSVFormat.EXCEL);
                            int count = 0;
                            userTransaction.begin();

                            for (CSVRecord csvRecord : parser) {
                                if (csvRecord.get(0).isEmpty()) {
                                    continue;
                                }
                                try {
                                    WarAgent warAgent = null;
                                    WarCustomerRegion warRegion = null;

                                    warAgent = warAgentQueryService.findByInitials(csvRecord.get(0));

                                    if (warAgent == null) {
                                        warAgent = new WarAgent();
                                        warAgent.setInitials(csvRecord.get(0));
                                    }

                                    for (int i = 0; i < csvRecord.size(); i++) {
                                        switch (i) {
                                            case 0:
                                                warAgent.setIsManager(Boolean.FALSE);
                                                warAgent.setActive(Boolean.TRUE);
                                                break;

                                            case 1:
                                                String name = csvRecord.get(i);
                                                if (name != null && !name.isEmpty()) {
                                                    FlowUserDetail flowUserDetail = null;
                                                    FlowUser flowUser = null;
                                                    if (warAgent.getId() != null) {
                                                        flowUserDetail = warAgent.getUser().getFlowUserDetail();
                                                        flowUser = warAgent.getUser();
                                                    } else {
                                                        flowUserDetail = new FlowUserDetail();
                                                        flowUser = new FlowUser();
                                                        flowUser.setFlowUserDetail(flowUserDetail);
                                                        warAgent.setUser(flowUser);
                                                    }

                                                    String fullName = WordUtils.capitalize(name);
                                                    String user = name.replaceAll(" ", "_").toLowerCase();
                                                    flowUserDetail.setFullName(fullName);
                                                    flowUserDetail.setSecretQuestion("To be filled out by user.");
                                                    flowUserDetail.setSecretAnswer(user);
                                                    flowUser.setUsername(user);
                                                    flowUser.setEmail(user + "@" + WarConstants.EMAIL_SUFFIX);
                                                    flowUser.setPassword(PasswordHash.createHash(user));

                                                }

                                                break;

                                            case 2:
                                                String regionCode = csvRecord.get(i);
                                                if (regionCode != null && !regionCode.isEmpty()) {

                                                    warRegion = warRegionQueryService.findByCode(regionCode);
                                                    if (warRegion == null) {
                                                        warRegion = new WarCustomerRegion();
                                                    }
                                                    warAgent.setRegion(regionCode);

                                                }
                                                break;

                                            case 3:
                                                String regionName = csvRecord.get(i);
                                                if (regionName != null && !regionName.isEmpty()) {
                                                    if (warRegion != null) {
                                                        warRegion.setRegionName(regionName);
                                                        warRegion.setRegionCode(warAgent.getRegion());
                                                    }
                                                }
                                                break;
                                            case 4:
                                                String rsm = csvRecord.get(i);
                                                if (rsm != null && !rsm.isEmpty()) {
                                                    warAgent.setIsManager(Boolean.TRUE);
                                                }
                                                break;

                                            case 5:
                                                String email = csvRecord.get(i);
                                                if (email != null && !email.isEmpty()) {
                                                    warAgent.getUser().setEmail(email);
                                                }
                                                break;
                                        }
                                    }


                                    if (warAgent.getId() == null) {
                                        warAgentCrudService.create(warAgent);
                                    } else {
                                        warAgentCrudService.update(warAgent, warAgent.getId());
                                    }

                                    if (warRegion.getId() == null) {
                                        warRegionCrudService.create(warRegion);
                                    } else {
                                        warRegionCrudService.update(warRegion, warRegion.getId());
                                    }

                                    csvPrinter.printRecord(csvRecord);
                                    if (count % 1000 == 0) {
                                        userTransaction.commit();
                                        userTransaction.begin();
                                    }
                                } catch (Exception ex) {
                                    exceptionSummary.handleException(ex, getClass(), csvRecord);
                                }

                                csvPrinter.flush();

                            }
                            userTransaction.commit();
                            csvPrinter.close();
                        }
                    }


                    File[] customers = selectedFolder.listFiles((dir, name) -> {
                        return name.toLowerCase().contains("customers.csv");
                    });
                    File customerResult = new File(selectedFolder, "customer-result.log");
                    if (customers.length > 0) {

                        FileWriter fileWriter = new FileWriter(customerResult);
                        File customer = customers[0];
                        if (customer != null) {
                            CSVParser parser = CSVParser.parse(customer, Charset.defaultCharset(), CSVFormat.EXCEL);

                            List<CSVRecord> recordList = parser.getRecords();
                            Integer totalRecords = recordList.size();
                            int count = 0;
                            userTransaction.begin();
                            for (CSVRecord csvRecord : recordList) {
                                try {
                                    String customerCode = csvRecord.get(0);
                                    if (customerCode.isEmpty()) continue;
                                    WarCustomer warCustomer = null;
                                    School school = null;
                                    if (!warCustomerQueryService.isExists(customerCode.trim())) {
                                        warCustomer = new WarCustomer();
                                        warCustomer.setCustomerCode(customerCode);
                                    } else {
                                        warCustomer = warCustomerQueryService.findByCustomerCode(customerCode.trim());
                                        school = warCustomer.getSchool();
                                    }


                                    String schoolName = csvRecord.get(1);

                                    String agentInitials = csvRecord.get(2);

                                    String address1 = csvRecord.get(3);

                                    String address2 = csvRecord.get(4);

                                    String congregation = csvRecord.get(5);

                                    String diocese = csvRecord.get(6);

                                    String association = csvRecord.get(7);

                                    String email = null;

                                    if (csvRecord.size() > 8) {
                                        email = csvRecord.get(8);
                                    }
                                    String landLine = null;

                                    if (csvRecord.size() > 9) {
                                        landLine = csvRecord.get(9);
                                    }
                                    if (agentInitials != null && !agentInitials.trim().isEmpty() && !agentInitials.trim().equalsIgnoreCase("vacant")) {
                                        WarAgent warAgent = warAgentQueryService.findByInitials(agentInitials);
                                        if (warAgent != null) {
                                            if (schoolName != null && !schoolName.trim().isEmpty()) {
                                                if (warCustomer.getId() == null) {
                                                    school = warSchoolQueryService.findBySchoolName(schoolName);
                                                    warCustomer.setOwnerAgentId(warAgent.getId());
                                                }
                                                if (school == null) {
                                                    school = new School();
                                                }
                                                school.setName(schoolName);
                                                if (email != null && !email.trim().isEmpty()) {
                                                    school.setEmail(email);
                                                } else {
                                                    school.setEmail("xxx@xxx.xxx");
                                                }
                                                if (landLine != null && !landLine.trim().isEmpty()) {
                                                    school.setLandline(landLine);
                                                } else {
                                                    school.setLandline("0");
                                                }

                                                school.setAddressLine1(address1);
                                                school.setAddressLine2(address2);

                                                if (congregation != null && !congregation.equalsIgnoreCase("0")) {
                                                    warCustomer.setCongregation(congregation);
                                                }

                                                if (diocese != null && !diocese.equalsIgnoreCase("0")) {
                                                    warCustomer.setDiocese(diocese);
                                                }
                                                if (association != null && !association.equalsIgnoreCase("0")) {
                                                    warCustomer.setAssociation(association);
                                                }
                                                if (school.getId() != null) {
                                                    warSchoolCrudService.create(school);
                                                } else {
                                                    warSchoolCrudService.update(school, school.getId());
                                                }
                                                warCustomer.setSchool(school);

                                                if (warCustomer.getId() == null) {
                                                    warCustomerCrudService.create(warCustomer);
                                                } else {
                                                    warCustomerCrudService.update(warCustomer, warCustomer.getId());
                                                }

                                            }
                                        } else {
                                            fileWriter.append(agentInitials + " do not exists. - " + csvRecord.get(0) + "\n");
                                        }
                                    }
                                    fileWriter.flush();
                                    if (count % 1000 == 0) {
                                        userTransaction.commit();
                                        userTransaction.begin();
                                    }
                                    count++;
                                } catch (Exception e) {
                                    exceptionSummary.handleException(e, getClass(), csvRecord);
                                }
                            }

                            fileWriter.append("Done " + count + "/" + totalRecords);
                            fileWriter.close();
                            userTransaction.commit();
                        }
                    }
                } catch (Exception e) {
                    exceptionSummary.handleException(e, getClass(), folder);
                }

            }

        }

        return Response.ok("<h1>Done! :)", MediaType.TEXT_HTML_TYPE).build();
    }


}
