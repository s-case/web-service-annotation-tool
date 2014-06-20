package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.AccountModel;
import WADL.Link;
import WADL.SQLITEController;

public class PUTSOAPServiceHandler
{

    private AccountModel oAccount;
    private SOAPServiceModel oSOAPService;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    PUTSOAPServiceHandler(int accountId, int SOAPServiceId, SOAPServiceModel oSOAPService, UriInfo applicationUri)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        this.oSOAPService = oSOAPService;
        this.oSOAPService.setSOAPServiceId(SOAPServiceId);
        oSOAPService.setAccount( this.oAccount);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public void setAccount(AccountModel oAccount)
    {
        this.oAccount = oAccount;
    }

    public AccountModel getAccount()
    {
        return this.oAccount;
    }

    public SOAPServiceModel putSOAPService()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.putSOAPService( oSOAPService));
    }

    public SOAPServiceModel createHypermediaURIs(SOAPServiceModel oSOAPService)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update again SOAPService","PUT","Sibling"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the updated SOAPService","GET","Sibling"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the updated SOAPService","DELETE","Sibling"));


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