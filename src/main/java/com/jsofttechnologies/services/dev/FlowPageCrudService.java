package com.jsofttechnologies.services.dev;



import com.jsofttechnologies.jpa.dev.FlowPage;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Path("services/flow_page_crud")
@Stateless
public class FlowPageCrudService extends CrudService<FlowPage, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlowPageCrudService() {
		super(FlowPage.class);
	}

	@Override
	protected FlowPage preCreateValidation(FlowPage t) throws Exception {
		
		return t;
	}

	@Override
	protected FlowPage preUpdateValidation(FlowPage t) throws Exception {
		
		return t;
	}

}
