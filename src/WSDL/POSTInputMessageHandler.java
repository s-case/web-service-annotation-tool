package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class POSTInputMessageHandler
{
    private SOAPOperationModel oSOAPOperation;
    private InputMessageModel oInputMessage;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    POSTInputMessageHandler(int SOAPOperationId, InputMessageModel oInputMessage, UriInfo applicationUri)
    {
        oSOAPOperation = new SOAPOperationModel();
        oSOAPOperation.setSOAPOperationId(SOAPOperationId);
        this.oInputMessage = oInputMessage;
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

    public InputMessageModel postInputMessage()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.postInputMessage( oInputMessage));
    }

    public InputMessageModel createHypermediaURIs(InputMessageModel oInputMessage)
    {
        //add the sibling hypermedia links POST and GET list

        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of InputMessage","GET","Sibling"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new InputMessage","POST","Sibling"));


        //add the child hypermedia links GET, PUT, DELETE

        oInputMessage.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oInputMessage.getInputMessageId()),"GET created InputMessage", "GET", "Child"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oInputMessage.getInputMessageId()),"Update created InputMessage", "PUT", "Child"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oInputMessage.getInputMessageId()),"DELETE created InputMessage", "DELETE", "Child"));

        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update SOAPOperation","PUT","Parent"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read SOAPOperation","GET","Parent"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete SOAPOperation","DELETE","Parent"));

        return oInputMessage;
    }
}

