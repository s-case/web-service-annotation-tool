package WADL;

public class PUTRESTMethodHandler
{

    private ResourceModel oResource;
    private RESTMethodModel oRESTMethod;
    private SQLITEController oSQLITEController;

    PUTRESTMethodHandler(int resourceId, int RESTMethodId, RESTMethodModel oRESTMethod)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        this.oRESTMethod = oRESTMethod;
        this.oRESTMethod.setRESTMethodId(RESTMethodId);
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

    public RESTMethodModel putRESTMethod()
    {
        //TODO add authentication if needed

        return oSQLITEController.putRESTMethod(oRESTMethod);
    }

}