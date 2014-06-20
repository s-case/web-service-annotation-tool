package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class GETSOAPServiceHandler
{
    private SQLITEController oSQLITEController;
    private SOAPServiceModel oSOAPService;
    private UriInfo		 oApplicationUri;

    GETSOAPServiceHandler(int SOAPServiceId, UriInfo applicationUri)
    {
        oSOAPService = new SOAPServiceModel();
        oSOAPService.setSOAPServiceId(SOAPServiceId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public SOAPServiceModel getSOAPService()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.getSOAPService( oSOAPService));
    }

    public SOAPServiceModel createHypermediaURIs(SOAPServiceModel oSOAPService)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update  SOAPService","PUT","Sibling"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET again the SOAPService","GET","Sibling"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the SOAPService","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        oSOAPService.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"SOAPOperation"),"Create a new SOAPOperation for this SOAPService", "POST", "Child"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"SOAPOperation"),"GET all the SOAPOperation of this SOAPService", "GET", "Child"));

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new SOAPService","POST","Parent"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all SOAPService of this Account","GET","Parent"));

        return oSOAPService;
    }

}