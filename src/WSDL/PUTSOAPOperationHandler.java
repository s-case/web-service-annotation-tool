package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class PUTSOAPOperationHandler
{

    private SOAPServiceModel oSOAPService;
    private SOAPOperationModel oSOAPOperation;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    PUTSOAPOperationHandler(int SOAPServiceId, int SOAPOperationId, SOAPOperationModel oSOAPOperation, UriInfo applicationUri)
    {
        oSOAPService = new SOAPServiceModel();
        oSOAPService.setSOAPServiceId(SOAPServiceId);
        this.oSOAPOperation = oSOAPOperation;
        this.oSOAPOperation.setSOAPOperationId(SOAPOperationId);
        oSOAPOperation.setSOAPService( this.oSOAPService);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public void setSOAPService(SOAPServiceModel oSOAPService)
    {
        this.oSOAPService = oSOAPService;
    }
    public SOAPServiceModel getSOAPService()
    {
        return this.oSOAPService;
    }

    public SOAPOperationModel putSOAPOperation()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.putSOAPOperation( oSOAPOperation));
    }

    public SOAPOperationModel createHypermediaURIs(SOAPOperationModel oSOAPOperation)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update again SOAPOperation","PUT","Sibling"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the updated SOAPOperation","GET","Sibling"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the updated SOAPOperation","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"InputMessage"),"Create a new InputMessage for this SOAPOperation", "POST", "Child"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"InputMessage"),"GET all the InputMessage of this SOAPOperation", "GET", "Child"));

        //add the child hypermedia links POST, GETL

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