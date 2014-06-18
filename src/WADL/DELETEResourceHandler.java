package WADL;

import javax.ws.rs.core.UriInfo;

public class DELETEResourceHandler
{

    private SQLITEController oSQLITEController;
    private ResourceModel oResource;
    private UriInfo		 oApplicationUri;

    DELETEResourceHandler(int resourceId, UriInfo applicationUri)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public ResourceModel deleteResource()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.deleteResource( oResource));
    }
    
    public ResourceModel createHypermediaURIs(ResourceModel oResource)
    {
        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new Resource","POST","Parent"));
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all Resource of this RESTService","GET","Parent"));

        return oResource;
    }
}

