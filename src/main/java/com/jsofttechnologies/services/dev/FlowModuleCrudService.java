package com.jsofttechnologies.services.dev;

import com.jsofttechnologies.jpa.dev.FlowModule;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Path("services/flow_module_crud")
@Stateless
public class FlowModuleCrudService extends CrudService<FlowModule, Long> {

	public FlowModuleCrudService() {
		super(FlowModule.class);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected FlowModule preCreateValidation(FlowModule t) throws Exception {
		
		return t;
	}

	@Override
	protected FlowModule preUpdateValidation(FlowModule t) throws Exception {
		
		return t;
	}

}
