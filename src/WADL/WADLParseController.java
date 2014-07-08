package WADL;

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

@Path("/account/{accountId}/algoRESTService")

public class WADLParseController
{
	private RESTServiceModel oRESTService; //resourceModel object

	private POSTWADLParseHandler oPOSTWADLParseHandler; // only if the resource is of aggregate type

    @Context
    private UriInfo oApplicationUri;

	public WADLParseController() {}
	
	//placeholder to add any HTTPActivity() template operation

	 @Path("/WADLParse")
	 @POST
	 @Consumes("multipart/form-data")
	 @Produces("application/json")
	 
	 public RESTServiceModel postWADLParse(@PathParam("accountId") int accountId, @FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail )
	 {
		//create a new post<resourceName>Handler
		oPOSTWADLParseHandler = new POSTWADLParseHandler(accountId,oApplicationUri, uploadedInputStream, fileDetail.getFileName());
		return oPOSTWADLParseHandler.postWADLParse();
	 }
}