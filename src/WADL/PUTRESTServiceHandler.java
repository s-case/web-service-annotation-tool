package WADL;

import javax.ws.rs.core.UriInfo;


public class PUTRESTServiceHandler
{

    private AccountModel oAccount;
    private RESTServiceModel oRESTService;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;
    
    PUTRESTServiceHandler(Integer accountId, Integer RESTServiceId, RESTServiceModel oRESTService, UriInfo applicationUri)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        this.oRESTService = oRESTService;
        this.oRESTService.setRESTServiceId(RESTServiceId);
        oRESTService.setAccount( this.oAccount);
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

    public RESTServiceModel putRESTService()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.putRESTService( oRESTService));
    }

    public RESTServiceModel createHypermediaURIs(RESTServiceModel oRESTService)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update again RESTService","PUT","Sibling"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the updated RESTService","GET","Sibling"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the updated RESTService","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        oRESTService.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"resource"),"Create a new resource for this RESTService", "POST", "Child"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"resource"),"GET all the resource of this RESTService", "GET", "Child"));


        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new RESTService","POST","Parent"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all RESTService of this Account","GET","Parent"));

        return oRESTService;
    }
}
