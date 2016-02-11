package com.vsc.legacy.validation.contraints.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.PropertyUtils;

import com.vsc.legacy.validation.contraints.DateCompare;

public class DateCompareImpl implements ConstraintValidator<DateCompare, Object> {

	private String fromDatePropertyName;
	private String toDatePropertyName;
	
	@Override
	public void initialize(DateCompare dateCompare) {
		fromDatePropertyName = dateCompare.fromDatePropertyName();
		toDatePropertyName = dateCompare.toDatePropertyName();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		Boolean status = Boolean.TRUE;
		try {
			XMLGregorianCalendar fromDate = (XMLGregorianCalendar)PropertyUtils.getProperty(obj, fromDatePropertyName);
			XMLGregorianCalendar toDate = (XMLGregorianCalendar)PropertyUtils.getProperty(obj, toDatePropertyName);

			if (fromDate == null) {
				context.buildConstraintViolationWithTemplate("{105}").addConstraintViolation();
				status = Boolean.FALSE;
				return status;
			}
			if (toDate == null) {
				context.buildConstraintViolationWithTemplate("{109}").addConstraintViolation();
				status = Boolean.FALSE;
				return status;
			}
			
			fromDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);		
			toDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			
			Calendar currentDate = Calendar.getInstance();
			if (fromDate.toGregorianCalendar().before(currentDate)) {
				context.buildConstraintViolationWithTemplate("{106}").addConstraintViolation();
				status = Boolean.FALSE;
				return status;
			}
			if (toDate.toGregorianCalendar().before(currentDate)) {
				context.buildConstraintViolationWithTemplate("{110}").addConstraintViolation();
				status = Boolean.FALSE;
				return status;
			}
			if (fromDate.toGregorianCalendar().equals(toDate.toGregorianCalendar()) || fromDate.toGregorianCalendar().after(toDate.toGregorianCalendar())) {
				context.buildConstraintViolationWithTemplate("{107}").addConstraintViolation();
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

	public static long daysBetween(Calendar startDate, Calendar endDate) {
		long end = endDate.getTimeInMillis();
		long start = startDate.getTimeInMillis();
		return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
	}

}
