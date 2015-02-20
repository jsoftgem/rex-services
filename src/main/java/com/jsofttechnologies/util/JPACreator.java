package com.jsofttechnologies.util;

import javax.persistence.Persistence;

/**
 * Created by Jerico on 6/15/2014.
 */
public class JPACreator {

    public static void main(String... args) {
        Persistence.createEntityManagerFactory(ProjectConstants.MAIN_PU).createEntityManager();
    }

}
