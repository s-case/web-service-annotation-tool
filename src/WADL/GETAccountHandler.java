package WADL;

import javax.ws.rs.core.UriInfo;

public class GETAccountHandler
{
    private SQLITEController oSQLITEController;
    private AccountModel oAccount;
    private UriInfo		 oApplicationUri;

    GETAccountHandler(Integer accountId, UriInfo applicationUri)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public AccountModel getAccount()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.getAccount( oAccount));
    }
    
    public AccountModel createHypermediaURIs(AccountModel oAccount)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oAccount.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update  Account","PUT","Sibling"));
        oAccount.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET again the Account","GET","Sibling"));
        oAccount.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the Account","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        oAccount.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"RESTService"),"Create a new RESTService for this Account", "POST", "Child"));
        oAccount.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"RESTService"),"GET all the RESTService of this Account", "GET", "Child"));

        oRelativePath = oApplicationUri.getPath();

        oAccount.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"SOAPService"),"Create a new SOAPService for this Account", "POST", "Child"));
        oAccount.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"SOAPService"),"GET all the SOAPService of this Account", "GET", "Child"));

        oRelativePath = oApplicationUri.getPath();

        oAccount.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"algoRESTService/WADLParse"),"Create a new RESTService for this Account by parsing a WADL file", "POST", "Child"));

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oAccount.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new Account","POST","Parent"));

        return oAccount;
    }

}
