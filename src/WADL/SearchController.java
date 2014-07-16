package WADL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/algoSearch")

public class SearchController
{
	private GETSearchListHandler oGETSearchListHandler;// add all the activity handlers this controller is associated with in the PSM

 @Context
 private UriInfo oApplicationUri;

	public SearchController() {}
	
	//placeholder to add any HTTPActivity() template operation
	
	//GET (aggregate resource)

	@Path("/")
	@GET
	@Produces("application/json")

	public SearchModel getSearchList(@QueryParam("searchKeyword") String searchKeyword, @QueryParam("searchRESTService") String searchRESTService, @QueryParam("searchRESTResource") String searchResource, @QueryParam("searchRESTMethod") String searchRESTMethod, @QueryParam("searchRESTParameter") String searchRESTParameter, @QueryParam("searchSOAPService") String searchSOAPService, @QueryParam("searchSOAPOperation") String searchSOAPOperation, @QueryParam("searchSOAPParameter") String searchSOAPParameter)
	{
		//create a new get<resourceName>ListHandler
		oGETSearchListHandler = new GETSearchListHandler( searchKeyword, searchRESTService, searchResource, searchRESTMethod,  searchRESTParameter, searchSOAPService, searchSOAPOperation, searchSOAPParameter, oApplicationUri);
		return oGETSearchListHandler.getSearchList();
	}
}