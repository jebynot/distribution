package com.vsc.mediatype.jackson.json.deserializer;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class BigIntegerDeserializer extends JsonDeserializer<BigInteger> {

	@Override
	public BigInteger deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String value = p.getText();
		
		BigInteger bigInteger = null;
		try{
			bigInteger = new BigInteger(value);
		} catch(Exception e){
			bigInteger = new BigInteger("-1");
		}
		
        return bigInteger;
	}

}
