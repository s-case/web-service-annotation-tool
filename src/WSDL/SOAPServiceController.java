package WSDL;

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

@Path("/account/{accountId}/SOAPService")

public class SOAPServiceController
{
	private SOAPServiceModel oSOAPService; //resourceModel object
	private POSTSOAPServiceHandler oPOSTSOAPServiceHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETSOAPServiceHandler oGETSOAPServiceHandler;// add all the activity handlers this controller is associated with in the PSM
	private PUTSOAPServiceHandler oPUTSOAPServiceHandler;// add all the activity handlers this controller is associated with in the PSM
	private DELETESOAPServiceHandler oDELETESOAPServiceHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETSOAPServiceListHandler oGETSOAPServiceListHandler; // only if the resource is of aggregate type

    @Context
    private UriInfo oApplicationUri;

	public SOAPServiceController() {}
	
	//placeholder to add any HTTPActivity() template operation
	//GET for individual resource

	@Path("{SOAPServiceId}")
	@GET
	@Produces("application/json")

	public SOAPServiceModel getSOAPService(@PathParam("SOAPServiceId") int SOAPServiceId)
	{
		//create a new get<resourceName>Handler
		oGETSOAPServiceHandler = new GETSOAPServiceHandler(SOAPServiceId,oApplicationUri);
		return oGETSOAPServiceHandler.getSOAPService();
	}
	 
	 //POST
	 @Path("/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public SOAPServiceModel postSOAPService(@PathParam("accountId") int accountId, SOAPServiceModel oSOAPService )
	 {
		//create a new post<resourceName>Handler
		oPOSTSOAPServiceHandler = new POSTSOAPServiceHandler(accountId, oSOAPService,oApplicationUri);
		return oPOSTSOAPServiceHandler.postSOAPService();
	 }
	 
	//PUT
	 
	 @Path("/{SOAPServiceId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public SOAPServiceModel putSOAPService(@PathParam("accountId") int accountId, @PathParam("SOAPServiceId") int SOAPServiceId, SOAPServiceModel oSOAPService)
	 {
		//create a new put<resourceName>Handler
		oPUTSOAPServiceHandler = new PUTSOAPServiceHandler(accountId, SOAPServiceId, oSOAPService,oApplicationUri);
		return oPUTSOAPServiceHandler.putSOAPService();
	 }
	 
	 //DELETE

	@Path("/{SOAPServiceId}")
	@Produces("application/json")
	@DELETE

	public SOAPServiceModel deleteSOAPService( @PathParam("SOAPServiceId") int SOAPServiceId)
	{
		//create a new delete<resourceName>Handler
		oDELETESOAPServiceHandler = new DELETESOAPServiceHandler(SOAPServiceId,oApplicationUri);
		return oDELETESOAPServiceHandler.deleteSOAPService();
	}

	//GET (aggregate resource)

	@Path("/")
	@GET
	@Produces("application/json")

	public SOAPServiceModel getSOAPServiceList(@PathParam("accountId") int accountId)
	{
		//create a new get<resourceName>ListHandler
		oGETSOAPServiceListHandler = new GETSOAPServiceListHandler(accountId, oApplicationUri);
		return oGETSOAPServiceListHandler.getSOAPServiceList();
	}
}