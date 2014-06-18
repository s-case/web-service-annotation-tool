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
        //create an empty <resourceModel> and populate its linklists with hypermedia links
        ResourceModel oResource = new ResourceModel();

        //add the sibling hypermedia links POST and GET list

        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of resource","GET","Sibling"));
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new resource","POST","Sibling"));


        //add the children hypermedia links GET
        Iterator<ResourceModel> setIterator = oRESTService.getSetOfResource().iterator();

        while(setIterator.hasNext())
        {
            ResourceModel oNextResource = new ResourceModel();
            oNextResource = setIterator.next();
            oResource.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextResource.getResourceId()),String.format("%s",oNextResource.getResourceName()), "GET", "Child"));
        }

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Update RESTService","PUT","Parent"));
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read RESTService","GET","Parent"));
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Delete RESTService","DELETE","Parent"));

        return oResource;
    }

//TODO Extension is needed to support getList on resources that are not related resource of any other.
}