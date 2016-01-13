package com.vsc.validation.contraints.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.PropertyUtils;

import com.vsc.model.property.summary.request.PropertyList;
import com.vsc.validation.contraints.ListDateValidation;

public class ListDateValidationImpl implements ConstraintValidator<ListDateValidation, PropertyList> {

	private String lastModifiedDate;
	
	@Override
	public void initialize(ListDateValidation listDateValidation) {
		lastModifiedDate = listDateValidation.lastModifiedDate();
	}

	@Override
	public boolean isValid(PropertyList propertyList, ConstraintValidatorContext context) {
		Boolean status = Boolean.TRUE;
		try {
			XMLGregorianCalendar modifiedDate = (XMLGregorianCalendar)PropertyUtils.getProperty(propertyList, lastModifiedDate);
			
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss");
			
			if (modifiedDate == null) {
				context.buildConstraintViolationWithTemplate("{200}").addConstraintViolation();
				status = Boolean.FALSE;
				return status;
			}
						
			try {
				sdf.parse(modifiedDate.toString());
			} catch (ParseException e) {
				context.buildConstraintViolationWithTemplate("{201}").addConstraintViolation();
				status = Boolean.FALSE;
				return status;
			}
			
			Calendar currentDate = Calendar.getInstance();
			if (modifiedDate.toGregorianCalendar().after(currentDate)) {
				context.buildConstraintViolationWithTemplate("{202}").addConstraintViolation();
				status = Boolean.FALSE;
				return status;
			}

			return status;
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		}
		return false;
	}
}
