package com.vsc.ws.legacy.rs.property.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.orbitz.monitoring.api.monitor.TransactionMonitor;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.vsc.legacy.model.availability.request.VSCAvailRQ;
import com.vsc.legacy.model.availability.response.VSCAvailRS;
import com.vsc.legacy.model.property.summary.request.VSCPropertyListRQ;
import com.vsc.legacy.model.property.summary.response.VSCPropertyListRS;
import com.vsc.legacy.model.search.request.VSCSearchRQ;
import com.vsc.legacy.model.search.response.Error;
import com.vsc.legacy.model.search.response.Errors;
import com.vsc.legacy.model.search.response.Success;
import com.vsc.legacy.model.search.response.VSCSearchRS;
import com.vsc.legacy.validation.validator.PropertySearchValidator;
import com.vsc.ws.legacy.rs.common.BaseService;
import com.vsc.ws.legacy.rs.property.PropertyService;


public class PropertyServiceImpl extends BaseService implements PropertyService {

	private static final Logger LOGGER = Logger
			.getLogger(PropertyServiceImpl.class);
	@Autowired
	private RestTemplate restTemplate;
	@Value("${legacy.property.search.api.url}")
	private String esbSearchURL;
	@Value("${legacy.property.list.api.url}")
	private String esbPropertyListURL;
	@Value("${legacy.property.avail.api.url}")
	private String esbAvailURL;
	@Value("${ICE_PARTNER_ID}")
	private String partnerCode;
	@Value("${ICE_CURRENCY_CODE}")
	private String currencyCode;
	private PropertySearchValidator propertySearchValidator;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
	
	public PropertyServiceImpl(PropertySearchValidator propertySearchValidator) {
		this.propertySearchValidator = propertySearchValidator;
	}

	/**
	 * The method is used to search properties with input search request
	 * 	1) VSCSearchRQ is validated with validator
	 *  2) Response is created with error list if any
	 *  3) If error list is empty, Search API exposed by ESB is invoked and response is generated with VSCSearchRS
	 *
	 * @param searchRQ
	 * @param type
	 * @return Response
	 */
	@Override
	public Response search(VSCSearchRQ searchRQ, String type) {
		Errors errors = (Errors)propertySearchValidator.validate(searchRQ);
		VSCSearchRS searchRS = new VSCSearchRS();
		String transactionID = getTransactionID();
		searchRQ.setTransactionID(transactionID);
		searchRQ.setPartnerID(partnerCode);
		
		searchRS.setTransactionID(transactionID);
		searchRS.setTimeStamp(sdf.format(new Date()));
		
		if (errors != null) {
			for(int i=0; i<errors.getError().size(); i++) {
				for(int j=i+1; j<errors.getError().size(); j++){
					Error error1 = errors.getError().get(i);
					Error error2 = errors.getError().get(j);
					if(error1.getCode().equalsIgnoreCase(error2.getCode())){
						errors.getError().remove(error2);
						j--;
					}
				}
			}
			
			searchRS.setErrors(errors);
			searchRS.setSuccess(null);
			return Response.ok(searchRS).build();
		} else {
			searchRS.setSuccess(new Success());
		}
		
		ResponseEntity<VSCSearchRS> responseEntity;
		try{
			responseEntity = restTemplate.postForEntity(esbSearchURL, searchRQ, VSCSearchRS.class);
		} catch(RestClientException exception) {
            errors = new Errors();
            Error error = new Error();
            error.setCode("006");
            error.setContent("System unavailable.");
            errors.getError().add(error);
            searchRS.setErrors(errors);
            searchRS.setSuccess(null);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(searchRS).build();
		}
		
		searchRS = responseEntity.getBody();
		searchRS.setTransactionID(transactionID);
		searchRS.setTimeStamp(sdf.format(new Date()));
		return Response.ok(searchRS).build();
	}

	@Override
	public Response getPopertyList(VSCPropertyListRQ propertyListRQ, String type) {
		com.vsc.legacy.model.property.summary.response.Errors errors = (com.vsc.legacy.model.property.summary.response.Errors)propertySearchValidator.validate(propertyListRQ);
		VSCPropertyListRS propertyListRS = new VSCPropertyListRS(); 
		String transactionID = getTransactionID();
		propertyListRQ.setTransactionID(transactionID);
		propertyListRQ.setPartnerID(partnerCode);
		propertyListRS.setTransactionID(transactionID);
		propertyListRS.setTimeStamp(sdf.format(new Date()));
		
		if (errors != null) {
			for(int i=0; i<errors.getError().size(); i++) {
				for(int j=i+1; j<errors.getError().size(); j++){
					com.vsc.legacy.model.property.summary.response.Error error1 = errors.getError().get(i);
					com.vsc.legacy.model.property.summary.response.Error error2 = errors.getError().get(j);
					if(error1.getCode().equalsIgnoreCase(error2.getCode())){
						errors.getError().remove(error2);
						j--;
					}
				}
			}
			
			propertyListRS.setErrors(errors);
			propertyListRS.setSuccess(null);
			return Response.ok(propertyListRS).build();
		} else {
			propertyListRS.setSuccess(new com.vsc.legacy.model.property.summary.response.Success());
		}
		
		ResponseEntity<VSCPropertyListRS> responseEntity;
		try{
			responseEntity = restTemplate.postForEntity(esbPropertyListURL, propertyListRQ, VSCPropertyListRS.class);
		} catch(RestClientException exception) {
            errors = new com.vsc.legacy.model.property.summary.response.Errors();
            com.vsc.legacy.model.property.summary.response.Error error = new com.vsc.legacy.model.property.summary.response.Error();
            error.setCode("006");
            error.setContent("System unavailable.");
            errors.getError().add(error);
            propertyListRS.setErrors(errors);
            propertyListRS.setSuccess(null);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(propertyListRS).build();
		}
		
		propertyListRS = responseEntity.getBody();
		propertyListRS.setTransactionID(transactionID);
		propertyListRS.setTimeStamp(sdf.format(new Date()));
		
		return Response.ok(propertyListRS).build();
	}
	
	@Override
	public Response getavailability(VSCAvailRQ availRQ, String type) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		TransactionMonitor monitor = new TransactionMonitor(PropertyServiceImpl.class.getName() + ".getavailability");
		com.vsc.legacy.model.availability.response.Errors errors = (com.vsc.legacy.model.availability.response.Errors)propertySearchValidator.validate(availRQ);
		VSCAvailRS availRS = new VSCAvailRS();
		String transactionID = getTransactionID();
		availRQ.setTransactionID(transactionID);
		availRQ.setPartnerID(partnerCode);
		availRS.setTransactionID(transactionID);
		availRS.setTimeStamp(sdf.format(new Date()));
		availRS.setCurrency(currencyCode);
		
		if (errors != null) {
			
			for(int i=0; i<errors.getError().size(); i++) {
				for(int j=i+1; j<errors.getError().size(); j++){
					com.vsc.legacy.model.availability.response.Error error1 = errors.getError().get(i);
					com.vsc.legacy.model.availability.response.Error error2 = errors.getError().get(j);
					if(error1.getCode().equalsIgnoreCase(error2.getCode())){
						errors.getError().remove(error2);
						j--;
					}
				}
			}
			
			availRS.setErrors(errors);
			availRS.setSuccess(null);
			monitor.failed();
			monitor.done();
			return Response.ok(availRS).build();
		} else {
			availRS.setSuccess(new com.vsc.legacy.model.availability.response.Success());
		}
		
		ResponseEntity<VSCAvailRS> responseEntity;
		try{
			responseEntity = restTemplate.postForEntity(esbAvailURL, availRQ, VSCAvailRS.class);
			monitor.succeeded();
		} catch(RestClientException exception) {
            errors = new com.vsc.legacy.model.availability.response.Errors();
            com.vsc.legacy.model.availability.response.Error error = new com.vsc.legacy.model.availability.response.Error();
            error.setCode("006");
            error.setContent("System unavailable.");
            errors.getError().add(error);
            availRS.setCurrency("USD");
            availRS.setErrors(errors);
            availRS.setSuccess(null);

			stopWatch.stop();
			LOGGER.debug("VSC_Avail | Time: " + stopWatch.getTime() + "ms | Transaction ID : " + transactionID + " | Partner ID : "+partnerCode);
			monitor.failed();

            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(availRS).build();
		} finally {
			monitor.done();
		}

		availRS = responseEntity.getBody();
		availRS.setCurrency(currencyCode);
		availRS.setTransactionID(transactionID);
		availRS.setTimeStamp(sdf.format(new Date()));

		stopWatch.stop();
		LOGGER.debug("VSC_Avail | Time: " + stopWatch.getTime() + "ms | Transaction ID : " + transactionID + " | Partner ID : "+partnerCode);
		return Response.ok(availRS).build();
	}

}
