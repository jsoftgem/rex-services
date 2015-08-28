package com.jsofttechnologies.rexwar.services.management;

import com.google.common.collect.Sets;
import com.jsofttechnologies.rexwar.model.management.WarCustomerRegion;
import com.jsofttechnologies.rexwar.model.management.WarCustomerTag;
import com.jsofttechnologies.rexwar.services.data.WarRegionQueryService;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jerico on 3/7/2015.
 */
@Path("services/war/customer_tag_crud")
@Stateless
public class WarCustomerTagCrudService extends CrudService<WarCustomerTag, Long> {

    @EJB
    private WarRegionQueryService warRegionQueryService;

    @EJB
    private WarCustomerTagQueryService warCustomerTagQueryService;

    public WarCustomerTagCrudService() {
        super(WarCustomerTag.class);
    }


    @Override
    protected WarCustomerTag preCreateValidation(WarCustomerTag warCustomerTag) throws Exception {

        if (warCustomerTag.getRegionId() == null && warCustomerTag.getRegionCode() != null) {
            WarCustomerRegion region = warRegionQueryService.findByCode(warCustomerTag.getRegionCode());
            warCustomerTag.setRegionId(region.getId());
        }

        return warCustomerTag;
    }

    @Override
    protected WarCustomerTag preUpdateValidation(WarCustomerTag warCustomerTag) throws Exception {
        if (warCustomerTag.getRegionId() == null && warCustomerTag.getRegionCode() != null) {
            WarCustomerRegion region = warRegionQueryService.findByCode(warCustomerTag.getRegionCode());
            warCustomerTag.setRegionId(region.getId());
        }
        return warCustomerTag;
    }


    @Path("save_top_schools/{agentId:[0-9][0-9]*}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveTopSchools(WarCustomerTag[] customerTags, @PathParam("agentId") Long agentId) {

        Response response = null;

        if (customerTags != null) {
            List<WarCustomerTag> originalList = warCustomerTagQueryService.getByAssignedAgent(agentId);
            List<WarCustomerTag> newList = Arrays.asList(customerTags);

            Set<WarCustomerTag> differenceSet = Sets.symmetricDifference(new HashSet<>(originalList), new HashSet<>(newList));

            Set<WarCustomerTag> toBeRemoved = new HashSet<>();

            if (!differenceSet.isEmpty()) {
                for (WarCustomerTag warCustomerTag : differenceSet) {
                    if (warCustomerTag.getToBeUpdated() != null && warCustomerTag.getToBeUpdated()) {
                        if (warCustomerTag.getId() == null) {
                            create(warCustomerTag);
                        } else {
                            update(warCustomerTag, warCustomerTag.getId());
                        }
                    } else if (warCustomerTag.getToBeRemoved() != null && warCustomerTag.getToBeRemoved()) {
                        toBeRemoved.add(warCustomerTag);
                    }
                }
            } else {
                for (WarCustomerTag warCustomerTag : newList) {
                    response = update(warCustomerTag, warCustomerTag.getId());
                }
            }


            if (!toBeRemoved.isEmpty()) {
                StringBuilder builder = new StringBuilder();
                builder.append("delete from WarCustomerTag e where e.id in (");
                WarCustomerTag[] remove = toBeRemoved.toArray(new WarCustomerTag[toBeRemoved.size()]);
                for (int i = 0; i < remove.length; i++) {
                    if (i == remove.length - 1) {
                        builder.append(remove[i].getId());
                    } else {
                        builder.append(remove[i].getId());
                        builder.append(",");
                    }

                }
                builder.append(")");
                currentEntityManager.createQuery(builder.toString()).executeUpdate();
                currentEntityManager.clear();
            }


        }

        return response;
    }

}
