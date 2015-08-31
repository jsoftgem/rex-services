package com.jsofttechnologies.v2.services.session;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.interceptor.UserInfoResource;
import com.jsofttechnologies.jpa.admin.FlowUserGroupModule;
import com.jsofttechnologies.jpa.admin.FlowUserGroupTask;
import com.jsofttechnologies.jpa.dev.FlowGroup;
import com.jsofttechnologies.jpa.dev.FlowModule;
import com.jsofttechnologies.jpa.dev.FlowUserTask;
import com.jsofttechnologies.services.admin.FlowUserGroupModuleQueryService;
import com.jsofttechnologies.services.dev.FlowUserTaskCrudService;
import com.jsofttechnologies.services.dev.FlowUserTaskQueryService;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.v2.services.FluidPlatformService;
import com.jsofttechnologies.v2.services.admin.ModuleService;
import com.jsofttechnologies.v2.services.admin.UserService;
import com.jsofttechnologies.v2.services.resource.UserResource;
import com.jsofttechnologies.v2.util.WarToken;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rickzx98 on 8/30/15.
 */
@Path("services/v2/session/module")
@Stateless
public class UserModuleService extends FluidPlatformService {


    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;

    @EJB
    private ModuleService moduleService;


    @GET
    @Path("group/modules")
    @Produces("application/json")
    public List<FlowGroup> getSessionModules(WarToken warToken) {
        String groupName = warToken.getGroup();

        List<FlowModule> flowModuleList = moduleService.getByGroup(groupName);

        Set<String> moduleGroupSet = flowModuleList.stream().map(FlowModule::getFlowGroupName).collect(Collectors.toSet());

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<FlowGroup> criteriaQuery = cb.createQuery(FlowGroup.class);

        Root<FlowGroup> groupRoot = criteriaQuery.from(FlowGroup.class);

        List<Predicate> predicates = moduleGroupSet.stream().map(gName -> cb.equal(groupRoot.get("name"), gName)).collect(Collectors.toList());

        criteriaQuery.select(groupRoot).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));


        TypedQuery<FlowGroup> tTypedQuery = entityManager.createQuery(criteriaQuery);

        List<FlowGroup> groupList = tTypedQuery.getResultList();


        for (FlowGroup flowGroup : groupList) {
            flowGroup.getFlowModules().clear();
            flowModuleList.stream().filter(module -> flowGroup.getName().equals(module.getFlowGroupName())).forEach(module -> {
                flowGroup.getFlowModules().add(module);
            });
            Collections.sort(flowGroup.getFlowModules(), new Comparator<FlowModule>() {
                @Override
                public int compare(FlowModule o1, FlowModule o2) {
                    return o1.getModuleTitle().compareTo(o2.getModuleTitle());
                }
            });
        }

        return groupList;

    }

}
