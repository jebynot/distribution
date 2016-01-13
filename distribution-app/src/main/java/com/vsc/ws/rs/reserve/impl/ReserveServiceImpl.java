package com.vsc.ws.rs.reserve.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vsc.context.Context;
import com.vsc.context.ContextPlaceHolder;
import com.vsc.model.reserve.cancel.request.VSCCancelRQ;
import com.vsc.model.reserve.cancel.response.VSCCancelRS;
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
	
	private ReserveServiceValidator reserveServiceValidator;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
	
	public ReserveServiceImpl(ReserveServiceValidator reserveServiceValidator) {
		this.reserveServiceValidator = reserveServiceValidator;
	}
	
	@Override
	public Response reserve(VSCReserveRQ reserveRQ, String type) {
		setContextData(reserveRQ);
		Errors errors = (Errors)reserveServiceValidator.validate(reserveRQ);
		VSCReserveRS reserveRS = new VSCReserveRS();
		reserveRS.setTimeStamp(new Date().toString());
		String transactionID = getTransactionID();
		reserveRS.setTransactionID(transactionID);
		if (errors != null) {
			for(int i=1; i<errors.getError().size(); i++) {
				Error error1 = errors.getError().get(i);
				Error error2 = errors.getError().get(i-1);
				if(error1.getCode().equalsIgnoreCase(error2.getCode())){
					errors.getError().remove(error1);
				}
			}
			reserveRS.setErrors(errors);
			reserveRS.setSuccess(null);
			
			reserveRS.setTimeStamp(sdf.format(new Date()));
			return Response.ok(reserveRS).build();
		} else {
			reserveRS.setSuccess(new Success());
		}
		
		ResponseEntity<VSCReserveRS> responseEntity= restTemplate.postForEntity(esbReserveURL, reserveRQ, VSCReserveRS.class);
		reserveRS = responseEntity.getBody();
		reserveRS.setTransactionID(transactionID);
		reserveRS.setTimeStamp(sdf.format(new Date()));
		return Response.ok(reserveRS).build();
	
	}
	
	@Override
	public Response cancel(VSCCancelRQ cancelRQ, String type){
		com.vsc.model.reserve.cancel.response.Errors errors = new com.vsc.model.reserve.cancel.response.Errors();
		VSCCancelRS cancelRS = new VSCCancelRS();
		cancelRS.setTimeStamp(new Date().toString());
		Context context = ContextPlaceHolder.getContext();
		String transactionID = context.getTransactionId();
		cancelRS.setErrors(errors);
		ResponseEntity<VSCCancelRS> responseEntity = restTemplate.postForEntity(esbReserveCancelURL, cancelRQ, VSCCancelRS.class);
		cancelRS = responseEntity.getBody();
		cancelRS.setTransactionID(transactionID);
		cancelRS.setTimeStamp(sdf.format(new Date()));
		return Response.ok(cancelRS).build();
	}

}