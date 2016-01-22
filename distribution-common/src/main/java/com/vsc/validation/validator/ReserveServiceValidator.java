package com.vsc.validation.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.vsc.model.reserve.cancel.request.VSCCancelRQ;
import com.vsc.model.reserve.details.request.VSCRetrieveResDetailsRQ;
import com.vsc.model.reserve.request.VSCReserveRQ;
import com.vsc.model.reserve.response.Error;
import com.vsc.model.reserve.response.Errors;

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
    
    public com.vsc.model.reserve.cancel.response.Errors validate(VSCCancelRQ cancelRQ) {

        Set<ConstraintViolation<VSCCancelRQ>> violations = validator.validate(cancelRQ);
        com.vsc.model.reserve.cancel.response.Errors errors = null;
        if(!CollectionUtils.isEmpty(violations)) {
            errors = new com.vsc.model.reserve.cancel.response.Errors();
            for(ConstraintViolation<VSCCancelRQ> violation : violations) {
                com.vsc.model.reserve.cancel.response.Error error = getCancelError(violation);
                if (error != null) {
                    errors.getError().add(getCancelError(violation));
                }

            }
        }
        return errors;
    }
    
    private com.vsc.model.reserve.cancel.response.Error getCancelError(ConstraintViolation violation) {
        com.vsc.model.reserve.cancel.response.Error error = null;
        if (!StringUtils.isEmpty(violation.getMessage())) {
            error = new com.vsc.model.reserve.cancel.response.Error();
            error.setContent(violation.getMessage());
            String errorCode = violation.getMessageTemplate();
            errorCode = StringUtils.trimLeadingCharacter(errorCode,'{');
            errorCode = StringUtils.trimTrailingCharacter(errorCode, '}');
            error.setCode(errorCode);
            logger.info(error.getCode() + SEPERATOR + error.getContent());
        }
        return error;
    }  
    
    public com.vsc.model.reserve.details.response.Errors validate(VSCRetrieveResDetailsRQ retrieveResDetailsRQ) {

        Set<ConstraintViolation<VSCRetrieveResDetailsRQ>> violations = validator.validate(retrieveResDetailsRQ);
        com.vsc.model.reserve.details.response.Errors errors = null;
        if(!CollectionUtils.isEmpty(violations)) {
            errors = new com.vsc.model.reserve.details.response.Errors();
            for(ConstraintViolation<VSCRetrieveResDetailsRQ> violation : violations) {
                com.vsc.model.reserve.details.response.Error error = getRetrieveReservationError(violation);
                if (error != null) {
                    errors.getError().add(getRetrieveReservationError(violation));
                }

            }
        }
        return errors;
    }
    
    private com.vsc.model.reserve.details.response.Error getRetrieveReservationError(ConstraintViolation violation) {
    	com.vsc.model.reserve.details.response.Error error = null;
        if (!StringUtils.isEmpty(violation.getMessage())) {
            error = new com.vsc.model.reserve.details.response.Error();
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
