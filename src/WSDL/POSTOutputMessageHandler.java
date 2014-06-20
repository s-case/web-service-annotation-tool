package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class POSTOutputMessageHandler
{
    private SOAPOperationModel oSOAPOperation;
    private OutputMessageModel oOutputMessage;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    POSTOutputMessageHandler(int SOAPOperationId, OutputMessageModel oOutputMessage, UriInfo applicationUri)
    {
        oSOAPOperation = new SOAPOperationModel();
        oSOAPOperation.setSOAPOperationId(SOAPOperationId);
        this.oOutputMessage = oOutputMessage;
        oOutputMessage.setSOAPOperation( this.oSOAPOperation);
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

    public OutputMessageModel postOutputMessage()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.postOutputMessage( oOutputMessage));
    }

    public OutputMessageModel createHypermediaURIs(OutputMessageModel oOutputMessage)
    {
        //add the sibling hypermedia links POST and GET list

        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of OutputMessage","GET","Sibling"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new OutputMessage","POST","Sibling"));


        //add the child hypermedia links GET, PUT, DELETE

        oOutputMessage.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oOutputMessage.getOutputMessageId()),"GET created OutputMessage", "GET", "Child"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oOutputMessage.getOutputMessageId()),"Update created OutputMessage", "PUT", "Child"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oOutputMessage.getOutputMessageId()),"DELETE created OutputMessage", "DELETE", "Child"));

        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update SOAPOperation","PUT","Parent"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read SOAPOperation","GET","Parent"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete SOAPOperation","DELETE","Parent"));

        return oOutputMessage;
    }
}

