package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class DELETEOutputMessageHandler
{

    private SQLITEController oSQLITEController;
    private OutputMessageModel oOutputMessage;
    private UriInfo		 oApplicationUri;

    DELETEOutputMessageHandler(int outputMessageId, UriInfo applicationUri)
    {
        oOutputMessage = new OutputMessageModel();
        oOutputMessage.setOutputMessageId(outputMessageId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public OutputMessageModel deleteOutputMessage()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.deleteOutputMessage( oOutputMessage));
    }

    public OutputMessageModel createHypermediaURIs(OutputMessageModel oOutputMessage)
    {

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new OutputMessage","POST","Parent"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all OutputMessage of this SOAPOperation","GET","Parent"));

        return oOutputMessage;
    }

}