package WADL;

import javax.ws.rs.core.UriInfo;

public class POSTResourceHandler
{
    private RESTServiceModel oRESTService;
    private ResourceModel oResource;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    POSTResourceHandler(int RESTServiceId, ResourceModel oResource, UriInfo applicationUri)
    {
        oRESTService = new RESTServiceModel();
        oRESTService.setRESTServiceId(RESTServiceId);
        this.oResource = oResource;
        oResource.setRESTService( this.oRESTService);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public void setRESTService(RESTServiceModel oRESTService)
    {
        this.oRESTService = oRESTService;
    }

    public RESTServiceModel getRESTService()
    {
        return this.oRESTService;
    }

    public ResourceModel postResource()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.postResource( oResource));
    }
    
    public ResourceModel createHypermediaURIs(ResourceModel oResource)
    {
        //add the sibling hypermedia links POST and GET list

        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of Resource","GET","Sibling"));
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new Resource","POST","Sibling"));


        //add the child hypermedia links GET, PUT, DELETE

        oResource.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oResource.getResourceId()),"GET created Resource", "GET", "Child"));
        oResource.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oResource.getResourceId()),"Update created Resource", "PUT", "Child"));
        oResource.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oResource.getResourceId()),"DELETE created Resource", "DELETE", "Child"));

        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update RESTService","PUT","Parent"));
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read RESTService","GET","Parent"));
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete RESTService","DELETE","Parent"));
        return oResource;
    }
}