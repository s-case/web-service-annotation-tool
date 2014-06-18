package WADL;

import javax.ws.rs.core.UriInfo;

public class POSTResourceRESTParameterHandler
{
    private ResourceModel oResource;
    private RESTParameterModel oRESTParameter;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    POSTResourceRESTParameterHandler(int resourceId, RESTParameterModel oRESTParameter, UriInfo applicationUri)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        this.oRESTParameter = oRESTParameter;
        oRESTParameter.setResource( this.oResource);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

 
    public RESTParameterModel postRESTParameter()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.postRESTParameter( oRESTParameter));
    }
    
    public RESTParameterModel createHypermediaURIs(RESTParameterModel oRESTParameter)
    {
        //add the sibling hypermedia links POST and GET list

        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of RESTParameter","GET","Sibling"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new RESTParameter","POST","Sibling"));


        //add the child hypermedia links GET, PUT, DELETE

        oRESTParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oRESTParameter.getRESTParameterId()),"GET created RESTParameter", "GET", "Child"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oRESTParameter.getRESTParameterId()),"Update created RESTParameter", "PUT", "Child"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oRESTParameter.getRESTParameterId()),"DELETE created RESTParameter", "DELETE", "Child"));

        String oRelativePath;
        //add the child hypermedia links POST, GETL



        oRelativePath = oApplicationUri.getPath().replaceAll("multi<ResourceName>/","");

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update Resource","PUT","Parent"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read Resource","GET","Parent"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete Resource","DELETE","Parent"));
        return oRESTParameter;
    }
}