package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class GETInputMessageHandler
{
    private SQLITEController oSQLITEController;
    private InputMessageModel oInputMessage;
    private UriInfo		 oApplicationUri;

    GETInputMessageHandler(int inputMessageId, UriInfo applicationUri)
    {
        oInputMessage = new InputMessageModel();
        oInputMessage.setInputMessageId(inputMessageId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public InputMessageModel getInputMessage()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.getInputMessage( oInputMessage));
    }

    public InputMessageModel createHypermediaURIs(InputMessageModel oInputMessage)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update  InputMessage","PUT","Sibling"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET again the InputMessage","GET","Sibling"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the InputMessage","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = String.format("%s/%s","multiInputParameter",oApplicationUri.getPath());

        oInputMessage.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"InputParameter"),"Create a new InputParameter for this InputMessage", "POST", "Child"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"InputParameter"),"GET all the InputParameter of this InputMessage", "GET", "Child"));

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new InputMessage","POST","Parent"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all InputMessage of this SOAPOperation","GET","Parent"));

        return oInputMessage;
    }

}