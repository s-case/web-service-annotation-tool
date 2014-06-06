package WADL;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/account/{accountId}/RESTService/{RESTServiceId}/resource/{resourceId}/RESTMethod")
public class RESTMethodController
{
	private RESTMethodModel oRESTMethod; //resourceModel object
	private POSTRESTMethodHandler oPOSTRESTMethodHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETRESTMethodHandler oGETRESTMethodHandler;
	private PUTRESTMethodHandler oPUTRESTMethodHandler;
	private DELETERESTMethodHandler oDELETERESTMethodHandler;
//	[OPTIONAL]private GET<ResourceName>ListHandler oGET<ResourceName>ListHandler; // only if the resource is of aggregate type
	
	public RESTMethodController() {}
	
	//placeholder to add any HTTPActivity() template operation

	@Path("/{RESTMethodId}")
	@GET
	@Produces("application/json")

	public RESTMethodModel getRESTMethod(@PathParam("RESTMethodId") int RESTMethodId)
	{
		//create a new get<resourceName>Handler
		oGETRESTMethodHandler = new GETRESTMethodHandler(RESTMethodId);
		return oGETRESTMethodHandler.getRESTMethod();
	}
	 
	 @Path("/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public RESTMethodModel postRESTMethod (@PathParam("resourceId") int resourceId, RESTMethodModel oRESTMethod )
	 {
		//create a new post<resourceName>Handler
		oPOSTRESTMethodHandler = new POSTRESTMethodHandler(resourceId, oRESTMethod);
		return oPOSTRESTMethodHandler.postRESTMethod();
	 }
	 
	 @Path("/{RESTMethodId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public RESTMethodModel putRESTMethod (@PathParam("resourceId") int resourceId, @PathParam("RESTMethodId") int RESTMethodId, RESTMethodModel oRESTMethod)
	 {
		//create a new put<resourceName>Handler
		oPUTRESTMethodHandler = new PUTRESTMethodHandler(resourceId, RESTMethodId, oRESTMethod);
		return oPUTRESTMethodHandler.putRESTMethod();
	 }
	 
	@Path("/{RESTMethodId}")
	@DELETE

	public void deleteRESTMethod (@PathParam("RESTMethodId") int RESTMethodId)
	{
		//create a new delete<resourceName>Handler
		oDELETERESTMethodHandler = new DELETERESTMethodHandler(RESTMethodId);
		oDELETERESTMethodHandler.deleteRESTMethod();
	}

/*
	@Path("/")
	@GET
	@Produces("application/vnd.<ApplicationName>+xml")

	public <resourceModel> get<resourceName>List([ONLY IF AUTH=BOTH] @DefaultValue("guest")[ONLY IF AUTH!= NO] @HeaderParam("authorization") String authHeader, [ONLY IF RESOURCE IS RELATED OF ANOTHER] @PathParam("<sourceResourceIdentifierName>") <sourceResourceIdentifierType> <sourceResourceIdentifierName>)
	{
		//create a new get<resourceName>ListHandler
		oGET<resourceName>ListHandler = new GET<resourceName>ListHandler([ONLY IF AUTH!= NO] authHeader, [ONLY IF RESOURCE IS RELATED OF ANOTHER] <sourceResourceIdentifierName>);
		return oGET<resourceName>ListHandler.get<resourceName>List();
	}
*/	 
}