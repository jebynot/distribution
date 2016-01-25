package com.vsc.ws.rs.reserve.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.vsc.model.reserve.cancel.request.VSCCancelRQ;
import com.vsc.model.reserve.cancel.response.VSCCancelRS;
import com.vsc.model.reserve.details.request.VSCRetrieveResDetailsRQ;
import com.vsc.model.reserve.details.response.VSCRetrieveResDetailsRS;
import com.vsc.model.reserve.request.VSCReserveRQ;
import com.vsc.model.reserve.response.Error;
import com.vsc.model.reserve.response.Errors;
import com.vsc.model.reserve.response.Success;
import com.vsc.model.reserve.response.VSCReserveRS;
import com.vsc.validation.validator.ReserveServiceValidator;
import com.vsc.ws.rs.common.BaseService;
import com.vsc.ws.rs.reserve.ReserveService;

public class ReserveServiceImpl extends BaseService implements ReserveService {
	
	@Autowired
	private RestTemplate restTemplate;
	@Value("${reserve.reservation.api.url}")
	private String esbReserveURL;
	@Value("${reserve.cancel.api.url}")
	private String esbReserveCancelURL;
	@Value("${reserve.retrieveReservation.api.url}")
	private String esbRetrieveReservationURL;
	@Value("${ICE_PARTNER_ID}")
	private String partnerCode;
	@Value("${ICE_CURRENCY_CODE}")
	private String currencyCode;
	
	private ReserveServiceValidator reserveServiceValidator;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
	
	public ReserveServiceImpl(ReserveServiceValidator reserveServiceValidator) {
		this.reserveServiceValidator = reserveServiceValidator;
	}
	
	@Override
	public Response reserve(VSCReserveRQ reserveRQ, String type) {
//		setContextData(reserveRQ);
		Errors errors = (Errors)reserveServiceValidator.validate(reserveRQ);
		VSCReserveRS reserveRS = new VSCReserveRS();
		String transactionID = getTransactionID();
		reserveRQ.setTransactionID(transactionID);
		reserveRQ.setPartnerID(partnerCode);
		
		reserveRS.setTransactionID(transactionID);
		reserveRS.setTimeStamp(sdf.format(new Date()));
		reserveRS.setCurrency(currencyCode);
		
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
			
			reserveRS.setErrors(errors);
			reserveRS.setSuccess(null);
			return Response.ok(reserveRS).build();
		} else {
			reserveRS.setSuccess(new Success());
		}
		
		ResponseEntity<VSCReserveRS> responseEntity;
		try{
			responseEntity = restTemplate.postForEntity(esbReserveURL, reserveRQ, VSCReserveRS.class);
		} catch(RestClientException exception) {
            errors = new Errors();
            Error error = new Error();
            error.setCode("006");
            error.setContent("System unavailable.");
            errors.getError().add(error);
            reserveRS.setErrors(errors);
            reserveRS.setSuccess(null);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(reserveRS).build();
		}
		reserveRS = responseEntity.getBody();
		reserveRS.setTransactionID(transactionID);
		reserveRS.setTimeStamp(sdf.format(new Date()));
		reserveRS.setCurrency(currencyCode);
		return Response.ok(reserveRS).build();
	
	}
	
	@Override
	public Response cancel(VSCCancelRQ cancelRQ, String type){
		com.vsc.model.reserve.cancel.response.Errors errors = (com.vsc.model.reserve.cancel.response.Errors)reserveServiceValidator.validate(cancelRQ);
		VSCCancelRS cancelRS = new VSCCancelRS();
		String transactionID = getTransactionID();
		cancelRQ.setTransactionID(transactionID);
		cancelRQ.setPartnerID(partnerCode);
		
		cancelRS.setTransactionID(transactionID);
		cancelRS.setTimeStamp(sdf.format(new Date()));
		cancelRS.setCurrency(currencyCode);
		
		if (errors != null) {
			for(int i=0; i<errors.getError().size(); i++) {
				for(int j=i+1; j<errors.getError().size(); j++){
					com.vsc.model.reserve.cancel.response.Error error1 = errors.getError().get(i);
					com.vsc.model.reserve.cancel.response.Error error2 = errors.getError().get(j);
					if(error1.getCode().equalsIgnoreCase(error2.getCode())){
						errors.getError().remove(error2);
						j--;
					}
				}
			}
			
			cancelRS.setErrors(errors);
			cancelRS.setSuccess(null);
			return Response.ok(cancelRS).build();
		} else {
			cancelRS.setSuccess(new com.vsc.model.reserve.cancel.response.Success());
		}
		
		ResponseEntity<VSCCancelRS> responseEntity;
		try{
			responseEntity = restTemplate.postForEntity(esbReserveCancelURL, cancelRQ, VSCCancelRS.class);
		} catch(RestClientException exception) {
            errors = new com.vsc.model.reserve.cancel.response.Errors();
            com.vsc.model.reserve.cancel.response.Error error = new com.vsc.model.reserve.cancel.response.Error();
            error.setCode("006");
            error.setContent("System unavailable.");
            errors.getError().add(error);
            cancelRS.setErrors(errors);
            cancelRS.setSuccess(null);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(cancelRS).build();
		}
		cancelRS = responseEntity.getBody();
		cancelRS.setTransactionID(transactionID);
		cancelRS.setTimeStamp(sdf.format(new Date()));
		cancelRS.setCurrency(currencyCode);
		return Response.ok(cancelRS).build();
	}

	@Override
	public Response retrieveReservation(VSCRetrieveResDetailsRQ retrieveResDetailsRQ, String type) {
		com.vsc.model.reserve.details.response.Errors errors = (com.vsc.model.reserve.details.response.Errors)reserveServiceValidator.validate(retrieveResDetailsRQ);
		VSCRetrieveResDetailsRS retrieveResDetailsRS = new VSCRetrieveResDetailsRS();
		String transactionID = getTransactionID();
		retrieveResDetailsRQ.setTransactionID(transactionID);
		retrieveResDetailsRQ.setPartnerID(partnerCode);
		
		retrieveResDetailsRS.setTransactionID(transactionID);
		retrieveResDetailsRS.setTimeStamp(sdf.format(new Date()));
		retrieveResDetailsRS.setCurrency(currencyCode);
		
		if (errors != null) {
			for(int i=0; i<errors.getError().size(); i++) {
				for(int j=i+1; j<errors.getError().size(); j++){
					com.vsc.model.reserve.details.response.Error error1 = errors.getError().get(i);
					com.vsc.model.reserve.details.response.Error error2 = errors.getError().get(j);
					if(error1.getCode().equalsIgnoreCase(error2.getCode())){
						errors.getError().remove(error2);
						j--;
					}
				}
			}
			
			retrieveResDetailsRS.setErrors(errors);
			retrieveResDetailsRS.setSuccess(null);
			return Response.ok(retrieveResDetailsRS).build();
		} else {
			retrieveResDetailsRS.setSuccess(new com.vsc.model.reserve.details.response.Success());
		}
		
		ResponseEntity<VSCRetrieveResDetailsRS> responseEntity;
		try{
			responseEntity = restTemplate.postForEntity(esbRetrieveReservationURL, retrieveResDetailsRQ, VSCRetrieveResDetailsRS.class);
		} catch(RestClientException exception) {
            errors = new com.vsc.model.reserve.details.response.Errors();
            com.vsc.model.reserve.details.response.Error error = new com.vsc.model.reserve.details.response.Error();
            error.setCode("006");
            error.setContent("System unavailable.");
            errors.getError().add(error);
            retrieveResDetailsRS.setErrors(errors);
            retrieveResDetailsRS.setSuccess(null);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(retrieveResDetailsRS).build();
		}
		retrieveResDetailsRS = responseEntity.getBody();
		retrieveResDetailsRS.setTransactionID(transactionID);
		retrieveResDetailsRS.setTimeStamp(sdf.format(new Date()));
		retrieveResDetailsRS.setCurrency(currencyCode);
		return Response.ok(retrieveResDetailsRS).build();
	
	}
}