package com.jsofttechnologies.ejb;

import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.jpa.admin.FlowUserProfile;
import com.jsofttechnologies.services.admin.FlowUserQueryService;
import com.jsofttechnologies.util.ProjectConstants;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jerico on 10/28/2014.
 */
@Startup
@Singleton
public class FlowUserManager {


    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;
    @EJB
    private FlowUserQueryService flowUserQueryService;

    @EJB
    private MergeExceptionSummary exceptionSummary;

    private Logger logger = Logger.getLogger(FlowUserManager.class.getName());
    private Map<Long, FlowUser> userMap;
    private Map<String, Long> userIdMap;
    private Map<Long, Set<FlowUserProfile>> userProfileSetMap;
    private Map<String, String> groupMap = new HashMap<>();


    public void init() {
        logger.log(Level.INFO, "starting MergeUserManager...");


        List<FlowUser> flowUserList = entityManager.createNamedQuery(FlowUser.FIND_ALL, FlowUser.class).getResultList();

        userMap = new HashMap<>();
        userIdMap = new HashMap<>();
        for (FlowUser flowUser : flowUserList) {
            userMap.put(flowUser.getId(), flowUser);
            userIdMap.put(flowUser.getUsername(), flowUser.getId());
        }

    }
    public void updateUser(FlowUser flowUser) {
        if (userMap.containsKey(flowUser.getId())) {
            FlowUser oldUser = userMap.get(flowUser.getId());
            if (!oldUser.equals(flowUser)) {
                userIdMap.put(flowUser.getUsername(), flowUser.getId());
            }
            userMap.put(flowUser.getId(), flowUser);
        } else {
            userMap.put(flowUser.getId(), flowUser);
            userIdMap.put(flowUser.getUsername(), flowUser.getId());
        }
    }
    public void refreshUserMap() {
        entityManager.clear();
        userMap.clear();
        userIdMap.clear();
    }
    public Set<FlowUserProfile> getMergeUserProfile(String username) {
        return getUserProfileSet(getUserId(username));
    }
    public FlowUser getUser(Long id) {
        if (userMap == null) userMap = new HashMap<>();
        synchronized (userMap) {
            if (!userMap.containsKey(id)) {
                try {
                    FlowUser user = flowUserQueryService.getById(id);
                    user.getFlowUserProfileSet();
                    userMap.put(id, user);
                } catch (Exception e) {
                    exceptionSummary.handleException(e, getClass());
                }
            }
        }
        return userMap.get(id);
    }
    public Long getUserId(String username) {
        if (userIdMap == null) userIdMap = new HashMap<>();
        synchronized (userIdMap) {
            if (!userIdMap.containsKey(username)) {
                try {
                    FlowUser user = flowUserQueryService.getFlowUserByUsername(username);
                    userIdMap.put(username, user.getId());

                } catch (Exception e) {
                    exceptionSummary.handleException(e, getClass());
                }
            }
        }
        return userIdMap.get(username);
    }
    public String getGroupName(String username) {
        FlowUser user = null;
        try {
            user = entityManager.createNamedQuery(FlowUser.FIND_BY_USERNAME, FlowUser.class).setParameter("username", username).getSingleResult();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }
        if (user != null)
            return user.getFlowUserGroup().getGroupName();
        else
            return null;
    }
    public FlowUserGroup getGroup(String username) {
        FlowUser user = null;
        try {
            user = entityManager.createNamedQuery(FlowUser.FIND_BY_USERNAME, FlowUser.class).setParameter("username", username).getSingleResult();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }
        if (user != null)
            return user.getFlowUserGroup();
        else
            return null;
    }
    public Set<FlowUserProfile> getUserProfileSet(Long id) {
        if (userProfileSetMap == null) userProfileSetMap = new HashMap<>();

        if (!userProfileSetMap.containsKey(id)) {
            userProfileSetMap.put(id, getUser(id).getFlowUserProfileSet());
            logger.log(Level.INFO, "getUserProfileSet: " + getUser(id).toString());
        }

        return userProfileSetMap.get(id);
    }
    public Boolean isGroupAdmin(String username) {
        Long userId = getUserId(username);
        return getGroup(username).getOwnerUserId().equals(userId);
    }

}
