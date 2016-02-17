package com.vsc.legacy.validation.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.vsc.legacy.model.quote.request.VSCQuoteRQ;
import com.vsc.legacy.model.quote.response.Errors;
import com.vsc.legacy.model.quote.response.Error;

/**
 * Created by Jerry on 24/1/15.
 */
public class QuoteServiceValidator {
	private static final Logger logger = Logger.getLogger(QuoteServiceValidator.class);
    private static final String SEPERATOR = " | ";

    private Validator validator;


    public QuoteServiceValidator(Validator validator) {
        this.validator = validator;
    }
    
    public Errors validate(VSCQuoteRQ quoteRQ) {

        Set<ConstraintViolation<VSCQuoteRQ>> violations = validator.validate(quoteRQ);
        Errors errors = null;
        if(!CollectionUtils.isEmpty(violations)) {
            errors = new Errors();
            for(ConstraintViolation<VSCQuoteRQ> violation : violations) {
                Error error = getReserveError(violation);
                if (error != null) {
                    errors.getError().add(getReserveError(violation));
                }

            }
        }
        return errors;
    }
    
    private Error getReserveError(ConstraintViolation violation) {
        Error error = null;
        if (!StringUtils.isEmpty(violation.getMessage())) {
            error = new Error();
            error.setContent(violation.getMessage());
            String errorCode = violation.getMessageTemplate();
            errorCode = StringUtils.trimLeadingCharacter(errorCode,'{');
            errorCode = StringUtils.trimTrailingCharacter(errorCode, '}');
            error.setCode(errorCode);
            logger.info(error.getCode() + SEPERATOR + error.getContent());
        }
        return error;
    }
    
       
}
