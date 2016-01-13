package com.vsc.cxf.filters.authentication;

import java.io.InputStream;

import org.apache.cxf.common.util.Base64Utility;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BasicAuthTest {

	@Test
	public void testBasicAuth() throws Exception {
	   String url = "http://localhost:8080/distributionapp/services/rest/property/search?type=partner";
	   String name = "10";
	   String password = "test";

	   String authString = name + ":" + password;
	   System.out.println("Auth string: " + authString);
	   
	   String authStringEnc = Base64Utility.encode(authString.getBytes());
	   System.out.println("Base64 encoded auth string: " + authStringEnc);

	   InputStream stream = BasicAuthTest.class.getClassLoader().getResourceAsStream("property/vsc_searchrq.xml");
	   System.out.println(stream);
	   
	   HttpClient client = HttpClientBuilder.create().build();
	   HttpPost post = new HttpPost(url);
	   post.setHeader("Authorization","Basic "+authStringEnc);
	   post.setHeader("Content-type", "application/xml");
	   post.setEntity(new InputStreamEntity(stream));
	   HttpResponse response = client.execute(post);
	   System.out.println(response.getStatusLine().getStatusCode());
	}
	
}
