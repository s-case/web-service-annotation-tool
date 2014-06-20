package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class PUTInputMessageHandler
{

    private SOAPOperationModel oSOAPOperation;
    private InputMessageModel oInputMessage;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    PUTInputMessageHandler(int SOAPOperationId, int inputMessageId, InputMessageModel oInputMessage, UriInfo applicationUri)
    {
        oSOAPOperation = new SOAPOperationModel();
        oSOAPOperation.setSOAPOperationId(SOAPOperationId);
        this.oInputMessage = oInputMessage;
        this.oInputMessage.setInputMessageId(inputMessageId);
        oInputMessage.setSOAPOperation( this.oSOAPOperation);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }


    public void setSOAPOperation(SOAPOperationModel oSOAPOperation)
    {
        this.oSOAPOperation = oSOAPOperation;
    }

    public SOAPOperationModel getSOAPOperation()
    {
        return this.oSOAPOperation;
    }

    public InputMessageModel putInputMessage()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.putInputMessage( oInputMessage));
    }

    public InputMessageModel createHypermediaURIs(InputMessageModel oInputMessage)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update again InputMessage","PUT","Sibling"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the updated InputMessage","GET","Sibling"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the updated InputMessage","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL


        oRelativePath = String.format("%s/%s","multiInputParameter",oApplicationUri.getPath());

        oInputMessage.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"InputParameter"),"Create a new InputParameter for this InputMessage", "POST", "Child"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"InputParameter"),"GET all the InputParameter of this InputMessage", "GET", "Child"));


        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new InputMessage","POST","Parent"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all InputMessage of this InputParameter","GET","Parent"));

        return oInputMessage;
    }

}