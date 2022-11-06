package eu.trentorise.game.repo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import eu.trentorise.game.model.PointConcept;

public class LocalDateTimeDeserializer extends KeyDeserializer {

	@Override
	public Object deserializeKey(String key, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		return PointConcept.PERIOD_KEY_FORMAT.parseDateTime(key);
	}

}
