/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsofttechnologies.security;

import com.jsofttechnologies.jpa.admin.FlowSession;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.util.ProjectConstants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jerico
 */
@Stateless
public class SessionHelper {

    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;

    public Long getCurrentUserID(String sessionId) {
        //EntityManager entityManager = EntityManagerFactoryListener.createEntityManager();
        FlowSession flowSession = entityManager.find(FlowSession.class,
                sessionId);
        entityManager.clear();
        // entityManager.close();
        return flowSession.getUserId();
    }

    public FlowUser getCurrentUser(String sessionId) {
        // EntityManager entityManager = EntityManagerFactoryListener.createEntityManager();
        Long userId = getCurrentUserID(sessionId);
        FlowUser flowUser = entityManager.find(FlowUser.class, userId);
        entityManager.clear();
        // entityManager.close();
        return flowUser;
    }

    public static Map<String, String> getMergeSessionMap(String sessionValue) {
        Map<String, String> mergeSessionHeaderMap = new HashMap<>();
        String[] values = sessionValue.split("\\;");

        for (String value : values) {
            String[] keyValue = value.split("\\:");

            String key = keyValue[0];
            String kv = keyValue[1];
            if (key.contains(ProjectConstants.KEY_HEADER_MAP_MSID)) {
                key = key.replaceFirst("\\[", "");
            } else if (key.equals(ProjectConstants.KEY_HEADER_MAP_MUID)) {
                kv = kv.replaceAll("\\[", "");
                kv = kv.replaceAll("\\]", "");
            }

            mergeSessionHeaderMap.put(key, kv);
        }

        return mergeSessionHeaderMap;
    }

    public MergeSecurityContext getSecurityContext(String userId, String sessionId) {

        FlowUser flowUser = entityManager.find(FlowUser.class, Long.valueOf(userId.trim()));

        FlowSession flowSession = entityManager.createNamedQuery(FlowSession.FIND_BY_SESSIONID, FlowSession.class).setParameter("sessionId", sessionId).getSingleResult();

        entityManager.clear();

        return new MergeSecurityContext(flowUser, flowSession);
    }
}
