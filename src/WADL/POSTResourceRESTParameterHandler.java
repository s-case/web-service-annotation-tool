package WADL;

public class POSTResourceRESTParameterHandler
{
    private ResourceModel oResource;
    private RESTParameterModel oRESTParameter;
    private SQLITEController oSQLITEController;

    POSTResourceRESTParameterHandler(int resourceId, RESTParameterModel oRESTParameter)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        this.oRESTParameter = oRESTParameter;
        oRESTParameter.setResource( this.oResource);
        oSQLITEController = new SQLITEController();
    }

 
    public RESTParameterModel postRESTParameter()
    {
        //TODO add authentication if needed

        return oSQLITEController.postRESTParameter(oRESTParameter);
    }
}