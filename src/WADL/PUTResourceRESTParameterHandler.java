package WADL;

import javax.ws.rs.core.UriInfo;

public class PUTResourceRESTParameterHandler
{

    private ResourceModel oResource;
    private RESTParameterModel oRESTParameter;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;
    
    PUTResourceRESTParameterHandler(int resourceId, int RESTParameterId, RESTParameterModel oRESTParameter, UriInfo applicationUri)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        this.oRESTParameter = oRESTParameter;
        this.oRESTParameter.setRESTParameterId(RESTParameterId);
        oRESTParameter.setResource( this.oResource);
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

    public RESTParameterModel putRESTParameter()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.putRESTParameter( oRESTParameter));
    }

    public RESTParameterModel createHypermediaURIs(RESTParameterModel oRESTParameter)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update again RESTParameter","PUT","Sibling"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the updated RESTParameter","GET","Sibling"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the updated RESTParameter","DELETE","Sibling"));

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new RESTParameter","POST","Parent"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all RESTParameter of this Resource","GET","Parent"));

        return oRESTParameter;
    }
}