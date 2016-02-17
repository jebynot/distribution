package com.vsc.mediatype.jackson.json.deserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;



public class DateDeserializer extends JsonDeserializer<XMLGregorianCalendar>{

	@Override
	public XMLGregorianCalendar deserialize(JsonParser p,
			DeserializationContext ctxt) throws IOException,
			JsonProcessingException {

		GregorianCalendar c = new GregorianCalendar();

		String value = p.getText();
		
		SimpleDateFormat sdf = null;
		if (value.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")){
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		} else if(value.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})T[0-9]{2}:[0-9]{2}:[0-9]{2}")) {
			sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		} else {
			return null;
		}
		sdf.setLenient(false);
		
		try{
			Date date = sdf.parse(value);
			c.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch(Exception e){
			
		}
		
		return null;
	}
	
}