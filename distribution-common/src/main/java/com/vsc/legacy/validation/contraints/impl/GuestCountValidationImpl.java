package com.vsc.legacy.validation.contraints.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.vsc.legacy.model.search.request.GuestCount;
import com.vsc.legacy.validation.contraints.GuestCountValidation;


public class GuestCountValidationImpl implements ConstraintValidator<GuestCountValidation, GuestCount> {
	
	private static final Logger logger = Logger.getLogger(GuestCountValidationImpl.class);

    private String count;
    private String ageQualifyingCode;

	@Override
	public void initialize(GuestCountValidation constraintAnnotation) {

		count = constraintAnnotation.count();
		ageQualifyingCode = constraintAnnotation.ageQualifyingCode();
		
	}

	@Override
	public boolean isValid(GuestCount guestCount, ConstraintValidatorContext context) {
		
		Boolean status = Boolean.TRUE;
        try {
            
            if(null == PropertyUtils.getProperty(guestCount, ageQualifyingCode)) {
            	context.buildConstraintViolationWithTemplate("{009}").addConstraintViolation();
                status = Boolean.FALSE;
                return status;
            }
            
            if(null == PropertyUtils.getProperty(guestCount, count)) {
            	context.buildConstraintViolationWithTemplate("{009}").addConstraintViolation();
                status = Boolean.FALSE;
                return status;
            }
            String requestAgeQualifyingCode = (String)PropertyUtils.getProperty(guestCount, ageQualifyingCode);
            
            if(PropertyUtils.getProperty(guestCount, count).toString().isEmpty()) {
            	context.buildConstraintViolationWithTemplate("{113}").addConstraintViolation();
                status = Boolean.FALSE;
                return status;
            }
            
            if(requestAgeQualifyingCode.trim().isEmpty()) {
            	context.buildConstraintViolationWithTemplate("{116}").addConstraintViolation();
                status = Boolean.FALSE;
                return status;
            }
           
            try{
            	BigInteger requestCount = (BigInteger) PropertyUtils.getProperty(guestCount, count);
            } catch(NumberFormatException e) {
            	context.buildConstraintViolationWithTemplate("{160}").addConstraintViolation();
                status = Boolean.FALSE;
                return status;
            }
            

        } catch (IllegalAccessException e) {
            logger.error(e);
        } catch (InvocationTargetException e) {
            logger.error(e);
        } catch (NoSuchMethodException e) {
            logger.error(e);
        }
        return status;
    }
		
	
}
