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


@Path("/multiRESTParameter/")

public class RESTParameterController
{
	private RESTParameterModel oRESTParameter; //resourceModel object
	private POSTResourceRESTParameterHandler oPOSTResourceRESTParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETResourceRESTParameterHandler oGETResourceRESTParameterHandler;
	private PUTResourceRESTParameterHandler oPUTResourceRESTParameterHandler;
	private DELETEResourceRESTParameterHandler oDELETEResourceRESTParameterHandler;
	private POSTRESTMethodRESTParameterHandler oPOSTRESTMethodRESTParameterHandler;
	private GETRESTMethodRESTParameterHandler oGETRESTMethodRESTParameterHandler;
	private PUTRESTMethodRESTParameterHandler oPUTRESTMethodRESTParameterHandler;
	private DELETERESTMethodRESTParameterHandler oDELETERESTMethodRESTParameterHandler;
	private GETResourceRESTParameterListHandler oGETResourceRESTParameterListHandler; // only if the resource is of aggregate type
	private GETRESTMethodRESTParameterListHandler oGETRESTMethodRESTParameterListHandler;

	@Context
	private UriInfo oApplicationUri;
	
	public RESTParameterController() {}
	
	//placeholder to add any HTTPActivity() template operation

	@Path("/account/{accountId}/RESTService/{RESTServiceId}/resource/{resourceId}/RESTParameter/{RESTParameterId}")
	@GET
	@Produces("application/json")

	public RESTParameterModel getResourceRESTParameter(@PathParam("RESTParameterId") int RESTParameterId)
	{
		//create a new get<resourceName>Handler
		oGETResourceRESTParameterHandler = new GETResourceRESTParameterHandler(RESTParameterId);
		return oGETResourceRESTParameterHandler.getRESTParameter();
	}
	
	 
	 @Path("/account/{accountId}/RESTService/{RESTServiceId}/resource/{resourceId}/RESTParameter/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public RESTParameterModel postResourceRESTParameter (@PathParam("resourceId") int resourceId, RESTParameterModel oRESTParameter )
	 {
		//create a new post<resourceName>Handler
		oPOSTResourceRESTParameterHandler = new POSTResourceRESTParameterHandler(resourceId, oRESTParameter,oApplicationUri);
		return oPOSTResourceRESTParameterHandler.postRESTParameter();
	 }

	 @Path("/account/{accountId}/RESTService/{RESTServiceId}/resource/{resourceId}/RESTParameter/{RESTParameterId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public RESTParameterModel putResourceRESTParameter (@PathParam("resourceId") int resourceId, @PathParam("RESTParameterId") int RESTParameterId, RESTParameterModel oRESTParameter)
	 {
		//create a new put<resourceName>Handler
		oPUTResourceRESTParameterHandler = new PUTResourceRESTParameterHandler(resourceId, RESTParameterId, oRESTParameter);
		return oPUTResourceRESTParameterHandler.putRESTParameter();
	 }
	 
	@Path("/account/{accountId}/RESTService/{RESTServiceId}/resource/{resourceId}/RESTParameter/{RESTParameterId}")
	@DELETE

	public void deleteResourceRESTParameter (@PathParam("RESTParameterId") int RESTParameterId)
	{
		//create a new delete<resourceName>Handler
		oDELETEResourceRESTParameterHandler = new DELETEResourceRESTParameterHandler(RESTParameterId);
		oDELETEResourceRESTParameterHandler.deleteRESTParameter();
	}

/*
	@Path("/[ONLY IF IT IS RELATED RESOURCE OF MORE THAN ONE OTHER RESOURCES]<baseUri>/[ONLY IF IT IS RELATED RESOURCE OF MORE THAN ONE OTHER RESOURCES]<resourceName>/")
	@GET
	@Produces("application/vnd.<ApplicationName>+xml")

	public <resourceModel> get[ONLY IF IT IS RELATED RESOURCE OF MORE THAN ONE OTHER RESOURCES]<SourceResourceName><resourceName>List([ONLY IF AUTH=BOTH] @DefaultValue("guest")[ONLY IF AUTH!= NO] @HeaderParam("authorization") String authHeader, [ONLY IF RESOURCE IS RELATED OF ANOTHER] @PathParam("<sourceResourceIdentifierName>") <sourceResourceIdentifierType> <sourceResourceIdentifierName>)
	{
		//create a new get<resourceName>ListHandler
		oGET[ONLY IF IT IS RELATED RESOURCE OF MORE THAN ONE OTHER RESOURCES]<SourceResourceName><resourceName>ListHandler = new GET[ONLY IF IT IS RELATED RESOURCE OF MORE THAN ONE OTHER RESOURCES]<SourceResourceName><resourceName>ListHandler([ONLY IF AUTH!= NO] authHeader, [ONLY IF RESOURCE IS RELATED OF ANOTHER] <sourceResourceIdentifierName>);
		return oGET[ONLY IF IT IS RELATED RESOURCE OF MORE THAN ONE OTHER RESOURCES]<SourceResourceName><resourceName>ListHandler.get<resourceName>List();
	}
	*/
	
	@Path("/account/{accountId}/RESTService/{RESTServiceId}/resource/{resourceId}/RESTMethod/{RESTMethodId}/RESTParameter/{RESTParameterId}")
	@GET
	@Produces("application/json")

	public RESTParameterModel getRESTMethodRESTParameter(@PathParam("RESTParameterId") int RESTParameterId)
	{
		//create a new get<resourceName>Handler
		oGETRESTMethodRESTParameterHandler = new GETRESTMethodRESTParameterHandler(RESTParameterId);
		return oGETRESTMethodRESTParameterHandler.getRESTParameter();
	}
	 
	 @Path("/account/{accountId}/RESTService/{RESTServiceId}/resource/{resourceId}/RESTMethod/{RESTMethodId}/RESTParameter/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public RESTParameterModel postRESTMethodRESTParameter(@PathParam("RESTMethodId") int RESTMethodId, RESTParameterModel oRESTParameter )
	 {
		//create a new post<resourceName>Handler
		oPOSTRESTMethodRESTParameterHandler = new POSTRESTMethodRESTParameterHandler(RESTMethodId, oRESTParameter,oApplicationUri);
		return oPOSTRESTMethodRESTParameterHandler.postRESTParameter();
	 }
	 
	 @Path("/account/{accountId}/RESTService/{RESTServiceId}/resource/{resourceId}/RESTMethod/{RESTMethodId}/RESTParameter/{RESTParameterId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public RESTParameterModel putRESTMethodRESTParameter(@PathParam("RESTMethodId") int RESTMethodId, @PathParam("RESTParameterId") int RESTParameterId, RESTParameterModel oRESTParameter)
	 {
		//create a new put<resourceName>Handler
		oPUTRESTMethodRESTParameterHandler = new PUTRESTMethodRESTParameterHandler(RESTMethodId, RESTParameterId, oRESTParameter);
		return oPUTRESTMethodRESTParameterHandler.putRESTParameter();
	 }
	 
	@Path("/account/{accountId}/RESTService/{RESTServiceId}/resource/{resourceId}/RESTMethod/{RESTMethodId}/RESTParameter/{RESTParameterId}")
	@DELETE

	public void deleteRESTMethodRESTParameter(@PathParam("RESTParameterId") int RESTParameterId)
	{
		//create a new delete<resourceName>Handler
		oDELETERESTMethodRESTParameterHandler = new DELETERESTMethodRESTParameterHandler(RESTParameterId);
		oDELETERESTMethodRESTParameterHandler.deleteRESTParameter();
	}


	@Path("/account/{accountId}/RESTService/{RESTServiceId}/resource/{resourceId}/RESTParameter/")
	@GET
	@Produces("application/json")

	public RESTParameterModel getResourceRESTParameterList(@PathParam("resourceId") int resourceId)
	{
		//create a new get<resourceName>ListHandler
		oGETResourceRESTParameterListHandler = new GETResourceRESTParameterListHandler(resourceId,oApplicationUri);
		return oGETResourceRESTParameterListHandler.getRESTParameterList();
	}
	
	@Path("account/{accountId}/RESTService/{RESTServiceId}/resource/{resourceId}/RESTMethod/{RESTMethodId}/RESTParameter/")
	@GET
	@Produces("application/json")

	public RESTParameterModel getRESTMethodRESTParameterList(@PathParam("RESTMethodId") int RESTMethodId)
	{
		//create a new get<resourceName>ListHandler
		oGETRESTMethodRESTParameterListHandler = new GETRESTMethodRESTParameterListHandler(RESTMethodId,oApplicationUri);
		return oGETRESTMethodRESTParameterListHandler.getRESTParameterList();
	}
	 
}