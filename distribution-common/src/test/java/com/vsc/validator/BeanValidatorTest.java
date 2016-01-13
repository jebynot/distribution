package com.vsc.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.CollectionUtils;

import com.vsc.model.search.request.StayDateRange;
import com.vsc.model.search.request.VSCSearchRQ;

@RunWith(JUnit4.class)
public class BeanValidatorTest {

	private static Validator validator;
	
	@BeforeClass
	public static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	@Ignore
	public void testSeachRQ() {
		VSCSearchRQ searchRQ = new VSCSearchRQ();
		Set<ConstraintViolation<VSCSearchRQ>> violations = validator.validate(searchRQ);
		if(!CollectionUtils.isEmpty(violations)) {
			for(ConstraintViolation<VSCSearchRQ> violation : violations) {
				System.out.println("Property : "+violation.getPropertyPath()+" Message :  "+violation.getMessage());
			}
		}
	}
	
	@Test
	public void testDateCompare() throws DatatypeConfigurationException {
		String checkInDateStr = "2015-08-01";
		String checkOutDateStr = "2015-08-05";
		StayDateRange stayDateRange = new StayDateRange();
		XMLGregorianCalendar checkInDate = getXmlGregorianCalendar(checkInDateStr);
		XMLGregorianCalendar checkOutDate = getXmlGregorianCalendar(checkOutDateStr);
		stayDateRange.setCheckIn(checkInDate);
		stayDateRange.setCheckOut(checkOutDate);
		Set<ConstraintViolation<StayDateRange>> violations = validator.validate(stayDateRange);
		if(!CollectionUtils.isEmpty(violations)) {
			for(ConstraintViolation<StayDateRange> violation : violations) {
				System.out.println(" Message :  "+violation.getMessage());
			}
		}
	}
	
	private XMLGregorianCalendar getXmlGregorianCalendar(String strDate) throws DatatypeConfigurationException {
		XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(strDate);
		return calendar;
	}
}
