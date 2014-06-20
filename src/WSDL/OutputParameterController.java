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

@Path("/multiOutputParameter/")

public class OutputParameterController
{
	private OutputParameterModel oOutputParameter; //resourceModel object
	private POSTOutputMessageOutputParameterHandler oPOSTOutputMessageOutputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETOutputMessageOutputParameterHandler oGETOutputMessageOutputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private PUTOutputMessageOutputParameterHandler oPUTOutputMessageOutputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private DELETEOutputMessageOutputParameterHandler oDELETEOutputMessageOutputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETOutputMessageOutputParameterListHandler oGETOutputMessageOutputParameterListHandler; // only if the resource is of aggregate type

	private POSTOutputParameterOutputParameterHandler oPOSTOutputParameterOutputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETOutputParameterOutputParameterHandler oGETOutputParameterOutputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private PUTOutputParameterOutputParameterHandler oPUTOutputParameterOutputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private DELETEOutputParameterOutputParameterHandler oDELETEOutputParameterOutputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETOutputParameterOutputParameterListHandler oGETOutputParameterOutputParameterListHandler; // only if the resource is of aggregate type

    @Context
    private UriInfo oApplicationUri;

	public OutputParameterController() {}
	
	//placeholder to add any HTTPActivity() template operation
	//GET for individual resource

	@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationID}/OutputMessage/{outputMessageId}/OutputParameter/{outputParameterId}")
	@GET
	@Produces("application/json")

	public OutputParameterModel getOutputMessageOutputParameter(@PathParam("outputParameterId") int outputParameterId)
	{
		//create a new get<resourceName>Handler
		oGETOutputMessageOutputParameterHandler = new GETOutputMessageOutputParameterHandler(outputParameterId,oApplicationUri);
		return oGETOutputMessageOutputParameterHandler.getOutputParameter();
	}
	 
	 //POST
	 
	 @Path("/account/{accountid}/SOAPService/{SOAPServiceid}/SOAPOperation/{SOAPOperationId}/OutputMessage/{outputMessageId}/OutputParameter/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public OutputParameterModel postOutputMessageOutputParameter(@PathParam("outputMessageId") int outputMessageId, OutputParameterModel oOutputParameter )
	 {
		//create a new post<resourceName>Handler
		oPOSTOutputMessageOutputParameterHandler = new POSTOutputMessageOutputParameterHandler(outputMessageId, oOutputParameter,oApplicationUri);
		return oPOSTOutputMessageOutputParameterHandler.postOutputParameter();
	 }
	 
	//PUT
	 
	 @Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationID}/OutputMessage/{outputMessageId}/OutputParameter/{outputParameterId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public OutputParameterModel putOutputMessageOutputParameter(@PathParam("outputMessageId") int outputMessageId, @PathParam("outputParameterId") int outputParameterId, OutputParameterModel oOutputParameter)
	 {
		//create a new put<resourceName>Handler
		oPUTOutputMessageOutputParameterHandler = new PUTOutputMessageOutputParameterHandler(outputMessageId, outputParameterId, oOutputParameter,oApplicationUri);
		return oPUTOutputMessageOutputParameterHandler.putOutputParameter();
	 }
	 
	 //DELETE

	@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationID}/OutputMessage/{outputMessageId}/OutputParameter/{outputParameterId}")
	@Produces("application/json")
	@DELETE

	public OutputParameterModel deleteOutputMessageOutputParameter(@PathParam("outputParameterid") int outputParameterId)
	{
		//create a new delete<resourceName>Handler
		oDELETEOutputMessageOutputParameterHandler = new DELETEOutputMessageOutputParameterHandler(outputParameterId,oApplicationUri);
		return oDELETEOutputMessageOutputParameterHandler.deleteOutputParameter();
	}

	//GET (aggregate resource)

	@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationID}/OutputMessage/{outputMessageId}/OutputParameter/")
	@GET
	@Produces("application/json")

	public OutputParameterModel getOutputMessageOutputParameterList(@PathParam("outputMessageId") int outputMessageId)
	{
		//create a new get<resourceName>ListHandler
		oGETOutputMessageOutputParameterListHandler = new GETOutputMessageOutputParameterListHandler(outputMessageId, oApplicationUri);
		return oGETOutputMessageOutputParameterListHandler.getOutputParameterList();
	}
	 
	//GET for individual resource

	@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationID}/OutputMessage/{outputMessageId}/OutputParameter/{outputParameterId}/OutputParameter/{targetOutputParameterId}")
	@GET
	@Produces("application/json")

	public OutputParameterModel getOutputParameterOutputParameter(@PathParam("targetOutputParameterId") int targetOutputParameterId)
	{
		//create a new get<resourceName>Handler
		oGETOutputParameterOutputParameterHandler = new GETOutputParameterOutputParameterHandler(targetOutputParameterId,oApplicationUri);
		return oGETOutputParameterOutputParameterHandler.getOutputParameter();
	}
	 
	 //POST
	 
	 @Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationID}/OutputMessage/{outputMessageId}/OutputParameter/{outputParameterId}/OutputParameter/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public OutputParameterModel postOutputParameterOutputParameter(@PathParam("outputParameterId") int outputParameterId, OutputParameterModel oOutputParameter)
	 {
		//create a new post<resourceName>Handler
		oPOSTOutputParameterOutputParameterHandler = new POSTOutputParameterOutputParameterHandler(outputParameterId, oOutputParameter,oApplicationUri);
		return oPOSTOutputParameterOutputParameterHandler.postOutputParameter();
	 }
	 
	//PUT
	 
	 @Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationID}/OutputMessage/{outputMessageId}/OutputParameter/{outputParameterId}/OutputParameter/{targetOutputParameterId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public OutputParameterModel putOutputParameterOutputParameter(@PathParam("outputParameterId") int outputParameterid, @PathParam("targetOutputParameterId") int targetOutputParameterId, OutputParameterModel oOutputParameter)
	 {
		//create a new put<resourceName>Handler
		oPUTOutputParameterOutputParameterHandler = new PUTOutputParameterOutputParameterHandler(outputParameterid, targetOutputParameterId, oOutputParameter,oApplicationUri);
		return oPUTOutputParameterOutputParameterHandler.putOutputParameter();
	 }
	 
	 //DELETE

	@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationID}/OutputMessage/{outputMessageId}/OutputParameter/{outputParameterId}/OutputParameter/{targetOutputParameterId}")
	@Produces("application/json")
	@DELETE

	public OutputParameterModel deleteOutputParameterOutputParameter(@PathParam("targetOutputParameterId") int targetOutputParameterId)
	{
		//create a new delete<resourceName>Handler
		oDELETEOutputParameterOutputParameterHandler = new DELETEOutputParameterOutputParameterHandler(targetOutputParameterId,oApplicationUri);
		return oDELETEOutputParameterOutputParameterHandler.deleteOutputParameter();
	}

	//GET (aggregate resource)

	@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationID}/OutputMessage/{outputMessageId}/OutputParameter/{outputParameterId}/OutputParameter/")
	@GET
	@Produces("application/json")

	public OutputParameterModel getOutputParameterOutputParameterList(@PathParam("outputParameterId") int outputParameterId)
	{
		//create a new get<resourceName>ListHandler
		oGETOutputParameterOutputParameterListHandler = new GETOutputParameterOutputParameterListHandler(outputParameterId, oApplicationUri);
		return oGETOutputParameterOutputParameterListHandler.getOutputParameterList();
	}
}