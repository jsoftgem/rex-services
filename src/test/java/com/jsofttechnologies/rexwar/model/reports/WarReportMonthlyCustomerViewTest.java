package com.jsofttechnologies.rexwar.model.reports;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Jerico on 2/23/2015.
 */

@RunWith(JUnit4.class)
public class WarReportMonthlyCustomerViewTest {
    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManager = Persistence.createEntityManagerFactory("warpu").createEntityManager();
    }

    @Test
    public void testEntity() {
        List<WarReportMonthlyCustomerView> viewList = entityManager.createNativeQuery("select * from monthly_customer_report_activtiy", WarReportMonthlyCustomerView.class).getResultList();
        for (WarReportMonthlyCustomerView warReportMonthlyCustomerView : viewList) {
            System.out.println(warReportMonthlyCustomerView);
        }
    }
}
