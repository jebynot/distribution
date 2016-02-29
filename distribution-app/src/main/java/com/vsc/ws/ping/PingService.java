package com.vsc.ws.ping;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by jebynot on 2/26/16.
 */
@Path("/ping")
public interface PingService {

    @Path("/ping.html")
    @GET
    public String ping();
}
