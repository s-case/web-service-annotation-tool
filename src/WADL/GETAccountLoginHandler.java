package WADL;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.core.util.Base64;

public class GETAccountLoginHandler
{

    private SQLITEController oSQLITEController;
    private String authHeader;
    private UriInfo		 oApplicationUri;
    private AccountModel oAccount;
    
    GETAccountLoginHandler(String authHeader, UriInfo applicationUri)
    {
        oAccount = new AccountModel();
        this.authHeader = authHeader;
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public void setAuthHeader( String authHeader)
    {
    	this.authHeader = authHeader;
    }

    public String getAuthHeader()
    {
        return this.authHeader;
    }

    public AccountModel checkLogin()
    {
    	//check if there is a non null authentication header
    	if(authHeader == null)
    	{
    		throw new WebApplicationException(Response.Status.FORBIDDEN);
    	}
    	
    	//decode the auth header
    	decodeAuthorizationHeader();
    	
        //get the <resourceName>List from the database
        oAccount = oSQLITEController.authenticatedUser(oAccount);
        if(oAccount == null)
        {
        	throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        return oAccount;
    }
    
    public void decodeAuthorizationHeader()
    {
    	//check if this request has basic authentication
    	if( !authHeader.contains("Basic "))
    	{
    		throw new WebApplicationException(Response.Status.BAD_REQUEST);
    	}
    	
        authHeader = authHeader.substring("Basic ".length());
        String[] decodedHeader;
        decodedHeader = Base64.base64Decode(authHeader).split(":");
        
        if( decodedHeader == null)
        {
        	throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        oAccount.setUsername(decodedHeader[0]);
        oAccount.setPassword(decodedHeader[1]);
    }


//TODO Extension is needed to support getList on resources that are not related resource of any other.
}