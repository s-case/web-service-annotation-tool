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

@Path("/account/{accountId}/SOAPService/{SOAPServiceId}/SOAPOperation/{SOAPOperationId}/OutputMessage")

public class OutputMessageController
{
	private OutputMessageModel oOutputMessage; //resourceModel object
	private POSTOutputMessageHandler oPOSTOutputMessageHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETOutputMessageHandler oGETOutputMessageHandler;// add all the activity handlers this controller is associated with in the PSM
	private PUTOutputMessageHandler oPUTOutputMessageHandler;// add all the activity handlers this controller is associated with in the PSM
	private DELETEOutputMessageHandler oDELETEOutputMessageHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETOutputMessageListHandler oGETOutputMessageListHandler; // only if the resource is of aggregate type

    @Context
    private UriInfo oApplicationUri;

	public OutputMessageController() {}
	
	//placeholder to add any HTTPActivity() template operation
	//GET for individual resource


	@Path("{outputMessageId}")
	@GET
	@Produces("application/json")

	public OutputMessageModel getOutputMessage(@PathParam("outputMessageId") int outputMessageId)
	{
		//create a new get<resourceName>Handler
		oGETOutputMessageHandler = new GETOutputMessageHandler(outputMessageId,oApplicationUri);
		return oGETOutputMessageHandler.getOutputMessage();
	}
	 
	 //POST
	 
	 @Path("/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public OutputMessageModel postOutputMessage(@PathParam("SOAPOperationId") int SOAPOperationId, OutputMessageModel oOutputMessage )
	 {
		//create a new post<resourceName>Handler
		oPOSTOutputMessageHandler = new POSTOutputMessageHandler(SOAPOperationId, oOutputMessage,oApplicationUri);
		return oPOSTOutputMessageHandler.postOutputMessage();
	 }
	 
	//PUT
	 
	 @Path("/{outputMessageId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public OutputMessageModel putOutputMessage( @PathParam("SOAPOperationId") int SOAPOperationId, @PathParam("outputMessageId") int outputMessageId, OutputMessageModel oOutputMessage)
	 {
		//create a new put<resourceName>Handler
		oPUTOutputMessageHandler = new PUTOutputMessageHandler(SOAPOperationId, outputMessageId, oOutputMessage,oApplicationUri);
		return oPUTOutputMessageHandler.putOutputMessage();
	 }
	 
	 //DELETE

	@Path("/{outputMessageId}")
	@Produces("application/json")
	@DELETE

	public OutputMessageModel deleteOutputMessage(@PathParam("outputMessageId") int outputMessageId)
	{
		//create a new delete<resourceName>Handler
		oDELETEOutputMessageHandler = new DELETEOutputMessageHandler(outputMessageId,oApplicationUri);
		return oDELETEOutputMessageHandler.deleteOutputMessage();
	}

	//GET (aggregate resource)

	@Path("/")
	@GET
	@Produces("application/json")

	public OutputMessageModel getOutputMessageList( @PathParam("SOAPOperationId") int SOAPOperationId)
	{
		//create a new get<resourceName>ListHandler
		oGETOutputMessageListHandler = new GETOutputMessageListHandler( SOAPOperationId, oApplicationUri);
		return oGETOutputMessageListHandler.getOutputMessageList();
	}
	 
}