package com.vsc.ws.legacy.rs.reserve.impl;

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

import com.vsc.legacy.model.reserve.cancel.request.VSCCancelRQ;
import com.vsc.legacy.model.reserve.cancel.response.VSCCancelRS;
import com.vsc.legacy.model.reserve.details.request.VSCRetrieveResDetailsRQ;
import com.vsc.legacy.model.reserve.details.response.VSCRetrieveResDetailsRS;
import com.vsc.legacy.model.reserve.request.VSCReserveRQ;
import com.vsc.legacy.model.reserve.response.Error;
import com.vsc.legacy.model.reserve.response.Errors;
import com.vsc.legacy.model.reserve.response.Success;
import com.vsc.legacy.model.reserve.response.VSCReserveRS;
import com.vsc.legacy.validation.validator.ReserveServiceValidator;
import com.vsc.ws.legacy.rs.common.BaseService;
import com.vsc.ws.legacy.rs.reserve.ReserveService;

public class ReserveServiceImpl extends BaseService implements ReserveService {

	private static final Logger LOGGER = Logger
			.getLogger(ReserveServiceImpl.class);
	@Autowired
	private RestTemplate restTemplate;
	@Value("${legacy.reserve.reservation.api.url}")
	private String esbReserveURL;
	@Value("${legacy.reserve.cancel.api.url}")
	private String esbReserveCancelURL;
	@Value("${legacy.reserve.retrieveReservation.api.url}")
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
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		TransactionMonitor monitor = new TransactionMonitor(ReserveServiceImpl.class.getName() + ".reserve");
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
			monitor.failed();
			monitor.done();
			return Response.ok(reserveRS).build();
		} else {
			reserveRS.setSuccess(new Success());
		}
		
		ResponseEntity<VSCReserveRS> responseEntity;
		try{
			responseEntity = restTemplate.postForEntity(esbReserveURL, reserveRQ, VSCReserveRS.class);
			monitor.succeeded();
		} catch(RestClientException exception) {
            errors = new Errors();
            Error error = new Error();
            error.setCode("006");
            error.setContent("System unavailable.");
            errors.getError().add(error);
            reserveRS.setErrors(errors);
            reserveRS.setSuccess(null);

			stopWatch.stop();
			monitor.failed();
			LOGGER.debug("VSC_Reserve | Time: " + stopWatch.getTime() + "ms | Transaction ID : " + transactionID + " | Partner ID : "+partnerCode);

            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(reserveRS).build();
		} finally {
			monitor.done();
		}
		reserveRS = responseEntity.getBody();
		reserveRS.setTransactionID(transactionID);
		reserveRS.setTimeStamp(sdf.format(new Date()));
		reserveRS.setCurrency(currencyCode);

		stopWatch.stop();
		LOGGER.debug("VSC_Reserve | Time: " + stopWatch.getTime() + "ms | Transaction ID : " + transactionID + " | Partner ID : "+partnerCode);

		return Response.ok(reserveRS).build();
	
	}
	
	@Override
	public Response cancel(VSCCancelRQ cancelRQ, String type){
		com.vsc.legacy.model.reserve.cancel.response.Errors errors = (com.vsc.legacy.model.reserve.cancel.response.Errors)reserveServiceValidator.validate(cancelRQ);
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
					com.vsc.legacy.model.reserve.cancel.response.Error error1 = errors.getError().get(i);
					com.vsc.legacy.model.reserve.cancel.response.Error error2 = errors.getError().get(j);
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
			cancelRS.setSuccess(new com.vsc.legacy.model.reserve.cancel.response.Success());
		}
		
		ResponseEntity<VSCCancelRS> responseEntity;
		try{
			responseEntity = restTemplate.postForEntity(esbReserveCancelURL, cancelRQ, VSCCancelRS.class);
		} catch(RestClientException exception) {
            errors = new com.vsc.legacy.model.reserve.cancel.response.Errors();
            com.vsc.legacy.model.reserve.cancel.response.Error error = new com.vsc.legacy.model.reserve.cancel.response.Error();
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
		com.vsc.legacy.model.reserve.details.response.Errors errors = (com.vsc.legacy.model.reserve.details.response.Errors)reserveServiceValidator.validate(retrieveResDetailsRQ);
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
					com.vsc.legacy.model.reserve.details.response.Error error1 = errors.getError().get(i);
					com.vsc.legacy.model.reserve.details.response.Error error2 = errors.getError().get(j);
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
			retrieveResDetailsRS.setSuccess(new com.vsc.legacy.model.reserve.details.response.Success());
		}
		
		ResponseEntity<VSCRetrieveResDetailsRS> responseEntity;
		try{
			responseEntity = restTemplate.postForEntity(esbRetrieveReservationURL, retrieveResDetailsRQ, VSCRetrieveResDetailsRS.class);
		} catch(RestClientException exception) {
            errors = new com.vsc.legacy.model.reserve.details.response.Errors();
            com.vsc.legacy.model.reserve.details.response.Error error = new com.vsc.legacy.model.reserve.details.response.Error();
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