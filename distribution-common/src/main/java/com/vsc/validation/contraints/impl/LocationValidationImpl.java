package com.vsc.validation.contraints.impl;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;

import com.vsc.model.search.request.Country;
import com.vsc.model.search.request.Location;
import com.vsc.model.search.request.StateProvince;
import com.vsc.validation.contraints.LocationValidation;

/**
 * Created by jebynot on 8/21/15.
 */
public class LocationValidationImpl implements ConstraintValidator<LocationValidation, Location> {

    private static final Logger logger = Logger.getLogger(LocationValidationImpl.class);

    private String cityName;
    private String state;
    private String country;
    private String region;
    private String postalCode;

    @Override
    public void initialize(LocationValidation constraintAnnotation) {
        cityName = constraintAnnotation.cityName();
        state = constraintAnnotation.state();
        country = constraintAnnotation.country();
        postalCode = constraintAnnotation.postalCode();
    }

    @Override
    public boolean isValid(Location location, ConstraintValidatorContext context) {
        Boolean status = Boolean.TRUE;
        try {
            String requestCityName = (String) PropertyUtils.getProperty(location, cityName);
            StateProvince stateProvince = (StateProvince)PropertyUtils.getProperty(location, state);
            Country requestCountry = (Country)PropertyUtils.getProperty(location, country);

            if(!StringUtils.isEmpty(requestCityName)){
	            if(stateProvince == null || (StringUtils.isEmpty(stateProvince.getName())
	                    && StringUtils.isEmpty(stateProvince.getCode()))) {
	            	context.buildConstraintViolationWithTemplate("{307}").addConstraintViolation();
	                status = Boolean.FALSE;
	            }
	            
	            if (requestCountry == null || (StringUtils.isEmpty(requestCountry.getCode()) &&
	                    StringUtils.isEmpty(requestCountry.getName()))) {
	                context.buildConstraintViolationWithTemplate("{312}").addConstraintViolation();
	                status = Boolean.FALSE;
	            }
            }
            else{
            
            	if ((null !=stateProvince && (!StringUtils.isEmpty(stateProvince.getName()) || 
            			!StringUtils.isEmpty(stateProvince.getCode()))) && 
            			(requestCountry == null || (StringUtils.isEmpty(requestCountry.getCode()) &&
	                    StringUtils.isEmpty(requestCountry.getName())))) {
	                context.buildConstraintViolationWithTemplate("{312}").addConstraintViolation();
	                status = Boolean.FALSE;
	            }
	            if((null != country && (!StringUtils.isEmpty(requestCountry.getName()) || 
	            		!StringUtils.isEmpty(requestCountry.getCode()))) && 
	            		(stateProvince == null || (StringUtils.isEmpty(stateProvince.getName())
	                    && StringUtils.isEmpty(stateProvince.getCode())))) {
	            	context.buildConstraintViolationWithTemplate("{307}").addConstraintViolation();
	                status = Boolean.FALSE;
	            }
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
