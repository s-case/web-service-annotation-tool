package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class GETOutputMessageHandler
{
    private SQLITEController oSQLITEController;
    private OutputMessageModel oOutputMessage;
    private UriInfo		 oApplicationUri;

    GETOutputMessageHandler(int outputMessageId, UriInfo applicationUri)
    {
        oOutputMessage = new OutputMessageModel();
        oOutputMessage.setOutputMessageId(outputMessageId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public OutputMessageModel getOutputMessage()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.getOutputMessage( oOutputMessage));
    }

    public OutputMessageModel createHypermediaURIs(OutputMessageModel oOutputMessage)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update  OutputMessage","PUT","Sibling"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET again the OutputMessage","GET","Sibling"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the OutputMessage","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = String.format("%s/%s","multiOutputParameter",oApplicationUri.getPath());

        oOutputMessage.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"OutputParameter"),"Create a new OutputParameter for this OutputMessage", "POST", "Child"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"OutputParameter"),"GET all the OutputParameter of this OutputMessage", "GET", "Child"));

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new OutputMessage","POST","Parent"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all OutputMessage of this SOAPOperation","GET","Parent"));

        return oOutputMessage;
    }

}