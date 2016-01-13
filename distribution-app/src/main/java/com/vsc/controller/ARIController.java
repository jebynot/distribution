package com.vsc.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/ari")
public class ARIController {

	  @RequestMapping(method = RequestMethod.GET, value = "/name")
	  public String getRates(@PathVariable("name") String name) {
	    return name + " from the server";
	  }

	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public String test() throws Exception {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone='yes'?>\n" +
				"<VSC_searchRQ timeStamp=\"2015-06-01T16:35:43+5:30\" target=\"Test\" version=\"1.000\" xmlns=\"\">\n" +
				"\t<pos>\n" +
				"\t\t<sources>\n" +
				"\t\t\t<source>\n" +
				"\t\t\t\t<type>partner</type>\n" +
				"\t\t\t\t<ID>18</ID>\n" +
				"\t\t\t\t<password>password</password>\n" +
				"\t\t\t</source>\n" +
				"\t\t</sources>\t\t\n" +
				"\t\t<position latitude=\"\" longitude=\"\" IP=\"\" />\n" +
				"\t\t<language>EN</language>\n" +
				"\t\t<currency>USD</currency>\t\t\n" +
				"\t</pos>\n" +
				"\t<searchRequest>\n" +
				"\t\t<location>\n" +
				"\t\t\t<cityName>Orlando</cityName>\t\t\t\n" +
				"\t\t\t<stateProvince name=\"\" code=\"\" />\n" +
				"\t\t\t<country name=\"\" code=\"\" />\n" +
				"\t\t\t<region></region>\n" +
				"\t\t\t<postalCode></postalCode>\n" +
				"\t\t</location>\n" +
				"\t\t<property propertyCode=\"*\">\n" +
				"\t\t\t<propertyName></propertyName>\n" +
				"\t\t\t<propertyOwnership>\n" +
				"\t\t\t\t<propertyChainCode>*</propertyChainCode>\n" +
				"\t\t\t\t<propertyManager>*</propertyManager>\n" +
				"\t\t</propertyOwnership>\n" +
				"\t\t\t<accommodationType code=\"\">*</accommodationType>\t\t\t\n" +
				"\t\t\t<units>\n" +
				"<unit unitCode=\"*\"><!-- Will support one/all values -->\n" +
				"\t\t\t\t\t<unitName>*</unitName>\n" +
				"\t\t\t\t\t<unitType>*</unitType>\n" +
				"\t\t\t\t\t<numberOfBedRooms></numberOfBedRooms>\t\t\t\t\t\n" +
				"\t\t\t\t</unit>\n" +
				"\t\t\t</units>\t\t\n" +
				"\t\t</property>\t\n" +
				"\t\t<stayDateRange checkIn=\"2015-08-01\" checkOut=\"2015-08-05\"/>\n" +
				"\t\t<guestCounts>\n" +
				"\t\t\t<guestCount ageQualifyingCode=\"1\" count=\"2\"/>\t\t\t\n" +
				"\t\t</guestCounts>\t\t\n" +
				"\t\t<pets count=\"1\"/>\t\n" +
				"\t\t<displaySequence>LowToHigh</displaySequence>\t\t\n" +
				"\t\t<promoCode></promoCode>\n" +
				"\t\t<maxResults>50</maxResults>\n" +
				"\t</searchRequest>\n" +
				"</VSC_searchRQ>\t\n";

		URL prod = new URL("http://localhost:8080/distributionapp/services/rest/search/properties");
		HttpURLConnection httpConnection = (HttpURLConnection) prod.openConnection();

		httpConnection.setDoOutput(true);
		httpConnection.setRequestMethod("POST");
		//String encoded = new String(Base64.encodeBase64("MidtierTest:123Password123".getBytes()));
		String authorization = "Basic ";
		httpConnection.setRequestProperty("Authorization", authorization);
		httpConnection.setRequestProperty ( "Content-Type", "text/xml" );
		OutputStreamWriter out = new OutputStreamWriter(
				httpConnection.getOutputStream());
		out.write(xml);
		out.flush();
		out.close();
		System.out.println("Printing......");
		System.out.println(httpConnection.getResponseCode());
		System.out.println(httpConnection.getResponseMessage());
		InputStreamReader reader = new InputStreamReader( httpConnection.getInputStream() );
		StringBuilder buf = new StringBuilder();
		char[] cbuf = new char[ 2048 ];
		int num;

		while ( -1 != (num=reader.read( cbuf )))
		{
			buf.append( cbuf, 0, num );
		}

		String result = buf.toString();
		System.out.println( "\nResponse received from server after POST" + result );
		return result;
	}
	
}
