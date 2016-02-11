package com.vsc.ws.legacy.rs.property;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vsc.legacy.model.availability.request.VSCAvailRQ;
import com.vsc.legacy.model.property.summary.request.VSCPropertyListRQ;
import com.vsc.legacy.model.search.request.VSCSearchRQ;

@Path("/legacy/property")
public interface PropertyService {
	@Path("/search")
	@POST
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response search(VSCSearchRQ searchRQ, @QueryParam("type") String type);
	
	
	@Path("/list")
	@POST
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getPopertyList(VSCPropertyListRQ propertyListRQ, @QueryParam("type") String type);
	
	
	@Path("/avail")
	@POST
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getavailability(VSCAvailRQ availRQ, @QueryParam("type") String type);
	
}
