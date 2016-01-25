package com.vsc.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.RuntimeDelegate;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.vsc.validation.validator.PropertySearchValidator;
import com.vsc.validation.validator.QuoteServiceValidator;
import com.vsc.validation.validator.ReserveServiceValidator;
import com.vsc.cxf.filters.authentication.BasicAuthFilter;
import com.vsc.cxf.provider.JSONProvider;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.apache.cxf.jaxrs.validation.JAXRSBeanValidationInInterceptor;
import org.apache.cxf.jaxrs.validation.JAXRSBeanValidationOutInterceptor;
import org.apache.cxf.jaxrs.validation.ValidationExceptionMapper;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.message.Message;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;
import com.vsc.ws.rs.property.PropertyService;
import com.vsc.ws.rs.property.impl.PropertyServiceImpl;
import com.vsc.ws.rs.quote.QuoteService;
import com.vsc.ws.rs.quote.impl.QuoteServiceImpl;
import com.vsc.ws.rs.reserve.ReserveService;
import com.vsc.ws.rs.reserve.impl.ReserveServiceImpl;
import com.vsc.ws.soap.booking.BookingService;
import com.vsc.ws.soap.booking.impl.BookingServiceImpl;


@Configuration
@ComponentScan(basePackages = {"com.vsc.controller","com.vsc.services"})
@ImportResource({"classpath:META-INF/cxf/cxf.xml","classpath:META-INF/cxf/cxf-servlet.xml"})
@PropertySources({
	@PropertySource(value = "file:${app.config.dir}/partner-config.properties", ignoreResourceNotFound=true),
	@PropertySource(value = "file:${app.config.dir}/esb-config.properties", ignoreResourceNotFound=true),
    })
public class AppConfig {
	
	@ApplicationPath("/")
    public class JaxRsApiApplication extends Application { }
 
    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }
 
    @Bean
    @DependsOn("cxf")
    public Server jaxRsServer(ApplicationContext appContext) {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(jaxRsApiApplication(), JAXRSServerFactoryBean.class);
        factory.setServiceBeans(Arrays.<Object>asList(propertyService(),reserveService(),quoteService()));
        factory.setAddress("/rest");
        factory.setProvider(authFilter());
        factory.setProvider(jaxbProvider());
        factory.setProvider(jacksonJaxbJsonProvider());
        factory.setProvider(new ValidationExceptionMapper());
        factory.setExtensionMappings(extensionMappings());
        factory.setInInterceptors(Arrays.<Interceptor< ? extends Message>>asList(new JAXRSBeanValidationInInterceptor(), new LoggingInInterceptor()));
        factory.setOutInterceptors(
                Arrays.< Interceptor< ? extends Message > >asList(
                        new JAXRSBeanValidationOutInterceptor(),new LoggingOutInterceptor()
                )
        );
        return factory.create();
    }
    
    @Bean
    @DependsOn("cxf")
    public Server jaxWSServer(ApplicationContext appContext) {
    	JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setAddress("/soap");
        factory.setServiceBean(bookingService());
        return factory.create();
    }
    
    
    @Bean
    public PropertyService propertyService() {
    	return new PropertyServiceImpl(propertySearchValidator());
    }

    @Bean
    public PropertySearchValidator propertySearchValidator() {
        return new PropertySearchValidator(validator());
    }
    
    @Bean		
   	public ReserveService reserveService() {		
   	return new ReserveServiceImpl(reserveServiceValidator());		
   	}		
   			
   	@Bean		
   	public ReserveServiceValidator reserveServiceValidator() {		
   	return new ReserveServiceValidator(validator());		
   	}		
   			
   	@Bean		
   	public QuoteService quoteService() {		
   	return new QuoteServiceImpl(quoteServiceValidator());		
   	}		
   			
   	@Bean		
   	public QuoteServiceValidator quoteServiceValidator() {		
   	return new QuoteServiceValidator(validator());		
   	}		
 
    @Bean
    public BookingService bookingService() {
    	return new BookingServiceImpl();
    }
    
    @Bean
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }
 
    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }

    @Bean
    public JAXBElementProvider jaxbProvider() {
        JAXBElementProvider jaxbElementProvider = new JAXBElementProvider();
        jaxbElementProvider.setMarshallerProperties(propertiesMap());
        return new JAXBElementProvider();
    }

    @Bean
    public Map propertiesMap() {
        Map<String, Object> marshallProperties = new HashMap<String, Object>();
        marshallProperties.put("jaxb.formatted.output",Boolean.TRUE);
        return marshallProperties;
    }

    @Bean
    public Validator validator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator;
    }

    @Bean
    public BasicAuthFilter authFilter() {
    	return new BasicAuthFilter();
    }
    
    @Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
    
    @Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
    
    @Bean
    public JacksonJaxbJsonProvider jacksonJaxbJsonProvider() {
    	return new JSONProvider();
    }
    
    
    public Map<Object, Object> extensionMappings() {
    	Map<Object, Object> map = new HashMap<Object, Object>();
    	map.put("xml", MediaType.APPLICATION_XML);
    	map.put("json", MediaType.APPLICATION_JSON);
    	return map;
    }
    
}
