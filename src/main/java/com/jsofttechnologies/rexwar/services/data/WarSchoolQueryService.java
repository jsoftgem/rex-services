/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsofttechnologies.rexwar.services.data;

import com.jsofttechnologies.rexwar.model.tables.School;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.util.List;

/**
 * @author Jerico
 */
@Path("services/war/school_query")
@Stateless
public class WarSchoolQueryService extends QueryService<School> {

    public WarSchoolQueryService() {
        super(School.class, School.FIND_ALL);
    }


    public School findBySchoolName(String name) {
        List<School> schoolList = null;

        try {
            setNamedQuery(School.FIND_BY_SCHOOL_NAME);
            putParam("name",name);
            schoolList = doGetResultList();
            if(schoolList!= null && !schoolList.isEmpty()){
                return schoolList.get(0);
            }

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), name);
        }


        return null;
    }
}
