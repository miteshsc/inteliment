package inteliment.com.restful;

import inteliment.com.service.SearchService;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Mitesh Chavda
 * @version 1.0
 */

@Component
@Path("/")
public class SearchWebService {
    private SearchService searchService;

    public SearchService getSearchService() {
	return searchService;
    }

    public void setSearchService(SearchService searchService) {
	this.searchService = searchService;
    }

    /**
     * This method search all searched words from paragraph and return word
     * count information.
     * 
     * 
     * @param searchText
     *            list of words
     * @return searched words count
     * @throws IOException
     */
    @POST
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public String search(String searchText) throws IOException {

	String response = searchService.countSearchText(searchText);

	return response;
    }

    /**
     * This method gives word list of top
     * 
     * @param count
     *            top number of result
     * @return CSV format data of text and count
     * @throws IOException
     */
    @GET
    @Produces("text/csv")
    @Path("/top/{count}")
    public Response top(@PathParam("count") int count) throws IOException {

	String response = searchService.getTopTextCount(count);
	return Response
		.ok(response)
		.header("Content-Disposition",
			"attachment; filename=" + count + ".csv").build();

    }

}
