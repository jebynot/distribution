package com.vsc.validation.contraints.impl;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;

import com.vsc.model.reserve.request.Address;
import com.vsc.model.reserve.request.Country;
import com.vsc.model.reserve.request.StateProvince;

import com.vsc.validation.contraints.AddressLocationValidation;


public class AddressLocationValidationImpl implements ConstraintValidator<AddressLocationValidation, Address> {

    private static final Logger logger = Logger.getLogger(LocationValidationImpl.class);

    private String state;
    private String country;

    @Override
    public void initialize(AddressLocationValidation constraintAnnotation) {
        state = constraintAnnotation.state();
        country = constraintAnnotation.country();
    }

    @Override
    public boolean isValid(Address address, ConstraintValidatorContext context) {
        Boolean status = Boolean.TRUE;
        try {
            StateProvince stateProvince = (StateProvince)PropertyUtils.getProperty(address, state);
            Country requestCountry = (Country)PropertyUtils.getProperty(address, country);

            if(stateProvince == null || (StringUtils.isEmpty(stateProvince.getContent())
                    && StringUtils.isEmpty(stateProvince.getCode()))) {
            	context.buildConstraintViolationWithTemplate("{698}").addConstraintViolation();
                status = Boolean.FALSE;
            }
            
            if (requestCountry == null || (StringUtils.isEmpty(requestCountry.getCode()) &&
                    StringUtils.isEmpty(requestCountry.getContent()))) {
                context.buildConstraintViolationWithTemplate("{701}").addConstraintViolation();
                status = Boolean.FALSE;
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
