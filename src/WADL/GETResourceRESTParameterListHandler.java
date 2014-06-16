package WADL;

import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

public class GETResourceRESTParameterListHandler
{

    private SQLITEController oSQLITEController;
    private ResourceModel oResource;
    private UriInfo		 oApplicationUri;
    
    GETResourceRESTParameterListHandler(int resourceId, UriInfo applicationUri)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public RESTParameterModel getRESTParameterList()
    {
        //TODO add authentication if needed

        //get the <resourceName>List from the database
        oResource = oSQLITEController.getResourceRESTParameterList( oResource);
        return createHypermediaURIs(oResource);
    }

    public RESTParameterModel createHypermediaURIs(ResourceModel oResourceModel)
    {
        RESTParameterModel oRESTParameter = new RESTParameterModel();
        Iterator<RESTParameterModel> setIterator = oResource.getSetOfRESTParameter().iterator();

        while(setIterator.hasNext())
        {
            RESTParameterModel oNextRESTParameter = new RESTParameterModel();
            oNextRESTParameter = setIterator.next();
            oRESTParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextRESTParameter.getRESTParameterId()),String.format("%s",oNextRESTParameter.getParameterName()), "GET"));
        }

        return oRESTParameter;
    }
    
}