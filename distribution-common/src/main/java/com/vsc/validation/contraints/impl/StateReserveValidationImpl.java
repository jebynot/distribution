package com.vsc.validation.contraints.impl;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;


import com.vsc.model.search.request.Country;
import com.vsc.model.search.request.StateProvince;

import com.vsc.validation.contraints.StateReserveValidation;

public class StateReserveValidationImpl implements ConstraintValidator<StateReserveValidation, StateProvince> {

	@Override
	public void initialize(StateReserveValidation constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(StateProvince value,
			ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	/*private static final Logger logger = Logger.getLogger(LocationValidationImpl.class);

   
    private String statecode;
    private String statename;
   
	@Override
	public void initialize(StateReserveValidation constraintAnnotation) {
		statecode = constraintAnnotation.statecode();
		statename= constraintAnnotation.statename();
	}

	@Override
	public boolean isValid(StateProvince state_province,ConstraintValidatorContext context) {
		Boolean status = Boolean.TRUE;
        try {
           
            StateProvince stateProvince = (StateProvince)PropertyUtils.getProperty(state_province, statecode);
            
            if ((null !=stateProvince && (!StringUtils.isEmpty(stateProvince.getName()) || 
        			!StringUtils.isEmpty(stateProvince.getCode()))) && 
        			(stateProvince == null || (StringUtils.isEmpty(stateProvince.getCode()) &&
                    StringUtils.isEmpty(stateProvince.getName())))) {
                context.buildConstraintViolationWithTemplate("{698}").addConstraintViolation();
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
*/
	

}
