package com.vsc.mediatype.jackson.json.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class BooleanDeserializer extends JsonDeserializer<Boolean> {

	@Override
	public Boolean deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String value = p.getText();
		if ("true".equals(value) || "True".equals(value)) {
            return Boolean.TRUE;
        }
        if ("false".equals(value) || "False".equals(value)) {
            return Boolean.FALSE;
        }
        return null;
	}

}
