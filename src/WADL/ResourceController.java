package WADL;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/account/{accountId}/RESTService/{RESTServiceId}/resource")
public class ResourceController
{
	private ResourceModel oResource; //resourceModel object
	private POSTResourceHandler oPOSTResourceHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETResourceHandler oGETResourceHandler;
	private PUTResourceHandler oPUTResourceHandler;
	private DELETEResourceHandler oDELETEResourceHandler;
//	[OPTIONAL]private GET<ResourceName>ListHandler oGET<ResourceName>ListHandler; // only if the resource is of aggregate type
	
	public ResourceController() {}
	
	//placeholder to add any HTTPActivity() template operation
	@Path("/{resourceId}")
	@GET
	@Produces("application/json")

	public ResourceModel getResource(@PathParam("resourceId") int resourceId)
	{
		//create a new get<resourceName>Handler
		oGETResourceHandler = new GETResourceHandler(resourceId);
		return oGETResourceHandler.getResource();
	}
	 
	 @Path("/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public ResourceModel postResource (@PathParam("RESTServiceId") int RESTServiceId, ResourceModel oResource )
	 {
		//create a new post<resourceName>Handler
		oPOSTResourceHandler = new POSTResourceHandler(RESTServiceId, oResource);
		return oPOSTResourceHandler.postResource();
	 }
	 
	 @Path("/{resourceId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public ResourceModel putResource (@PathParam("RESTServiceId") int RESTServiceId, @PathParam("resourceId") int resourceId, ResourceModel oResource)
	 {
		//create a new put<resourceName>Handler
		oPUTResourceHandler = new PUTResourceHandler(RESTServiceId, resourceId, oResource);
		return oPUTResourceHandler.putResource();
	 }
	 
	 //DELETE
	 //[ONLY IF AUTH!= NO] --> added only if the authentication option is yes or both
	//[ONLY IF AUTH=BOTH] --> added only if the authentication option is both
	// [ONLY IF RESOURCE IS RELATED OF ANOTHER] --> added only if the resource is a related resource of another resource

	@Path("/{resourceId}")
	@DELETE

	public void deleteResource (@PathParam("resourceId") int resourceId)
	{
		//create a new delete<resourceName>Handler
		oDELETEResourceHandler = new DELETEResourceHandler(resourceId);
		oDELETEResourceHandler.deleteResource();
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