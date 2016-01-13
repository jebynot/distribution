package com.vsc.ws.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.vsc.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfig.class)
public class RestTemplateTest {

	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	public void testRestTemplate() {
		System.out.println("inside testRestTemplate");
	}
	
}
