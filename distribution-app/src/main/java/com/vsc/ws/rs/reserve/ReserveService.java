package com.vsc.ws.rs.reserve;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vsc.model.reserve.cancel.request.VSCCancelRQ;
import com.vsc.model.reserve.request.VSCReserveRQ;

@Path("/reserve")
public interface ReserveService {

	@Path("/reservation")
	@POST
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response reserve(VSCReserveRQ reserveRQ, @QueryParam("type") String type);
	
	@Path("/cancel")
	@POST
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response cancel(VSCCancelRQ cancelRQ, @QueryParam("type") String type);
	
	
}