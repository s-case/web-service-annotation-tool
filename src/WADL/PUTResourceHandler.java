package WADL;

import javax.ws.rs.core.UriInfo;

public class PUTResourceHandler
{

    private RESTServiceModel oRESTService;
    private ResourceModel oResource;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;
    
    PUTResourceHandler(int RESTServiceId, int resourceId, ResourceModel oResource, UriInfo applicationUri)
    {
        oRESTService = new RESTServiceModel();
        oRESTService.setRESTServiceId(RESTServiceId);
        this.oResource = oResource;
        this.oResource.setResourceId(resourceId);
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

    public ResourceModel putResource()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.putResource( oResource));
    }

    public ResourceModel createHypermediaURIs(ResourceModel oResource)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update again Resource","PUT","Sibling"));
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the updated Resource","GET","Sibling"));
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the updated Resource","DELETE","Sibling"));

        String oRelativePath;
        //add the child hypermedia links POST, GETL
        oRelativePath = oApplicationUri.getPath();

        oResource.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"RESTMethod"),"Create a new RESTMethod for this Resource", "POST", "Child"));
        oResource.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"RESTMethod"),"GET all the RESTMethod of this Resource", "GET", "Child"));

        //add the child hypermedia links POST, GETL

        oRelativePath = String.format("%s/%s","multiRESTParameter",oApplicationUri.getPath());


        oResource.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"RESTParameter"),"Create a new RESTParameter for this Resource", "POST", "Child"));
        oResource.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"RESTParameter"),"GET all the RESTParameter of this Resource", "GET", "Child"));
        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new Resource","POST","Parent"));
        oResource.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all Resource of this RESTService","GET","Parent"));

        return oResource;
    }
}