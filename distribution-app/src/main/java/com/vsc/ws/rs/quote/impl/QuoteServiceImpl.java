package com.vsc.ws.rs.quote.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.vsc.model.quote.request.VSCQuoteRQ;
import com.vsc.model.quote.response.Error;
import com.vsc.model.quote.response.Errors;
import com.vsc.model.quote.response.Success;
import com.vsc.model.quote.response.VSCQuoteRS;
import com.vsc.validation.validator.QuoteServiceValidator;
import com.vsc.ws.rs.common.BaseService;
import com.vsc.ws.rs.quote.QuoteService;

public class QuoteServiceImpl extends BaseService implements QuoteService {
	
	@Autowired
	private RestTemplate restTemplate;
	@Value("${quote.quotes.api.url}")
	private String esbQuoteURL;
	@Value("${ICE_PARTNER_ID}")
	private String partnerCode;
	@Value("${ICE_CURRENCY_CODE}")
	private String currencyCode;
	
	private QuoteServiceValidator quoteServiceValidator;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
	
	public QuoteServiceImpl(QuoteServiceValidator quoteServiceValidator) {
		this.quoteServiceValidator = quoteServiceValidator;
	}
	
	@Override
	public Response quote(VSCQuoteRQ quoteRQ, String type) {
		Errors errors = (Errors)quoteServiceValidator.validate(quoteRQ);
		VSCQuoteRS quoteRS = new VSCQuoteRS();
		String transactionID = getTransactionID();
		quoteRQ.setTransactionID(transactionID);
		quoteRQ.setPartnerID(partnerCode);
		
		quoteRS.setTransactionID(transactionID);
		quoteRS.setTimeStamp(sdf.format(new Date()));
		quoteRS.setCurrency(currencyCode);
		
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
			quoteRS.setErrors(errors);
			quoteRS.setSuccess(null);
			return Response.ok(quoteRS).build();
		} else {
			quoteRS.setSuccess(new Success());
		}
		
		ResponseEntity<VSCQuoteRS> responseEntity;
		try{
			responseEntity = restTemplate.postForEntity(esbQuoteURL, quoteRQ, VSCQuoteRS.class);
		} catch(RestClientException exception) {
            errors = new Errors();
            Error error = new Error();
            error.setCode("006");
            error.setContent("System unavailable.");
            errors.getError().add(error);
            quoteRS.setErrors(errors);
            quoteRS.setSuccess(null);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(quoteRS).build();
		}
		quoteRS = responseEntity.getBody();
		quoteRS.setTransactionID(transactionID);
		quoteRS.setTimeStamp(sdf.format(new Date()));
		quoteRS.setCurrency(currencyCode);
		return Response.ok(quoteRS).build();
	
	}	
	
}
