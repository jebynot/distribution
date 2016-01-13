package com.vsc.ws.rs.quote.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vsc.context.Context;
import com.vsc.context.ContextPlaceHolder;
import com.vsc.model.quote.request.VSCQuoteRQ;
import com.vsc.model.quote.response.Errors;
import com.vsc.model.quote.response.Error;
import com.vsc.model.quote.response.Success;
import com.vsc.model.quote.response.VSCQuoteRS;
import com.vsc.validation.validator.QuoteServiceValidator;
import com.vsc.validation.validator.ReserveServiceValidator;
import com.vsc.ws.rs.common.BaseService;
import com.vsc.ws.rs.quote.QuoteService;
import com.vsc.ws.rs.reserve.ReserveService;

public class QuoteServiceImpl extends BaseService implements QuoteService {
	
	@Autowired
	private RestTemplate restTemplate;
	@Value("${quote.quotes.api.url}")
	private String esbQuoteURL;
	
	private QuoteServiceValidator quoteServiceValidator;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
	
	public QuoteServiceImpl(QuoteServiceValidator quoteServiceValidator) {
		this.quoteServiceValidator = quoteServiceValidator;
	}
	
	@Override
	public Response quote(VSCQuoteRQ quoteRQ, String type) {
		Errors errors = (Errors)quoteServiceValidator.validate(quoteRQ);
		VSCQuoteRS quoteRS = new VSCQuoteRS();
		quoteRS.setTimeStamp(new Date().toString());
		String transactionID = getTransactionID();

		if (errors != null) {
			for(int i=1; i<errors.getError().size(); i++) {
				Error error1 = errors.getError().get(i);
				Error error2 = errors.getError().get(i-1);
				if(error1.getCode().equalsIgnoreCase(error2.getCode())){
					errors.getError().remove(error1);
				}
			}
			quoteRS.setErrors(errors);
			quoteRS.setSuccess(null);
			quoteRS.setTransactionID(transactionID);
			quoteRS.setTimeStamp(sdf.format(new Date()));
			return Response.ok(quoteRS).build();
		} else {
			quoteRS.setSuccess(new Success());
		}
		
		ResponseEntity<VSCQuoteRS> responseEntity= restTemplate.postForEntity(esbQuoteURL, quoteRQ, VSCQuoteRS.class);
		quoteRS = responseEntity.getBody();
		quoteRS.setTransactionID(transactionID);
		quoteRS.setTimeStamp(sdf.format(new Date()));
		return Response.ok(quoteRS).build();
	
	}	
	
}
