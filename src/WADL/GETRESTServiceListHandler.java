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
        RESTServiceModel oRESTService = new RESTServiceModel();
        Iterator<RESTServiceModel> setIterator = oAccount.getSetOfRESTService().iterator();

        while(setIterator.hasNext())
        {
        	RESTServiceModel oNextRESTService = new RESTServiceModel();
            oNextRESTService = setIterator.next();
            oRESTService.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextRESTService.getRESTServiceId()),String.format("%s",oNextRESTService.getWsName()), "GET"));
        }

        return oRESTService;
    }
}