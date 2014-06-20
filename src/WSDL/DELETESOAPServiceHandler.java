package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class DELETESOAPServiceHandler
{

    private SQLITEController oSQLITEController;
    private SOAPServiceModel oSOAPService;
    private UriInfo		 oApplicationUri;

    DELETESOAPServiceHandler(int SOAPServiceId, UriInfo applicationUri)
    {
        oSOAPService = new SOAPServiceModel();
        oSOAPService.setSOAPServiceId(SOAPServiceId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public SOAPServiceModel deleteSOAPService()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.deleteSOAPService( oSOAPService));
    }

    public SOAPServiceModel createHypermediaURIs(SOAPServiceModel oSOAPService)
    {

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new SOAPService","POST","Parent"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all SOAPService of this Account","GET","Parent"));

        return oSOAPService;
    }

}