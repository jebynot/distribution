package com.vsc.ws.rs.property.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vsc.context.Context;
import com.vsc.context.ContextPlaceHolder;
import com.vsc.model.availability.request.VSCAvailRQ;
import com.vsc.model.availability.response.VSCAvailRS;
import com.vsc.model.property.summary.request.VSCPropertyListRQ;
import com.vsc.model.property.summary.response.VSCPropertyListRS;
import com.vsc.model.search.request.VSCSearchRQ;
import com.vsc.model.search.response.Error;
import com.vsc.model.search.response.Errors;
import com.vsc.model.search.response.Success;
import com.vsc.model.search.response.VSCSearchRS;
import com.vsc.validation.validator.PropertySearchValidator;
import com.vsc.ws.rs.common.BaseService;
import com.vsc.ws.rs.property.PropertyService;


public class PropertyServiceImpl extends BaseService implements PropertyService {

	@Autowired
	private RestTemplate restTemplate;
	@Value("${property.search.api.url}")
	private String esbSearchURL;
	@Value("${property.list.api.url}")
	private String esbPropertyListURL;
	@Value("${property.avail.api.url}")
	private String esbAvailURL;
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
		searchRS.setTimeStamp(new Date().toString());
		Context context = ContextPlaceHolder.getContext();
		String transactionID = getTransactionID();
		
		if (errors != null) {
			for(int i=1; i<errors.getError().size(); i++) {
				Error error1 = errors.getError().get(i);
				Error error2 = errors.getError().get(i-1);
				if(error1.getCode().equalsIgnoreCase(error2.getCode())){
					errors.getError().remove(error1);
				}
			}
			searchRS.setErrors(errors);
			searchRS.setSuccess(null);
			searchRS.setTransactionID(transactionID);
			searchRS.setTimeStamp(sdf.format(new Date()));
			return Response.ok(searchRS).build();
		} else {
			searchRS.setSuccess(new Success());
		}
		
		ResponseEntity<VSCSearchRS> responseEntity= restTemplate.postForEntity(esbSearchURL, searchRQ, VSCSearchRS.class);
		searchRS = responseEntity.getBody();
		searchRS.setTransactionID(transactionID);
		searchRS.setTimeStamp(sdf.format(new Date()));
		return Response.ok(searchRS).build();
	}

	@Override
	public Response getPopertyList(VSCPropertyListRQ propertyListRQ, String type) {
		com.vsc.model.property.summary.response.Errors errors = (com.vsc.model.property.summary.response.Errors)propertySearchValidator.validate(propertyListRQ);
		VSCPropertyListRS propertyListRS = new VSCPropertyListRS(); 
		propertyListRS.setTimeStamp(new Date().toString());
		Context context = ContextPlaceHolder.getContext();
		String transactionID = context.getTransactionId();
		if (errors != null) {
			for(int i=1; i<errors.getError().size(); i++){
				com.vsc.model.property.summary.response.Error error1 = errors.getError().get(i);
				com.vsc.model.property.summary.response.Error error2 = errors.getError().get(i-1);
				if(error1.getCode().equalsIgnoreCase(error2.getCode())){
					errors.getError().remove(error1);
				}
			}
			propertyListRS.setErrors(errors);
			propertyListRS.setSuccess(null);
			propertyListRS.setTransactionID(transactionID);
			propertyListRS.setTimeStamp(sdf.format(new Date()));
			return Response.ok(propertyListRS).build();
		} else {
			propertyListRS.setSuccess(new com.vsc.model.property.summary.response.Success());
		}
		
		ResponseEntity<VSCPropertyListRS> responseEntity = restTemplate.postForEntity(esbPropertyListURL, propertyListRQ, VSCPropertyListRS.class);
		propertyListRS = responseEntity.getBody();
		propertyListRS.setTransactionID(transactionID);
		propertyListRS.setTimeStamp(sdf.format(new Date()));
		
		return Response.ok(propertyListRS).build();
	}
	
	@Override
	public Response getavailability(VSCAvailRQ availRQ, String type) {
		com.vsc.model.availability.response.Errors errors = (com.vsc.model.availability.response.Errors)propertySearchValidator.validate(availRQ);
		VSCAvailRS availRS = new VSCAvailRS();
		availRS.setTimeStamp(new Date().toString());
		Context context = ContextPlaceHolder.getContext();
		String transactionID = context.getTransactionId();
		
		if (errors != null) {
			for(int i=1; i<errors.getError().size(); i++) {
				com.vsc.model.availability.response.Error error1 = errors.getError().get(i);
				com.vsc.model.availability.response.Error error2 = errors.getError().get(i-1);
				if(error1.getCode().equalsIgnoreCase(error2.getCode())){
					errors.getError().remove(error1);
				}
			}
			availRS.setErrors(errors);
			availRS.setSuccess(null);
			availRS.setTransactionID(transactionID);
			availRS.setTimeStamp(sdf.format(new Date()));
			return Response.ok(availRS).build();
		} else {
			availRS.setSuccess(new com.vsc.model.availability.response.Success());
		}
		
		ResponseEntity<VSCAvailRS> responseEntity= restTemplate.postForEntity(esbAvailURL, availRQ, VSCAvailRS.class);
		availRS = responseEntity.getBody();
		availRS.setTransactionID(transactionID);
		availRS.setTimeStamp(sdf.format(new Date()));
		return Response.ok(availRS).build();
	}

}
