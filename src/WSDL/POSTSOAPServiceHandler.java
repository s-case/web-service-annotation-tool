package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.AccountModel;
import WADL.Link;
import WADL.SQLITEController;

public class POSTSOAPServiceHandler
{
    private AccountModel oAccount;
    private SOAPServiceModel oSOAPService;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    POSTSOAPServiceHandler(int accountId, SOAPServiceModel oSOAPService, UriInfo applicationUri)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        this.oSOAPService = oSOAPService;
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

    public SOAPServiceModel postSOAPService()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.postSOAPService( oSOAPService));
    }

    public SOAPServiceModel createHypermediaURIs(SOAPServiceModel oSOAPService)
    {
        //add the sibling hypermedia links POST and GET list

        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of SOAPService","GET","Sibling"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new SOAPService","POST","Sibling"));


        //add the child hypermedia links GET, PUT, DELETE

        oSOAPService.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oSOAPService.getSOAPServiceId()),"GET created SOAPService", "GET", "Child"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oSOAPService.getSOAPServiceId()),"Update created SOAPService", "PUT", "Child"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oSOAPService.getSOAPServiceId()),"DELETE created SOAPService", "DELETE", "Child"));

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
}

