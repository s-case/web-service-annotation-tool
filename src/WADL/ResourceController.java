package WADL;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/account/{accountId}/RESTService/{RESTServiceId}/resource")
public class ResourceController
{
	private ResourceModel oResource; //resourceModel object
	private POSTResourceHandler oPOSTResourceHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETResourceHandler oGETResourceHandler;
	private PUTResourceHandler oPUTResourceHandler;
	private DELETEResourceHandler oDELETEResourceHandler;
	private GETResourceListHandler oGETResourceListHandler; // only if the resource is of aggregate type
	
	@Context
	private UriInfo oApplicationUri;
	
	public ResourceController() {}
	
	//placeholder to add any HTTPActivity() template operation
	@Path("/{resourceId}")
	@GET
	@Produces("application/json")

	public ResourceModel getResource(@PathParam("resourceId") int resourceId)
	{
		//create a new get<resourceName>Handler
		oGETResourceHandler = new GETResourceHandler(resourceId,oApplicationUri);
		return oGETResourceHandler.getResource();
	}
	 
	 @Path("/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public ResourceModel postResource (@PathParam("RESTServiceId") int RESTServiceId, ResourceModel oResource )
	 {
		//create a new post<resourceName>Handler
		oPOSTResourceHandler = new POSTResourceHandler(RESTServiceId, oResource,oApplicationUri);
		return oPOSTResourceHandler.postResource();
	 }
	 
	 @Path("/{resourceId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public ResourceModel putResource (@PathParam("RESTServiceId") int RESTServiceId, @PathParam("resourceId") int resourceId, ResourceModel oResource)
	 {
		//create a new put<resourceName>Handler
		oPUTResourceHandler = new PUTResourceHandler(RESTServiceId, resourceId, oResource,oApplicationUri);
		return oPUTResourceHandler.putResource();
	 }
	 
	 //DELETE
	 //[ONLY IF AUTH!= NO] --> added only if the authentication option is yes or both
	//[ONLY IF AUTH=BOTH] --> added only if the authentication option is both
	// [ONLY IF RESOURCE IS RELATED OF ANOTHER] --> added only if the resource is a related resource of another resource

	@Path("/{resourceId}")
	@Produces("application/json")
	@DELETE

	public ResourceModel deleteResource (@PathParam("resourceId") int resourceId)
	{
		//create a new delete<resourceName>Handler
		oDELETEResourceHandler = new DELETEResourceHandler(resourceId,oApplicationUri);
		return oDELETEResourceHandler.deleteResource();
	}

	@Path("/")
	@GET
	@Produces("application/json")

	public ResourceModel getResourceList(@PathParam("RESTServiceId") int RESTServiceId)
	{
		//create a new get<resourceName>ListHandler
		oGETResourceListHandler = new GETResourceListHandler(RESTServiceId,oApplicationUri);
		return oGETResourceListHandler.getResourceList();
	}
	 
}