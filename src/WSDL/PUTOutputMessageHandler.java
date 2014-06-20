package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class PUTOutputMessageHandler
{

    private SOAPOperationModel oSOAPOperation;
    private OutputMessageModel oOutputMessage;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    PUTOutputMessageHandler(int SOAPOperationId, int outputMessageId, OutputMessageModel oOutputMessage, UriInfo applicationUri)
    {
        oSOAPOperation = new SOAPOperationModel();
        oSOAPOperation.setSOAPOperationId(SOAPOperationId);
        this.oOutputMessage = oOutputMessage;
        this.oOutputMessage.setOutputMessageId(outputMessageId);
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

    public OutputMessageModel putOutputMessage()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.putOutputMessage( oOutputMessage));
    }

    public OutputMessageModel createHypermediaURIs(OutputMessageModel oOutputMessage)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update again OutputMessage","PUT","Sibling"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the updated OutputMessage","GET","Sibling"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the updated OutputMessage","DELETE","Sibling"));


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