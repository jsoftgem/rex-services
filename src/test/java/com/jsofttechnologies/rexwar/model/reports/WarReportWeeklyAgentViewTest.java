package com.jsofttechnologies.rexwar.model.reports;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Jerico on 2/6/2015.
 */
@RunWith(JUnit4.class)
public class WarReportWeeklyAgentViewTest {

    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManager = Persistence.createEntityManagerFactory("warpu").createEntityManager();
    }

    @Test
    public void testEntity() {
        List<WarReportWeeklyAgentView> viewList = entityManager.createNativeQuery("select * from weekly_agent_report_activity", WarReportWeeklyAgentView.class).getResultList();
        for (WarReportWeeklyAgentView warReportWeeklyAgentView : viewList) {
            System.out.println(warReportWeeklyAgentView);
        }
    }

}
