package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class DELETESOAPOperationHandler
{

    private SQLITEController oSQLITEController;
    private SOAPOperationModel oSOAPOperation;
    private UriInfo		 oApplicationUri;

    DELETESOAPOperationHandler(int SOAPOperationId, UriInfo applicationUri)
    {
        oSOAPOperation = new SOAPOperationModel();
        oSOAPOperation.setSOAPOperationId(SOAPOperationId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public SOAPOperationModel deleteSOAPOperation()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.deleteSOAPOperation( oSOAPOperation));
    }

    public SOAPOperationModel createHypermediaURIs(SOAPOperationModel oSOAPOperation)
    {
        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new SOAPOperation","POST","Parent"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all SOAPOperation of this SOAPService","GET","Parent"));

        return oSOAPOperation;
    }

}