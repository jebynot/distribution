package com.vsc.ws.ping.impl;

import com.vsc.ws.ping.PingService;

/**
 * Created by jebynot on 2/26/16.
 */
public class PingServiceImpl implements PingService{

    @Override
    public String ping() {
        return "ping";
    }
}
