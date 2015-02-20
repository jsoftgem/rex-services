package com.jsofttechnologies.services.util;

import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerico on 11/28/2014.
 */
@Path("factories/flow")
@Singleton
public class FlowFactories extends FlowService {

    private final List<SizeObject> SIZES = new ArrayList<>();

    public FlowFactories() {
        initSizes();
    }

    static class SizeObject {
        private String label;
        private Integer value;


        public SizeObject(String label, Integer value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    private void initSizes() {
        if (SIZES.isEmpty()) {
            SIZES.add(new SizeObject("25%", 25));
            SIZES.add(new SizeObject("50%", 50));
            SIZES.add(new SizeObject("75%", 75));
            SIZES.add(new SizeObject("100%", 100));
        }
    }

    @GET
    @Path("SIZES")
    @Produces("application/json")
    public List<SizeObject> getSizes() {
        return SIZES;
    }

}
