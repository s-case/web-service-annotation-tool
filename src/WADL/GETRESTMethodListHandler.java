package WADL;

import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

public class GETRESTMethodListHandler
{

    private SQLITEController oSQLITEController;
    private ResourceModel oResource;
    private UriInfo		 oApplicationUri;
    
    GETRESTMethodListHandler(int resourceId, UriInfo applicationUri)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public RESTMethodModel getRESTMethodList()
    {
        //TODO add authentication if needed

        //get the <resourceName>List from the database
        oResource = oSQLITEController.getRESTMethodList( oResource);
        return createHypermediaURIs(oResource);
    }

    public RESTMethodModel createHypermediaURIs(ResourceModel oResource)
    {
    	RESTMethodModel oRESTMethod = new RESTMethodModel();
        Iterator<RESTMethodModel> setIterator = oResource.getSetOfRESTMethod().iterator();

        while(setIterator.hasNext())
        {
            RESTMethodModel oNextRESTMethod = new RESTMethodModel();
            oNextRESTMethod = setIterator.next();
            oRESTMethod.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextRESTMethod.getRESTMethodId()),String.format("%s",oNextRESTMethod.getMethodIdentifier()), "GET"));
        }

        return oRESTMethod;
    }
}