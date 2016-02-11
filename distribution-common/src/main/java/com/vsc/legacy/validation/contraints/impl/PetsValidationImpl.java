package com.vsc.legacy.validation.contraints.impl;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.vsc.legacy.model.search.request.Pets;
import com.vsc.legacy.validation.contraints.PetsValidation;

public class PetsValidationImpl implements ConstraintValidator<PetsValidation, Pets> {

    private static final Logger logger = Logger.getLogger(PetsValidationImpl.class);

    private String count;

    @Override
    public void initialize(PetsValidation constraintAnnotation) {
        count = constraintAnnotation.count();
    }

    @Override
    public boolean isValid(Pets pets, ConstraintValidatorContext context) {
    	Boolean status = Boolean.TRUE;
        try {
            String petsCount = (String) PropertyUtils.getProperty(pets, count);
            
            if(petsCount == null) {
            	context.buildConstraintViolationWithTemplate("{009}").addConstraintViolation();
                status = Boolean.FALSE;
                return status;
            }
            
            if(!petsCount.isEmpty() && !petsCount.matches("\\d+")) {
            	context.buildConstraintViolationWithTemplate("{162}").addConstraintViolation();
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
