package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserProfile;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.util.List;

@Stateless
@Path("services/flow_user_profile_query")
public class FlowUserProfileQueryService extends
        QueryService<FlowUserProfile> {
    /**
     *
     */
    private static final long serialVersionUID = 8369241487540553156L;

    @EJB
    private MergeExceptionSummary exceptionSummary;

    public FlowUserProfileQueryService() {
        super(FlowUserProfile.class, FlowUserProfile.FIND_ALL);
    }

    public FlowUserProfile findByProfileName(String profile) {
        FlowUserProfile flowUserProfile = null;

        try {
            setNamedQuery(FlowUserProfile.FIND_BY_NAME);
            putParam("profileName", profile);
            List<FlowUserProfile> profiles = doGetResultList();
            if (profiles != null && !profiles.isEmpty()) {
                return profiles.get(0);
            }
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        return flowUserProfile;
    }
}
