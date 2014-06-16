package WADL;

import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

public class GETResourceListHandler
{

    private SQLITEController oSQLITEController;
    private RESTServiceModel oRESTService;
    private UriInfo		 oApplicationUri;

    GETResourceListHandler(int RESTServiceId, UriInfo applicationUri)
    {
        oRESTService = new RESTServiceModel();
        oRESTService.setRESTServiceId(RESTServiceId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public ResourceModel getResourceList()
    {
        //TODO add authentication if needed

        //get the <resourceName>List from the database
        oRESTService = oSQLITEController.getResourceList( oRESTService);
        return createHypermediaURIs(oRESTService);
    }

    public ResourceModel createHypermediaURIs(RESTServiceModel oRESTService)
    {
        ResourceModel oResource = new ResourceModel();
        Iterator<ResourceModel> setIterator = oRESTService.getSetOfResource().iterator();

        while(setIterator.hasNext())
        {
        	ResourceModel oNextResource = new ResourceModel();
        	oNextResource = setIterator.next();
        	oResource.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextResource.getResourceId()),String.format("%s",oNextResource.getResourceName()), "GET"));
        }

        return oResource;
    }

//TODO Extension is needed to support getList on resources that are not related resource of any other.
}