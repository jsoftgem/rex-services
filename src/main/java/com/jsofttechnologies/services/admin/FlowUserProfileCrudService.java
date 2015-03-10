package com.jsofttechnologies.services.admin;


import com.jsofttechnologies.ejb.FlowUserManager;
import com.jsofttechnologies.jpa.admin.FlowUserProfile;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Stateless
@Path("services/flow_user_profile_crud")
public class FlowUserProfileCrudService extends
        CrudService<FlowUserProfile, Long> {

    /**
     *
     */
    private static final long serialVersionUID = 6994107743536886025L;

    public FlowUserProfileCrudService() {
        super(FlowUserProfile.class);
    }

    @EJB
    public FlowUserManager flowUserManager;

    @Override
    public FlowUserProfile preCreateValidation(FlowUserProfile t)
            throws Exception {
        return t;
    }

    @Override
    public FlowUserProfile preUpdateValidation(FlowUserProfile t)
            throws Exception {
        FlowUserProfile flowUserProfile = getInstance();
        flowUserProfile.setProfileName(t.getProfileName());
        flowUserProfile.setDescription(t.getDescription());
        return flowUserProfile;
    }

    @Override
    protected void postCreateValidation(FlowUserProfile flowUserProfile) {
        flowUserManager.refreshUserMap();
        super.postCreateValidation(flowUserProfile);
    }

    @Override
    protected void postUpdateValidation(FlowUserProfile flowUserProfile) {
        flowUserManager.refreshUserMap();
        super.postUpdateValidation(flowUserProfile);
    }
}
