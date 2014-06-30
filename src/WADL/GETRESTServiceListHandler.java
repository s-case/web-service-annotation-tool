package WADL;

import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

public class GETRESTServiceListHandler
{

    private SQLITEController oSQLITEController;
    private AccountModel oAccount;
    private UriInfo		 oApplicationUri;

    GETRESTServiceListHandler(int accountId, UriInfo applicationUri)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public RESTServiceModel getRESTServiceList()
    {
        //TODO add authentication if needed

        //get the <resourceName>List from the database
        oAccount = oSQLITEController.getRESTServiceList( oAccount);
        return createHypermediaURIs(oAccount);
    }

    public RESTServiceModel createHypermediaURIs(AccountModel oAccount)
    {
        //create an empty <resourceModel> and populate its linklists with hypermedia links
        RESTServiceModel oRESTService = new RESTServiceModel();

        //add the sibling hypermedia links POST and GET list

        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of RESTService","GET","Sibling"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new RESTService","POST","Sibling"));


        //add the children hypermedia links GET
        Iterator<RESTServiceModel> setIterator = oAccount.getSetOfRESTService().iterator();

        while(setIterator.hasNext())
        {
            RESTServiceModel oNextRESTService = new RESTServiceModel();
            oNextRESTService = setIterator.next();
            oRESTService.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextRESTService.getRESTServiceId()),String.format("%s",oNextRESTService.getBaseUri()), "GET", "Child"));
        }

        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update Account","PUT","Parent"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read Account","GET","Parent"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete Account","DELETE","Parent"));
        return oRESTService;
    }
}