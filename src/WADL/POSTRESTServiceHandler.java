package WADL;

import javax.ws.rs.core.UriInfo;


public class POSTRESTServiceHandler
{
    private AccountModel oAccount;
    private RESTServiceModel oRESTService;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    POSTRESTServiceHandler(Integer accountId, RESTServiceModel oRESTService, UriInfo applicationUri)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        this.oRESTService = oRESTService;
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

    public RESTServiceModel postRESTService()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.postRESTService( oRESTService));
    }
    
    public RESTServiceModel createHypermediaURIs(RESTServiceModel oRESTService)
    {
        //add the sibling hypermedia links POST and GET list

        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of RESTService","GET","Sibling"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new RESTService","POST","Sibling"));       

        //add the child hypermedia links GET, PUT, DELETE

        oRESTService.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oRESTService.getRESTServiceId()),"GET created RESTService", "GET", "Child"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oRESTService.getRESTServiceId()),"Update created RESTService", "PUT", "Child"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oRESTService.getRESTServiceId()),"DELETE created RESTService", "DELETE", "Child"));

        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();
        
        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update Account","PUT","Parent"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read Account","GET","Parent"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete Account","DELETE","Parent"));
        oRESTService.getLinkList().add(new Link(String.format("%s/%s",String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"/algoRESTService/WADLParse"),"Create a new RESTService for this Account by parsing a WADL file", "POST", "Child"));
        
        return oRESTService;
    }
}