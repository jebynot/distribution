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

import com.vsc.legacy.validation.contraints.DateCompareReserveRate;


public class DateCompareReserveRateImpl implements ConstraintValidator<DateCompareReserveRate, Object>{

	private String startDatePropertyName;
	private String endDatePropertyName;
	
	
	@Override
	public void initialize(DateCompareReserveRate dateCompareReserve) {
		
		startDatePropertyName=dateCompareReserve.startDatePropertyName();
		endDatePropertyName=dateCompareReserve.endDatePropertyName();
	}
	
	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {

		Boolean status = Boolean.TRUE;
		try {
			XMLGregorianCalendar fromDate = (XMLGregorianCalendar)PropertyUtils.getProperty(obj, startDatePropertyName);
			XMLGregorianCalendar toDate = (XMLGregorianCalendar)PropertyUtils.getProperty(obj, endDatePropertyName);

			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD'T'00:00:00");
			
			if (fromDate == null) {
				context.buildConstraintViolationWithTemplate("{610}").addConstraintViolation();
				status = Boolean.FALSE;
				return status;
			}
			if (toDate == null) {
				context.buildConstraintViolationWithTemplate("{615}").addConstraintViolation();
				status = Boolean.FALSE;
				return status;
			}
			
			fromDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);		
			toDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			
			Calendar currentDate = Calendar.getInstance();
			if (fromDate.toGregorianCalendar().before(currentDate)) {
				context.buildConstraintViolationWithTemplate("{612}").addConstraintViolation();
				status = Boolean.FALSE;
				return status;
			}
			if (toDate.toGregorianCalendar().before(currentDate)) {
				context.buildConstraintViolationWithTemplate("{616}").addConstraintViolation();
				status = Boolean.FALSE;
				return status;
			}
			if (fromDate.toGregorianCalendar().equals(toDate.toGregorianCalendar()) || fromDate.toGregorianCalendar().after(toDate.toGregorianCalendar())) {
				context.buildConstraintViolationWithTemplate("{613}").addConstraintViolation();
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
