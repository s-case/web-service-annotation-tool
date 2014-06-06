package WADL;

public class POSTRESTMethodRESTParameterHandler
{
    private RESTMethodModel oRESTMethod;
    private RESTParameterModel oRESTParameter;
    private SQLITEController oSQLITEController;

    POSTRESTMethodRESTParameterHandler(int RESTMethodId, RESTParameterModel oRESTParameter)
    {
        oRESTMethod = new RESTMethodModel();
        oRESTMethod.setRESTMethodId(RESTMethodId);
        this.oRESTParameter = oRESTParameter;
        oRESTParameter.setRESTMethod( this.oRESTMethod);
        oSQLITEController = new SQLITEController();
    }

    public void setRESTMethod(RESTMethodModel oRESTMethod)
    {
        this.oRESTMethod = oRESTMethod;
    }

    public RESTMethodModel getRESTMethod()
    {
        return this.oRESTMethod;
    }

    public RESTParameterModel postRESTParameter()
    {
        //TODO add authentication if needed

        return oSQLITEController.postRESTParameter(oRESTParameter);
    }
}