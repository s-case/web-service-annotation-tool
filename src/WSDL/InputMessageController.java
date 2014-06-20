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


@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationId}/InputMessage")

public class InputMessageController
{
	private InputMessageModel oInputMessage; //resourceModel object
	private POSTInputMessageHandler oPOSTInputMessageHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETInputMessageHandler oGETInputMessageHandler;// add all the activity handlers this controller is associated with in the PSM
	private PUTInputMessageHandler oPUTInputMessageHandler;// add all the activity handlers this controller is associated with in the PSM
	private DELETEInputMessageHandler oDELETEInputMessageHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETInputMessageListHandler oGETInputMessageListHandler; // only if the resource is of aggregate type

    @Context
    private UriInfo oApplicationUri;

	public InputMessageController() {}
	
	//placeholder to add any HTTPActivity() template operation
	//GET for individual resource

	@Path("/{inputMessageId}")
	@GET
	@Produces("application/json")

	public InputMessageModel getInputMessage(@PathParam("inputMessageId") int inputMessageId)
	{
		//create a new get<resourceName>Handler
		oGETInputMessageHandler = new GETInputMessageHandler(inputMessageId,oApplicationUri);
		return oGETInputMessageHandler.getInputMessage();
	}
	 
	 //POST
	 
	 @Path("/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public InputMessageModel postInputMessage(@PathParam("SOAPOperationId") int SOAPOperationId, InputMessageModel oInputMessage )
	 {
		//create a new post<resourceName>Handler
		oPOSTInputMessageHandler = new POSTInputMessageHandler(SOAPOperationId, oInputMessage,oApplicationUri);
		return oPOSTInputMessageHandler.postInputMessage();
	 }
	 
	//PUT
	 
	 @Path("/{inputMessageId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public InputMessageModel putInputMessage(@PathParam("SOAPOperationId") int SOAPOperationId, @PathParam("inputMessageId") int inputMessageId, InputMessageModel oInputMessage)
	 {
		//create a new put<resourceName>Handler
		oPUTInputMessageHandler = new PUTInputMessageHandler(SOAPOperationId, inputMessageId, oInputMessage,oApplicationUri);
		return oPUTInputMessageHandler.putInputMessage();
	 }
	 
	 //DELETE

	@Path("/{inputMessageId}")
	@Produces("application/json")
	@DELETE

	public InputMessageModel deleteInputMessage(@PathParam("inputMessageId") int inputMessageId)
	{
		//create a new delete<resourceName>Handler
		oDELETEInputMessageHandler = new DELETEInputMessageHandler(inputMessageId,oApplicationUri);
		return oDELETEInputMessageHandler.deleteInputMessage();
	}

	//GET (aggregate resource)
	 //[ONLY IF AUTH!= NO] --> added only if the authentication option is yes or both
	//[ONLY IF AUTH=BOTH] --> added only if the authentication option is both
	// [ONLY IF RESOURCE IS RELATED OF ANOTHER] --> added only if the resource is a related resource of another resource

	@Path("/")
	@GET
	@Produces("application/json")

	public InputMessageModel getInputMessageList(@PathParam("SOAPOperationId") int SOAPOperationId)
	{
		//create a new get<resourceName>ListHandler
		oGETInputMessageListHandler = new GETInputMessageListHandler(SOAPOperationId, oApplicationUri);
		return oGETInputMessageListHandler.getInputMessageList();
	}
	 
}