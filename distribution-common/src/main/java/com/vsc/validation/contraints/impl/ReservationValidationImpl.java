package com.vsc.validation.contraints.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;

import com.vsc.model.reserve.request.Fee;
import com.vsc.model.reserve.request.Fees;
import com.vsc.model.reserve.request.GuestCounts;
import com.vsc.model.reserve.request.Rate;
import com.vsc.model.reserve.request.Rates;
import com.vsc.model.reserve.request.ResTotal;
import com.vsc.model.reserve.request.StayDateRange;
import com.vsc.model.reserve.request.Tax;
import com.vsc.model.reserve.request.Taxes;
import com.vsc.model.reserve.request.UnitRateType;
import com.vsc.validation.contraints.ReservationValidation;


public class ReservationValidationImpl implements ConstraintValidator<ReservationValidation, Object> {

	private String unitRateType;
	private String stayDateRange;
	private String resTotal;
	private String guestCounts;
	
	@Override
	public void initialize(ReservationValidation dateCompareReservation) {
		unitRateType=dateCompareReservation.unitRateType();
		stayDateRange=dateCompareReservation.stayDateRange();
		resTotal=dateCompareReservation.resTotal();
		guestCounts=dateCompareReservation.guestCounts();
		
	}


	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {

		Boolean status = Boolean.TRUE;
		
			try {
				ResTotal resTotalRQ = (ResTotal)PropertyUtils.getProperty(obj, resTotal);
				UnitRateType unitRateTypeRQ = (UnitRateType)PropertyUtils.getProperty(obj, unitRateType);
				StayDateRange stayDateRangeRQ = (StayDateRange)PropertyUtils.getProperty(obj, stayDateRange);
				GuestCounts guestCountsRQ = (GuestCounts)PropertyUtils.getProperty(obj, guestCounts);
				
				XMLGregorianCalendar checkIn = (XMLGregorianCalendar)stayDateRangeRQ.getCheckIn();
				XMLGregorianCalendar checkOut = (XMLGregorianCalendar)stayDateRangeRQ.getCheckOut();
				checkIn.setTimezone(DatatypeConstants.FIELD_UNDEFINED);		
				checkOut.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
				
				Float guestCount=guestCountsRQ.getTotal().floatValue();
				
				Rates rates=(Rates)unitRateTypeRQ.getRates();
				Fees fees=(Fees)unitRateTypeRQ.getFees();
				Taxes taxes=(Taxes)unitRateTypeRQ.getTaxes();
				Float AmountBeforeTax=resTotalRQ.getAmountBeforeTax();
				Float AmountAfterTax=resTotalRQ.getAmountAfterTax();
				List<Rate> rate=(List<Rate>) rates.getRate();
			
				List<XMLGregorianCalendar> stay_range =new ArrayList<>();//to check missing rates for a mandatory date range
				XMLGregorianCalendar newDate = checkIn;
				Calendar calendar=checkIn.toGregorianCalendar();
				 float days=daysBetween(checkIn.toGregorianCalendar(), checkOut.toGregorianCalendar());
				 int count =-1;
				
				while (count<days){//create stay_range list i.e contains list of dates from checkIn to CheckOut
				stay_range.add(newDate);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTimeInMillis(calendar.getTimeInMillis());
                try {
					newDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH)+1, gc.get(Calendar.DAY_OF_MONTH), 0, 0, 0, 0, DatatypeConstants.FIELD_UNDEFINED);
					
					count++;
				} catch (DatatypeConfigurationException e) {
					e.printStackTrace();
				}
				}
				
				XMLGregorianCalendar startDate = null ;
				XMLGregorianCalendar endDate = null;
				float ratesTotal = 0;
				float feesTotal = 0;
				float feeTotal = 0;
				float taxesTotal = 0;
				float totalAmountBeforeTax = 0;
				float totalAmountAfterTax = 0;
				float no_of_days=0;
				
				if(null != fees && null != fees.getFee() && !CollectionUtils.isEmpty(fees.getFee())) {
				
					List<Fee> fee=(List<Fee>) fees.getFee();
				
					for(Fee feeElememt: fee) {//calculate fees_total
						
						if(feeElememt.getApplicability().equalsIgnoreCase("Flat Rate"))//calculate fee_total if Applicability="Flat Rate"
						 {
							feeTotal =feeElememt.getFeeAmount();
							if (feeTotal!=feeElememt.getFeeTotal())
							 {
								   context.buildConstraintViolationWithTemplate("{643}").addConstraintViolation();
									status = Boolean.FALSE;
									return status;   
							   } 
						 }
						
						if(feeElememt.getApplicability().equalsIgnoreCase("Per Night"))//calculate fee_total if Applicability="Per Night"
							{
							no_of_days=daysBetween(checkIn.toGregorianCalendar(), checkOut.toGregorianCalendar());
							 feeTotal =no_of_days*feeElememt.getFeeAmount();
							 if (feeTotal!=feeElememt.getFeeTotal())
							 {
								   context.buildConstraintViolationWithTemplate("{643}").addConstraintViolation();
									status = Boolean.FALSE;
									return status;   
							   } 
							}
						
						if((feeElememt.getApplicability().equalsIgnoreCase("Per Adult"))||
								(feeElememt.getApplicability().equalsIgnoreCase("Per Child")||
										(feeElememt.getApplicability().equalsIgnoreCase("Per Person"))))//calculate fee_total if Applicability="Per Adult or Per Child or Per Person"
						{ 
							feeTotal =guestCount*feeElememt.getFeeAmount();
							
							if (feeTotal!=feeElememt.getFeeTotal())
							 {
								   context.buildConstraintViolationWithTemplate("{643}").addConstraintViolation();
									status = Boolean.FALSE;
									return status;   
							   } 
						}
						
						  if(!((feeElememt.getApplicability().equalsIgnoreCase("Per Adult"))||
								(feeElememt.getApplicability().equalsIgnoreCase("Per Child"))||
							   (feeElememt.getApplicability().equalsIgnoreCase("Per Person"))||
							   (feeElememt.getApplicability().equalsIgnoreCase("Per Night"))||
							   (feeElememt.getApplicability().equalsIgnoreCase("Flat Rate"))))// check whether the input value quoted for Applicability is "Per Adult or Per Child or Per Person"
						  {
							   context.buildConstraintViolationWithTemplate("{630}").addConstraintViolation();
								status = Boolean.FALSE;
								return status;   
						   }
						
						if(feeElememt.isMandatory()==false||feeElememt.isDisplayOnly()==true)//checks if Mandatory is false or DisplayOnly or true or both then that feeTotal is not added to feesTotal 
						{continue;}
						feesTotal+=feeElememt.getFeeTotal();
					}
					if(feesTotal!=fees.getFeesTotal())//check the calculated fees_total with entered fees_total
					   {
						   context.buildConstraintViolationWithTemplate("{644}").addConstraintViolation();
							status = Boolean.FALSE;
							return status;   
					   }
				}
				
				int dates_counters=0;		
				
				List<XMLGregorianCalendar> startDateList = new ArrayList<XMLGregorianCalendar>();//contains the list of start dates		int missing_dates_counters=0;
				List<XMLGregorianCalendar> endDateList = new ArrayList<XMLGregorianCalendar>();//contains the list of end dates
				for(Rate dates: rate) {
					startDate =(XMLGregorianCalendar) dates.getStartDate();
					endDate = (XMLGregorianCalendar) dates.getEndDate();
					ratesTotal +=dates.getAmountBeforeTax();//calculate rates_total
					
					startDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);		
					endDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
					
					startDateList.add(startDate);//add the start date to startDateList		
					endDateList.add(endDate);//add the end date to endDateList
										
					if (startDate.toGregorianCalendar().before(checkIn.toGregorianCalendar())) {
						context.buildConstraintViolationWithTemplate("{617}").addConstraintViolation();
						status = Boolean.FALSE;
						return status;
					}
					
					if (endDate.toGregorianCalendar().after(checkOut.toGregorianCalendar())) {
						context.buildConstraintViolationWithTemplate("{619}").addConstraintViolation();
						status = Boolean.FALSE;
						return status;
					}
					
					if (startDate.toGregorianCalendar().after(checkOut.toGregorianCalendar())) {
						context.buildConstraintViolationWithTemplate("{618}").addConstraintViolation();
						status = Boolean.FALSE;
						return status;
					}
					
					if (endDate.toGregorianCalendar().before(checkIn.toGregorianCalendar())) {
						context.buildConstraintViolationWithTemplate("{621}").addConstraintViolation();
						status = Boolean.FALSE;
						return status;
					}
					
									
					if (stay_range.contains(startDate)&&stay_range.contains(endDate) && dates.getRateTimeUnit().equalsIgnoreCase("day")) {  //check if startDate and endDate is there in dateList 
						dates_counters++;
					}
				}
				
				for (XMLGregorianCalendar start_date : startDateList) {
					int start_duplicates_counter= Collections.frequency(startDateList, start_date);
					
					if (start_duplicates_counter>1) {//check if duplicates rate are there for the same date 
						context.buildConstraintViolationWithTemplate("{761}").addConstraintViolation();
						status = Boolean.FALSE;
						return status;
					}
					
				
				}
					
				for (XMLGregorianCalendar end_date : endDateList) {
					int end_duplicates_counter= Collections.frequency(endDateList, end_date);
					
					if (end_duplicates_counter>1) {//check if duplicates rate are there for the same date 
						context.buildConstraintViolationWithTemplate("{761}").addConstraintViolation();
						status = Boolean.FALSE;
						return status;
					}
					
				
				}
				
				if ((dates_counters!=(stay_range.size()-1))) {//check if counters is equal to the no of days stayed
					context.buildConstraintViolationWithTemplate("{760}").addConstraintViolation();
					status = Boolean.FALSE;
					return status;
				}
				
				
				
				   if(ratesTotal!=rates.getRatesTotal())//check the calculated rates_total with entered rates_total
				   {
					   context.buildConstraintViolationWithTemplate("{625}").addConstraintViolation();
						status = Boolean.FALSE;
						return status;   
				   }	
				 
				   if(null != taxes && null != taxes.getTax() && !CollectionUtils.isEmpty(taxes.getTax())) {
					   List<Tax> tax =(List<Tax>) taxes.getTax();
					   for(Tax taxTotal: tax) {//calculate taxes_total
						   taxesTotal+=taxTotal.getTaxAmount();
						}
					   if(taxesTotal!=taxes.getTaxesTotal())//check the calculated taxes_total with entered taxes_total
					   {
						   context.buildConstraintViolationWithTemplate("{665}").addConstraintViolation();
							status = Boolean.FALSE;
							return status;   
					   }
				   }
				   
				   totalAmountBeforeTax=ratesTotal+feesTotal;
				   totalAmountAfterTax=ratesTotal+feesTotal+taxesTotal;
				   				   
				   if(totalAmountBeforeTax!=AmountBeforeTax)//check the calculated Amount_Before_Tax with entered Amount_Before_Tax
				   {
					   context.buildConstraintViolationWithTemplate("{707}").addConstraintViolation();
						status = Boolean.FALSE;
						return status;   
				   }
				   
				   if(totalAmountAfterTax!=AmountAfterTax)//check the calculated Amount_After_Tax with entered Amount_After_Tax
				   {
					   context.buildConstraintViolationWithTemplate("{711}").addConstraintViolation();
						status = Boolean.FALSE;
						return status;   
				   }
				  	
				return status;
								
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			
			return true;
	}
	public static long daysBetween(Calendar startDate, Calendar endDate) {
		long end = endDate.getTimeInMillis();
		long start = startDate.getTimeInMillis();
		return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
	}
	
	
}
