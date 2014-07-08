package WSDL;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

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
	 @Consumes("multipart/form-data")
	 @Produces("application/json")
	 
	 public SOAPServiceModel postWSDLParse(@PathParam("accountId") int accountId, @FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail)
	 {
		//create a new post<resourceName>Handler
		oPOSTWSDLParseHandler = new POSTWSDLParseHandler(accountId,oApplicationUri, uploadedInputStream, fileDetail.getFileName());
		return oPOSTWSDLParseHandler.postWSDLParse();
	 }
}