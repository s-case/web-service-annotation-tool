package WADL;

public class POSTRESTMethodHandler
{
    private ResourceModel oResource;
    private RESTMethodModel oRESTMethod;
    private SQLITEController oSQLITEController;

    POSTRESTMethodHandler(int resourceId, RESTMethodModel oRESTMethod)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        this.oRESTMethod = oRESTMethod;
        oRESTMethod.setResource( this.oResource);
        oSQLITEController = new SQLITEController();
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

        return oSQLITEController.postRESTMethod(oRESTMethod);
    }
}