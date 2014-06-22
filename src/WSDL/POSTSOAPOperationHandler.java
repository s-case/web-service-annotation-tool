package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class POSTSOAPOperationHandler
{
    private SOAPServiceModel oSOAPService;
    private SOAPOperationModel oSOAPOperation;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    POSTSOAPOperationHandler(int SOAPServiceId, SOAPOperationModel oSOAPOperation, UriInfo applicationUri)
    {
        oSOAPService = new SOAPServiceModel();
        oSOAPService.setSOAPServiceId(SOAPServiceId);
        this.oSOAPOperation = oSOAPOperation;
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

    public SOAPOperationModel postSOAPOperation()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.postSOAPOperation( oSOAPOperation));
    }

    public SOAPOperationModel createHypermediaURIs(SOAPOperationModel oSOAPOperation)
    {
        //add the sibling hypermedia links POST and GET list

        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of SOAPOperation","GET","Sibling"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new SOAPOperation","POST","Sibling"));


        //add the child hypermedia links GET, PUT, DELETE

        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oSOAPOperation.getSOAPOperationId()),"GET created SOAPOperation", "GET", "Child"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oSOAPOperation.getSOAPOperationId()),"Update created SOAPOperation", "PUT", "Child"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oSOAPOperation.getSOAPOperationId()),"DELETE created SOAPOperation", "DELETE", "Child"));

        String oRelativePath;
        //add the parent hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update SOAPService","PUT","Parent"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read SOAPService","GET","Parent"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete SOAPService","DELETE","Parent"));

        return oSOAPOperation;
    }
}