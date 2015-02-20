/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsofttechnologies.ejb;

import javax.ejb.Singleton;
import java.util.logging.Logger;

/**
 *
 * @author Jerico
 */
@Singleton
public class MergeLogger {

    public Logger createLogger(Class<?> declaringClass) {
        return Logger.getLogger(declaringClass.getName());
    }

}
