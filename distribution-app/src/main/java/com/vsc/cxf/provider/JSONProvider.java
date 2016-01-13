package com.vsc.cxf.provider;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class JSONProvider extends JacksonJaxbJsonProvider {
	
	public JSONProvider() {
		ObjectMapper mapper = new ObjectMapper();
        mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true);
        mapper.configure(org.codehaus.jackson.map.SerializationConfig.Feature.WRAP_ROOT_VALUE, true);
        mapper.configure(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,false);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        
        mapper.setDateFormat(sdf);
        _mapperConfig.setMapper(mapper);
        _mapperConfig.getConfiguredMapper().setAnnotationIntrospector(new JaxbAnnotationIntrospector());
	}

}
