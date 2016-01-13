package com.vsc.validation.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.vsc.model.reserve.request.VSCReserveRQ;
import com.vsc.model.reserve.response.Errors;
import com.vsc.model.reserve.response.Error;

/**
 * Created by Jerry on 24/1/15.
 */
public class ReserveServiceValidator {
	private static final Logger logger = Logger.getLogger(ReserveServiceValidator.class);
    private static final String SEPERATOR = " | ";

    private Validator validator;


    public ReserveServiceValidator(Validator validator) {
        this.validator = validator;
    }
    
    public Errors validate(VSCReserveRQ reserveRQ) {

        Set<ConstraintViolation<VSCReserveRQ>> violations = validator.validate(reserveRQ);
        Errors errors = null;
        if(!CollectionUtils.isEmpty(violations)) {
            errors = new Errors();
            for(ConstraintViolation<VSCReserveRQ> violation : violations) {
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
