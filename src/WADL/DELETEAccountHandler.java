package WADL;

import javax.ws.rs.core.UriInfo;


public class DELETEAccountHandler
{

    private SQLITEController oSQLITEController;
    private AccountModel oAccount;
    private UriInfo		 oApplicationUri;

    DELETEAccountHandler(Integer accountId, UriInfo applicationUri)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public AccountModel deleteAccount()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.deleteAccount( oAccount));
    }
    
    public AccountModel createHypermediaURIs(AccountModel oAccount)
    {
        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oAccount.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new Account","POST","Parent"));

        return oAccount;
    }
}