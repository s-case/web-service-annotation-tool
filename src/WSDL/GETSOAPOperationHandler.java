package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class GETSOAPOperationHandler
{
    private SQLITEController oSQLITEController;
    private SOAPOperationModel oSOAPOperation;
    private UriInfo		 oApplicationUri;

    GETSOAPOperationHandler(int SOAPOperationId, UriInfo applicationUri)
    {
        oSOAPOperation = new SOAPOperationModel();
        oSOAPOperation.setSOAPOperationId(SOAPOperationId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public SOAPOperationModel getSOAPOperation()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.getSOAPOperation( oSOAPOperation));
    }

    public SOAPOperationModel createHypermediaURIs(SOAPOperationModel oSOAPOperation)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update  SOAPOperation","PUT","Sibling"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET again the SOAPOperation","GET","Sibling"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the SOAPOperation","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"InputMessage"),"Create a new InputMessage for this SOAPOperation", "POST", "Child"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"InputMessage"),"GET all the InputMessage of this SOAPOperation", "GET", "Child"));

        oRelativePath = oApplicationUri.getPath();

        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"OutputMessage"),"Create a new OutputMessage for this SOAPOperation", "POST", "Child"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"OutputMessage"),"GET all the OutputMessage of this SOAPOperation", "GET", "Child"));

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new SOAPOperation","POST","Parent"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all SOAPOperation of this SOAPService","GET","Parent"));

        return oSOAPOperation;
    }

}