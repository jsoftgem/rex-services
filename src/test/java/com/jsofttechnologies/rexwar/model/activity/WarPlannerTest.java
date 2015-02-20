/**
 * Created by Jerico on 2/17/2015.
 */
package com.jsofttechnologies.rexwar.model.activity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by Jerico on 2/17/2015.
 */
@RunWith(JUnit4.class)
public class WarPlannerTest {
    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManager = Persistence.createEntityManagerFactory("warpu").createEntityManager();
    }

    @Test
    public void testWarPlanner() {
    }


}
