package inteliment.com.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 
 * @author Mitesh Chavda
 * @version 1.0
 *
 */
public interface SearchService {

    /**
     * This method takes searchText as input and get words count information
     * 
     * @param searchText
     * @return
     * @throws JsonMappingException
     * @throws JsonGenerationException
     * @throws IOException
     */
    public String countSearchText(String searchText)
	    throws JsonMappingException, JsonGenerationException, IOException;

    /**
     * This method take number as input and returns top words with highest count
     * 
     * @param count
     *            total number of result to be fetch
     * @return String comma separated string of text and count
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String getTopTextCount(int count) throws FileNotFoundException,
	    IOException;

}
