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

@Path("/account/{accountid}/SOAPService/{SOAPServiceId}/SOAPOperation")

public class SOAPOperationController
{
	private SOAPOperationModel oSOAPOperation; //resourceModel object
	private POSTSOAPOperationHandler oPOSTSOAPOperationHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETSOAPOperationHandler oGETSOAPOperationHandler;// add all the activity handlers this controller is associated with in the PSM
	private PUTSOAPOperationHandler oPUTSOAPOperationHandler;// add all the activity handlers this controller is associated with in the PSM
	private DELETESOAPOperationHandler oDELETESOAPOperationHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETSOAPOperationListHandler oGETSOAPOperationListHandler; // only if the resource is of aggregate type

    @Context
    private UriInfo oApplicationUri;

	public SOAPOperationController() {}
	
	//placeholder to add any HTTPActivity() template operation

	@Path("/{SOAPOperationId}")
	@GET
	@Produces("application/json")

	public SOAPOperationModel getSOAPOperation(@PathParam("SOAPOperationId") int SOAPOperationId)
	{
		//create a new get<resourceName>Handler
		oGETSOAPOperationHandler = new GETSOAPOperationHandler(SOAPOperationId,oApplicationUri);
		return oGETSOAPOperationHandler.getSOAPOperation();
	}
	 
	 //POST

	 
	 @Path("/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public SOAPOperationModel postSOAPOperation(@PathParam("SOAPServiceid") int SOAPServiceId, SOAPOperationModel oSOAPOperation )
	 {
		//create a new post<resourceName>Handler
		oPOSTSOAPOperationHandler = new POSTSOAPOperationHandler(SOAPServiceId, oSOAPOperation,oApplicationUri);
		return oPOSTSOAPOperationHandler.postSOAPOperation();
	 }
	 
	//PUT
	 
	 @Path("/{SOAPOperationId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public SOAPOperationModel putSOAPOperation(@PathParam("SOAPServiceId") int SOAPServiceId, @PathParam("SOAPOperationId") int SOAPOperationId, SOAPOperationModel oSOAPOperation)
	 {
		//create a new put<resourceName>Handler
		oPUTSOAPOperationHandler = new PUTSOAPOperationHandler(SOAPServiceId, SOAPOperationId, oSOAPOperation,oApplicationUri);
		return oPUTSOAPOperationHandler.putSOAPOperation();
	 }
	 
	 //DELETE

	@Path("/{SOAPOperationId}")
	@Produces("application/json")
	@DELETE

	public SOAPOperationModel deleteSOAPOperation( @PathParam("SOAPOperationId") int SOAPOperationId)
	{
		//create a new delete<resourceName>Handler
		oDELETESOAPOperationHandler = new DELETESOAPOperationHandler(SOAPOperationId,oApplicationUri);
		return oDELETESOAPOperationHandler.deleteSOAPOperation();
	}

	//GET (aggregate resource)

	@Path("/")
	@GET
	@Produces("application/jsonl")

	public SOAPOperationModel getSOAPOperationList(@PathParam("SOAPServiceId") int SOAPServiceId)
	{
		//create a new get<resourceName>ListHandler
		oGETSOAPOperationListHandler = new GETSOAPOperationListHandler(SOAPServiceId, oApplicationUri);
		return oGETSOAPOperationListHandler.getSOAPOperationList();
	}
	 
}