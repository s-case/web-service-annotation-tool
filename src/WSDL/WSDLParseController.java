package WSDL;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import WADL.POSTWADLParseHandler;
import WADL.RESTServiceModel;

@Path("/account/{accountId}/algoSOAPService")

public class WSDLParseController
{
	private SOAPServiceModel oSOAPService; //resourceModel object
	private POSTWSDLParseHandler oPOSTWSDLParseHandler;// add all the activity handlers this controller is associated with in the PSM

    @Context
    private UriInfo oApplicationUri;

	public WSDLParseController() {}
	
	//placeholder to add any HTTPActivity() template operation
	 @Path("/WSDLParse")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public SOAPServiceModel postWSDLParse(@PathParam("accountId") int accountId, @QueryParam("wsdlName") String wsdlName)
	 {
		//create a new post<resourceName>Handler
		oPOSTWSDLParseHandler = new POSTWSDLParseHandler(accountId,oApplicationUri,wsdlName);
		return oPOSTWSDLParseHandler.postWSDLParse();
	 }
}