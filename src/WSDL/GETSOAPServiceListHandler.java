package WSDL;

import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

import WADL.AccountModel;
import WADL.Link;
import WADL.SQLITEController;

public class GETSOAPServiceListHandler
{

    private SQLITEController oSQLITEController;
    private AccountModel oAccount;
    private UriInfo		 oApplicationUri;

    GETSOAPServiceListHandler(int accountId, UriInfo applicationUri)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public SOAPServiceModel getSOAPServiceList()
    {
        //TODO add authentication if needed

        //get the <resourceName>List from the database
        oAccount = oSQLITEController.getSOAPServiceList( oAccount);
        return createHypermediaURIs(oAccount);
    }


    public SOAPServiceModel createHypermediaURIs(AccountModel oAccount)
    {
        //create an empty <resourceModel> and populate its linklists with hypermedia links
    	SOAPServiceModel oSOAPService = new SOAPServiceModel();

        //add the sibling hypermedia links POST and GET list

        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of SOAPService","GET","Sibling"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new SOAPService","POST","Sibling"));


        //add the children hypermedia links GET
        Iterator<SOAPServiceModel> setIterator = oAccount.getSetOfSOAPService().iterator();

        while(setIterator.hasNext())
        {
        	SOAPServiceModel oNextSOAPService = new SOAPServiceModel();
            oNextSOAPService = setIterator.next();
            oSOAPService.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextSOAPService.getSOAPServiceId()),String.format("%s",oNextSOAPService.getName()), "GET", "Child"));
        }

        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update Account","PUT","Parent"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read Account","GET","Parent"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete Account","DELETE","Parent"));

        return oSOAPService;
    }

//TODO Extension is needed to support getList on resources that are not related resource of any other.
}