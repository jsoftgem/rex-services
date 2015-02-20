package com.jsofttechnologies.rexwar.model.reports;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Jerico on 2/7/2015.
 */
@RunWith(JUnit4.class)
public class WarReportWeeklyAgentViewCustomerTest {
    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManager = Persistence.createEntityManagerFactory("warpu").createEntityManager();
    }

    @Test
    public void testEntity() {
        List<WarReportWeeklyAgentViewCustomer> viewList = entityManager.createNativeQuery("select * from weekly_agent_report_activity_customer_day", WarReportWeeklyAgentViewCustomer.class).getResultList();
        for (WarReportWeeklyAgentViewCustomer warReportWeeklyAgentViewCustomer : viewList) {
            System.out.println(warReportWeeklyAgentViewCustomer);
        }
    }
}
