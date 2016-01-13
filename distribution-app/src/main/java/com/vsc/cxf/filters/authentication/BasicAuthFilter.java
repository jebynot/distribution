package com.vsc.cxf.filters.authentication;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.utils.ExceptionUtils;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.vsc.context.ContextPlaceHolder;
import com.vsc.services.authentication.AuthenticationService;

public class BasicAuthFilter implements ContainerRequestFilter{
	
	private static final Logger LOGGER = Logger.getLogger(BasicAuthFilter.class);
	
	private static final String BASIC = "BASIC";
	
	private MessageContext messageContext;  
	
	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		LOGGER.debug("inside filter");
		
		List<String> authHeaders = messageContext.getHttpHeaders().getRequestHeader(HttpHeaders.AUTHORIZATION);
        if (authHeaders == null || authHeaders.size() != 1) {
            LOGGER.debug("No Authorization header is available");
            throw ExceptionUtils.toNotAuthorizedException(null, getFaultResponse());
        }
        
		Message message = JAXRSUtils.getCurrentMessage();
		AuthorizationPolicy policy = (AuthorizationPolicy)message.get(AuthorizationPolicy.class);
		if(policy == null){
			LOGGER.debug("No Authorization header is available");
			throw ExceptionUtils.toNotAuthorizedException(null, getFaultResponse());
		}
		
		String userName = policy.getUserName();
		String password = policy.getPassword();
		
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			LOGGER.debug("No Authorization header is available");
			throw ExceptionUtils.toNotAuthorizedException(null, getFaultResponse());
		}
		
		Boolean isAuthenticated = authenticationService.authenticate(userName, password);
		
		if(!isAuthenticated){
			LOGGER.debug("Not valid credentials");
			throw ExceptionUtils.toNotAuthorizedException(null, getFaultResponse());
		}
		
		com.vsc.context.Context context = ContextPlaceHolder.getContext();
		context.setAttribute(com.vsc.context.Context.PARTNER_ID, userName);
		
	}

	@Context
    public void setMessageContext(MessageContext context) {
        this.messageContext = context;
    }
	
	private static Response getFaultResponse() {
        return JAXRSUtils.toResponseBuilder(401).build();
    }
}
