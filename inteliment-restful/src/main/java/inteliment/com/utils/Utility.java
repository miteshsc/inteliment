package inteliment.com.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Mitesh Chavda
 * @version 1.0
 *
 */
public class Utility {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Object getInstanceFromJson(String jsonString, Class c)
	    throws JsonMappingException, JsonGenerationException, IOException {
	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
		false);
	return mapper.readValue(jsonString, c);
    }

    public static String getJsonStringFromObject(Object object)
	    throws JsonProcessingException {

	String jsonString = mapper.writeValueAsString(object);

	return jsonString;
    }
}
