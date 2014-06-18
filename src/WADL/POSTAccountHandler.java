package WADL;

import javax.ws.rs.core.UriInfo;

public class POSTAccountHandler
{
    private AccountModel oAccount;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;
    
    POSTAccountHandler( AccountModel oAccount, UriInfo applicationUri)
    {
        this.oAccount = oAccount;
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public AccountModel postAccount()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.postAccount( oAccount));
    }
    
    public AccountModel createHypermediaURIs(AccountModel oAccount)
    {
    	//add the sibling hypermedia links POST and GET list

        oAccount.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new Account","POST","Sibling"));


        //add the child hypermedia links GET, PUT, DELETE

        oAccount.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oAccount.getAccountId()),"GET created Account", "GET", "Child"));
        oAccount.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oAccount.getAccountId()),"Update created Account", "PUT", "Child"));
        oAccount.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oAccount.getAccountId()),"DELETE created Account", "DELETE", "Child"));

        return oAccount;
    }
}
