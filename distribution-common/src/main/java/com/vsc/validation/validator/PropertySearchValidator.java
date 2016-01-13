package com.vsc.validation.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.vsc.model.availability.request.VSCAvailRQ;
import com.vsc.model.property.summary.request.VSCPropertyListRQ;
import com.vsc.model.search.request.VSCSearchRQ;
import com.vsc.model.search.response.Error;
import com.vsc.model.search.response.Errors;

/**
 * Created by jebynot on 8/21/15.
 */
public class PropertySearchValidator {
    private static final Logger logger = Logger.getLogger(PropertySearchValidator.class);
    private static final String SEPERATOR = " | ";

    private Validator validator;


    public PropertySearchValidator(Validator validator) {
        this.validator = validator;
    }

    //For Search API
    public Errors validate(VSCSearchRQ searchRQ) {

        Set<ConstraintViolation<VSCSearchRQ>> violations = validator.validate(searchRQ);
        Errors errors = null;
        if(!CollectionUtils.isEmpty(violations)) {
            errors = new Errors();
            for(ConstraintViolation<VSCSearchRQ> violation : violations) {
                Error error = getError(violation);
                if (error != null) {
                    errors.getError().add(getError(violation));
                }

            }
        }
        return errors;
    }
    
    private Error getError(ConstraintViolation violation) {
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
    
    //For PropertyList API
    public com.vsc.model.property.summary.response.Errors validate(VSCPropertyListRQ propertyListRQ) {

        Set<ConstraintViolation<VSCPropertyListRQ>> violations = validator.validate(propertyListRQ);
        com.vsc.model.property.summary.response.Errors errors = null;
        if(!CollectionUtils.isEmpty(violations)) {
            errors = new com.vsc.model.property.summary.response.Errors();
            for(ConstraintViolation<VSCPropertyListRQ> violation : violations) {
                com.vsc.model.property.summary.response.Error error = getPropertyListError(violation);
                if (error != null) {
                    errors.getError().add(getPropertyListError(violation));
                }

            }
        }
        return errors;
    }
        private com.vsc.model.property.summary.response.Error getPropertyListError(ConstraintViolation violation) {
            com.vsc.model.property.summary.response.Error error = null;
            if (!StringUtils.isEmpty(violation.getMessage())) {
                error = new com.vsc.model.property.summary.response.Error();
                error.setContent(violation.getMessage());
                String errorCode = violation.getMessageTemplate();
                errorCode = StringUtils.trimLeadingCharacter(errorCode,'{');
                errorCode = StringUtils.trimTrailingCharacter(errorCode, '}');
                error.setCode(errorCode);
                logger.info(error.getCode() + SEPERATOR + error.getContent());
            }
            return error;
        }
    
    
    
    //For Availability API
    public com.vsc.model.availability.response.Errors validate(VSCAvailRQ availRQ) {

        Set<ConstraintViolation<VSCAvailRQ>> violations = validator.validate(availRQ);
        com.vsc.model.availability.response.Errors errors = null;
        if(!CollectionUtils.isEmpty(violations)) {
            errors = new com.vsc.model.availability.response.Errors();
            for(ConstraintViolation<VSCAvailRQ> violation : violations) {
                com.vsc.model.availability.response.Error error = getAvailError(violation);
                if (error != null) {
                    errors.getError().add(getAvailError(violation));
                }

            }
        }
        return errors;
    }
    
    private com.vsc.model.availability.response.Error getAvailError(ConstraintViolation violation) {
        com.vsc.model.availability.response.Error error = null;
        if (!StringUtils.isEmpty(violation.getMessage())) {
            error = new com.vsc.model.availability.response.Error();
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
