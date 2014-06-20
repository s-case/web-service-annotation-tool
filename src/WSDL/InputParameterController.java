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

@Path("/multiInputParameter/")

public class InputParameterController
{
	private InputParameterModel oInputParameter; //resourceModel object
	private POSTInputMessageInputParameterHandler oPOSTInputMessageInputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETInputMessageInputParameterHandler oGETInputMessageInputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private PUTInputMessageInputParameterHandler oPUTInputMessageInputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private DELETEInputMessageInputParameterHandler oDELETEInputMessageInputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
    private GETInputMessageInputParameterListHandler oGETInputMessageInputParameterListHandler; // only if the resource is of aggregate type

	private POSTInputParameterInputParameterHandler oPOSTInputParameterInputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETInputParameterInputParameterHandler oGETInputParameterInputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private PUTInputParameterInputParameterHandler oPUTInputParameterInputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
	private DELETEInputParameterInputParameterHandler oDELETEInputParameterInputParameterHandler;// add all the activity handlers this controller is associated with in the PSM
    private GETInputParameterInputParameterListHandler oGETInputParameterInputParameterListHandler; // only if the resource is of aggregate type

    @Context
    private UriInfo oApplicationUri;

	public InputParameterController() {}
	
	//placeholder to add any HTTPActivity() template operation

	@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationId}/InputMessage/{inputMessageId}/InputParameter/{inputParameterId}")
	@GET
	@Produces("application/json")

	public InputParameterModel getInputMessageInputParameter(@PathParam("inputParameterId") int inputParameterId)
	{
		//create a new get<resourceName>Handler
		oGETInputMessageInputParameterHandler = new GETInputMessageInputParameterHandler(inputParameterId,oApplicationUri);
		return oGETInputMessageInputParameterHandler.getInputParameter();
	}
	 
	 //POST
	 
	 @Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationId}/InputMessage/{inputMessageId}/InputParameter/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public InputParameterModel postInputMessageInputParameter(@PathParam("inputMessageId") int inputMessageId, InputParameterModel oInputParameter )
	 {
		//create a new post<resourceName>Handler
		oPOSTInputMessageInputParameterHandler = new POSTInputMessageInputParameterHandler(inputMessageId, oInputParameter,oApplicationUri);
		return oPOSTInputMessageInputParameterHandler.postInputParameter();
	 }
	 
	//PUT
	 
	 @Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationId}/InputMessage/{inputMessageId}/InputParameter/{inputParameterId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public InputParameterModel putInputMessageInputParameter(@PathParam("inputMessageId") int inputMessageId, @PathParam("inputParameterId") int inputParameterId, InputParameterModel oInputParameter)
	 {
		//create a new put<resourceName>Handler
		oPUTInputMessageInputParameterHandler = new PUTInputMessageInputParameterHandler(inputMessageId, inputParameterId, oInputParameter,oApplicationUri);
		return oPUTInputMessageInputParameterHandler.putInputParameter();
	 }
	 
	 //DELETE

	@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationId}/InputMessage/{inputMessageId}/InputParameter/{inputParameterId}")
	@Produces("application/json")
	@DELETE

	public InputParameterModel deleteInputMessageInputParameter( @PathParam("inputParameter") int inputParameterId)
	{
		//create a new delete<resourceName>Handler
		oDELETEInputMessageInputParameterHandler = new DELETEInputMessageInputParameterHandler(inputParameterId,oApplicationUri);
		return oDELETEInputMessageInputParameterHandler.deleteInputParameter();
	}

	//GET (aggregate resource)

	@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationId}/InputMessage/{inputMessageId}/InputParameter/")
	@GET
	@Produces("application/json")

	public InputParameterModel getInputMessageInputParameterList(@PathParam("inputMessageId") int inputMessageId)
	{
		//create a new get<resourceName>ListHandler
		oGETInputMessageInputParameterListHandler = new GETInputMessageInputParameterListHandler(inputMessageId, oApplicationUri);
		return oGETInputMessageInputParameterListHandler.getInputParameterList();
	}
	
	//GET for individual resource

	@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationId}/InputMessage/{inputMessageId}/InputParameter/{inputParameterId}/InputParameter/{targetInputParameterId}")
	@GET
	@Produces("application/json")

	public InputParameterModel getInputParameterInputParameter(@PathParam("targetInputParameterId") int targetInputParameterId)
	{
		//create a new get<resourceName>Handler
		oGETInputParameterInputParameterHandler = new GETInputParameterInputParameterHandler(targetInputParameterId,oApplicationUri);
		return oGETInputParameterInputParameterHandler.getInputParameter();
	}
	 
	 //POST
	 
	 @Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationId}/InputMessage/{inputMessageId}/InputParameter/{inputParameterId}/InputParameter/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public InputParameterModel postInputParameterInputParameter(@PathParam("inputParameterId") int inputParameterId, InputParameterModel oInputParameter )
	 {
		//create a new post<resourceName>Handler
		oPOSTInputParameterInputParameterHandler = new POSTInputParameterInputParameterHandler(inputParameterId, oInputParameter,oApplicationUri);
		return oPOSTInputParameterInputParameterHandler.postInputParameter();
	 }
	 
	//PUT
	 
	 @Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationId}/InputMessage/{inputMessageId}/InputParameter/{inputParameterId}/InputParameter/{targetInputParameterId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public InputParameterModel putInputParameterInputParameter(@PathParam("inputParameterId") int inputParameterId, @PathParam("targetInputParameterId") int targetInputParameterId, InputParameterModel oInputParameter)
	 {
		//create a new put<resourceName>Handler
		oPUTInputParameterInputParameterHandler = new PUTInputParameterInputParameterHandler(inputParameterId, targetInputParameterId, oInputParameter,oApplicationUri);
		return oPUTInputParameterInputParameterHandler.putInputParameter();
	 }
	 
	 //DELETE

	@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationId}/InputMessage/{inputMessageId}/InputParameter/{inputParameterId}/InputParameter/{targetInputParameterId}")
	@Produces("application/json")
	@DELETE

	public InputParameterModel deleteInputParameterInputParameter(@PathParam("targetInputParameterId") int targetInputParameterId)
	{
		//create a new delete<resourceName>Handler
		oDELETEInputParameterInputParameterHandler = new DELETEInputParameterInputParameterHandler(targetInputParameterId,oApplicationUri);
		return oDELETEInputParameterInputParameterHandler.deleteInputParameter();
	}

	//GET (aggregate resource)
	 //[ONLY IF AUTH!= NO] --> added only if the authentication option is yes or both
	//[ONLY IF AUTH=BOTH] --> added only if the authentication option is both
	// [ONLY IF RESOURCE IS RELATED OF ANOTHER] --> added only if the resource is a related resource of another resource

	@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationId}/InputMessage/{inputMessageId}/InputParameter/{inputParameterId}/InputParameter/")
	@GET
	@Produces("application/json")

	public InputParameterModel getInputParameterInputParameterList(@PathParam("inputParameterId") int inputParameterId)
	{
		//create a new get<resourceName>ListHandler
		oGETInputParameterInputParameterListHandler = new GETInputParameterInputParameterListHandler(inputParameterId, oApplicationUri);
		return oGETInputParameterInputParameterListHandler.getInputParameterList();
	}
}