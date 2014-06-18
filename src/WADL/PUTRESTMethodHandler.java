package WADL;

import javax.ws.rs.core.UriInfo;

public class PUTRESTMethodHandler
{

    private ResourceModel oResource;
    private RESTMethodModel oRESTMethod;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;
    
    PUTRESTMethodHandler(int resourceId, int RESTMethodId, RESTMethodModel oRESTMethod, UriInfo applicationUri)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        this.oRESTMethod = oRESTMethod;
        this.oRESTMethod.setRESTMethodId(RESTMethodId);
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

    public RESTMethodModel putRESTMethod()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.putRESTMethod( oRESTMethod));
    }

    public RESTMethodModel createHypermediaURIs(RESTMethodModel oRESTMethod)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update again RESTMethod","PUT","Sibling"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the updated RESTMethod","GET","Sibling"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the updated RESTMethod","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = String.format("%s/%s","multiRESTParameter",oApplicationUri.getPath());

        oRESTMethod.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"RESTParameter"),"Create a new RESTParameter for this RESTMethod", "POST", "Child"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"RESTParameter"),"GET all the RESTParameter of this RESTMethod", "GET", "Child"));

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new RESTMethod","POST","Parent"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all RESTMethod of this resource","GET","Parent"));

        return oRESTMethod;
    }
}