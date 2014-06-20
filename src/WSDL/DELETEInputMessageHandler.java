package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class DELETEInputMessageHandler
{

    private SQLITEController oSQLITEController;
    private InputMessageModel oInputMessage;
    private UriInfo		 oApplicationUri;

    DELETEInputMessageHandler(int inputMessageId, UriInfo applicationUri)
    {
        oInputMessage = new InputMessageModel();
        oInputMessage.setInputMessageId(inputMessageId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public InputMessageModel deleteInputMessage()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.deleteInputMessage( oInputMessage));
    }

    public InputMessageModel createHypermediaURIs(InputMessageModel oInputMessage)
    {
        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new InputMessage","POST","Parent"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all InputMessage of this SOAPOperation","GET","Parent"));

        return oInputMessage;
    }

}