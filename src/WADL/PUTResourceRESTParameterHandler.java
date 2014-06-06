package WADL;

public class PUTResourceRESTParameterHandler
{

    private ResourceModel oResource;
    private RESTParameterModel oRESTParameter;
    private SQLITEController oSQLITEController;

    PUTResourceRESTParameterHandler(int resourceId, int RESTParameterId, RESTParameterModel oRESTParameter)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        this.oRESTParameter = oRESTParameter;
        this.oRESTParameter.setRESTParameterId(RESTParameterId);
        oRESTParameter.setResource( this.oResource);
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

    public RESTParameterModel putRESTParameter()
    {
        //TODO add authentication if needed

        return oSQLITEController.putRESTParameter(oRESTParameter);
    }

}