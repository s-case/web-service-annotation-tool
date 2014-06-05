package WADL;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

//[ONLY IF RESOURCE IS RELATED OF ANOTHER] --> added only if the resource is a related resource of another resource


@Path("/account/{accountId}/RESTService")
public class RESTServiceController
{
	private RESTServiceModel oRESTService; //resourceModel object
	private POSTRESTServiceHandler oPOSTRESTServiceHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETRESTServiceHandler oGETRESTServiceHandler;
	private PUTRESTServiceHandler oPUTRESTServiceHandler;
	private DELETERESTServiceHandler oDELETERESTServiceHandler;
//	private GETRESTServiceListHandler oGETRESTServiceListHandler; // only if the resource is of aggregate type
	
	public RESTServiceController() {}
	
	//GET for individual resource
	@Path("/{RESTServiceId}")
	@GET
	@Produces("application/JSON")

	
	//TODO CHANGE RESTServiceModel to RESTServiceRepresentation
	public RESTServiceModel getRESTService(@PathParam("RESTServiceId") Integer RESTServiceId)
	{
		//create a new get<resourceName>Handler
		oGETRESTServiceHandler = new GETRESTServiceHandler(RESTServiceId);
		return oGETRESTServiceHandler.getRESTService();
	}
	 
	 //POST

	 @Path("/")
	 @POST
	 @Consumes("application/JSON")
	 @Produces("application/JSON")
	 
	 public RESTServiceModel postRESTService (@PathParam("accountId") Integer accountId, RESTServiceModel oRESTService )
	 {
		//create a new post<resourceName>Handler
		oPOSTRESTServiceHandler = new POSTRESTServiceHandler(accountId, oRESTService);
		return oPOSTRESTServiceHandler.postRESTService();
	 }
	 
	//PUT

	 @Path("/{RESTServiceId}")
	 @PUT
	 @Consumes("application/JSON")
	 @Produces("application/JSON")
	 
	 public RESTServiceModel putRESTService (@PathParam("accountId") Integer accountId, @PathParam("RESTServiceId") Integer RESTServiceId, RESTServiceModel oRESTService)
	 {
		//create a new put<resourceName>Handler
		oPUTRESTServiceHandler = new PUTRESTServiceHandler(accountId, RESTServiceId, oRESTService);
		return oPUTRESTServiceHandler.putRESTService();
	 }
	 
	 //DELETE

	@Path("/{RESTServiceId}")
	@DELETE

	public void deleteRESTService (@PathParam("RESTServiceId") Integer RESTServiceId)
	{
		//create a new delete<resourceName>Handler
		oDELETERESTServiceHandler = new DELETERESTServiceHandler(RESTServiceId);
		oDELETERESTServiceHandler.deleteRESTService();
	}

	//GET (aggregate resource)
//	@Path("/")
//	@GET
//	@Produces("application/JSON")

//	public RESTServiceModel getRESTServiceList(@PathParam("accountId") Integer accountId)
//	{
		//create a new get<resourceName>ListHandler
	//	oGETRESTServiceListHandler = new GETRESTServiceListHandler(accountId);
		//return oGETRESTServiceListHandler.getRESTServiceList();
	//}
}