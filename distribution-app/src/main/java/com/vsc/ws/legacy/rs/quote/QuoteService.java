package com.vsc.ws.legacy.rs.quote;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vsc.legacy.model.quote.request.VSCQuoteRQ;

@Path("/legacy/quote")
public interface QuoteService {

	@Path("/quotes")
	@POST
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response quote(VSCQuoteRQ quoteRQ, @QueryParam("type") String type);
	
	
}