package com.jsofttechnologies.rexwar.services.session.planner;

import com.jsofttechnologies.rexwar.model.activity.WarActivity;
import com.jsofttechnologies.rexwar.model.activity.WarPlanner;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Jerico on 2/16/2015.
 */
@XmlRootElement
public class WarPlannerSession implements Serializable {

    private WarPlanner warPlanner;
    private WarActivity[] activities;

    public WarPlanner getWarPlanner() {
        return warPlanner;
    }

    public void setWarPlanner(WarPlanner warPlanner) {
        this.warPlanner = warPlanner;
    }

    public WarActivity[] getActivities() {
        return activities;
    }

    public void setActivities(WarActivity[] activities) {
        this.activities = activities;
    }
}
