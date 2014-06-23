package WADL;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/account/{accountId}/algoRESTService")

public class WADLParseController
{
	private RESTServiceModel oRESTService; //resourceModel object

	private POSTWADLParseHandler oPOSTWADLParseHandler; // only if the resource is of aggregate type

    @Context
    private UriInfo oApplicationUri;

	public WADLParseController() {}
	
	//placeholder to add any HTTPActivity() template operation

	 @Path("/WADLParse")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public RESTServiceModel postWADLParse(@PathParam("accountId") int accountId)
	 {
		//create a new post<resourceName>Handler
		oPOSTWADLParseHandler = new POSTWADLParseHandler(accountId,oApplicationUri);
		return oPOSTWADLParseHandler.postWADLParse();
	 }
}