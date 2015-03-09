package com.jsofttechnologies.rexwar.services.activity;

import com.jsofttechnologies.rexwar.model.activity.WarActivityNote;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 3/8/2015.
 */
@Path("services/war/activity_note_crud")
@Stateless
public class WarActivityNoteCrudService extends CrudService<WarActivityNote, Long> {

    public WarActivityNoteCrudService() {
        super(WarActivityNote.class);
    }

    @Override
    protected WarActivityNote preCreateValidation(WarActivityNote warActivityNote) throws Exception {
        return warActivityNote;
    }

    @Override
    protected WarActivityNote preUpdateValidation(WarActivityNote warActivityNote) throws Exception {
        return warActivityNote;
    }
}
