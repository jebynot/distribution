package com.vsc.cxf.provider;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

import javax.xml.datatype.XMLGregorianCalendar;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.vsc.mediatype.jackson.json.deserializer.BigIntegerDeserializer;
import com.vsc.mediatype.jackson.json.deserializer.BooleanDeserializer;
import com.vsc.mediatype.jackson.json.deserializer.DateDeserializer;
import com.vsc.mediatype.jackson.json.deserializer.FloatDeserializer;

public class JSONProvider extends JacksonJaxbJsonProvider {
	
	public JSONProvider() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Boolean.class, new BooleanDeserializer());
		module.addDeserializer(XMLGregorianCalendar.class, new DateDeserializer());
		module.addDeserializer(BigInteger.class, new BigIntegerDeserializer());
		module.addDeserializer(Float.class, new FloatDeserializer());
		mapper.registerModule(module);;
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        mapper.setDateFormat(sdf);
        _mapperConfig.setMapper(mapper);
        _mapperConfig.getConfiguredMapper().setAnnotationIntrospector(new com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector(TypeFactory.defaultInstance()));;
	}

}
