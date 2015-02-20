package com.jsofttechnologies.ejb;

import javax.ejb.Stateless;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jerico on 10/28/2014.
 */
@Stateless
public class MergeExceptionSummary {


    private Logger logger;

    public void handleException(Exception e, Class<?> origin) {
        logger = Logger.getLogger(origin.getName());
        logger.log(Level.WARNING, e.getMessage(), e);
    }

    public void handleException(Exception e, Class<?> origin, Object data) {
        logger = Logger.getLogger(origin.getName());
        logger.log(Level.WARNING, e.getMessage(), e);
    }
}
