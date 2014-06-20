package WSDL;

import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class GETSOAPOperationListHandler
{

    private SQLITEController oSQLITEController;
    private SOAPServiceModel oSOAPService;
    private UriInfo		 oApplicationUri;

    GETSOAPOperationListHandler(int SOAPServiceId, UriInfo applicationUri)
    {
        oSOAPService = new SOAPServiceModel();
        oSOAPService.setSOAPServiceId(SOAPServiceId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public SOAPOperationModel getSOAPOperationList()
    {
        //TODO add authentication if needed

        //get the <resourceName>List from the database
        oSOAPService = oSQLITEController.getSOAPOperationList( oSOAPService);
        return createHypermediaURIs(oSOAPService);
    }


    public SOAPOperationModel createHypermediaURIs(SOAPServiceModel oSOAPService)
    {
        //create an empty <resourceModel> and populate its linklists with hypermedia links
    	SOAPOperationModel oSOAPOperation = new SOAPOperationModel();

        //add the sibling hypermedia links POST and GET list

        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of SOAPOperation","GET","Sibling"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new SOAPOperation","POST","Sibling"));


        //add the children hypermedia links GET
        Iterator<SOAPOperationModel> setIterator = oSOAPService.getSetOfSOAPOperation().iterator();

        while(setIterator.hasNext())
        {
        	SOAPOperationModel oNextSOAPOperation = new SOAPOperationModel();
            oNextSOAPOperation = setIterator.next();
            oSOAPOperation.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextSOAPOperation.getSOAPOperationId()),String.format("%s",oNextSOAPOperation.getName()), "GET", "Child"));
        }

        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update SOAPService","PUT","Parent"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read SOAPService","GET","Parent"));
        oSOAPOperation.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete SOAPService","DELETE","Parent"));

        return oSOAPOperation;
    }

//TODO Extension is needed to support getList on resources that are not related resource of any other.
}