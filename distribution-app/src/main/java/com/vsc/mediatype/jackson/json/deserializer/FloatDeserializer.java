package com.vsc.mediatype.jackson.json.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class FloatDeserializer extends JsonDeserializer<Float> {

	@Override
	public Float deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String value = p.getText();
		
		Float floatNum = null;
		try{
			floatNum = new Float(value);
		} catch(Exception e){
			floatNum = new Float("-1");
		}
		
        return floatNum;
	}

}
