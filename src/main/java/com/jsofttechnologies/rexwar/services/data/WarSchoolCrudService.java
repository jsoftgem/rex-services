/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsofttechnologies.rexwar.services.data;

import com.jsofttechnologies.interceptor.Notify;
import com.jsofttechnologies.jpa.util.FlowAlertType;
import com.jsofttechnologies.rexwar.model.tables.School;
import com.jsofttechnologies.services.util.CrudService;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 *
 * @author Jerico
 */
@Path("services/war/school_crud")
@Stateless
@Notify(alertType = FlowAlertType.NOTICE, page = "school_edit", task = "school_task")
public class WarSchoolCrudService extends CrudService<School, Long> {

    public WarSchoolCrudService() {
        super(School.class);
    }

    @Override
    protected School preCreateValidation(School t) throws Exception {
        return t;
    }

    @Override
    protected School preUpdateValidation(School t) throws Exception {
        return t;
    }

}
