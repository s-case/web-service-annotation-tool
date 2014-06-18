package WADL;

import javax.ws.rs.core.UriInfo;

public class POSTRESTMethodHandler
{
    private ResourceModel oResource;
    private RESTMethodModel oRESTMethod;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    POSTRESTMethodHandler(int resourceId, RESTMethodModel oRESTMethod, UriInfo applicationUri)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        this.oRESTMethod = oRESTMethod;
        oRESTMethod.setResource( this.oResource);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri; 
    }

    public void setResource(ResourceModel oResource)
    {
        this.oResource = oResource;
    }

    public ResourceModel getResource()
    {
        return this.oResource;
    }

    public RESTMethodModel postRESTMethod()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.postRESTMethod( oRESTMethod));
    }
    
    public RESTMethodModel createHypermediaURIs(RESTMethodModel oRESTMethod)
    {
        //add the sibling hypermedia links POST and GET list

        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of RESTMethod","GET","Sibling"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new RESTMethod","POST","Sibling"));


        //add the child hypermedia links GET, PUT, DELETE

        oRESTMethod.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oRESTMethod.getRESTMethodId()),"GET created RESTMethod", "GET", "Child"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oRESTMethod.getRESTMethodId()),"Update created RESTMethod", "PUT", "Child"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oRESTMethod.getRESTMethodId()),"DELETE created RESTMethod", "DELETE", "Child"));

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Update Resource","PUT","Parent"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read Resource","GET","Parent"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Delete Resource","DELETE","Parent"));

        return oRESTMethod;
    }
}