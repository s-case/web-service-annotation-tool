package WADL;

public class PUTRESTMethodRESTParameterHandler
{

    private RESTMethodModel oRESTMethod;
    private RESTParameterModel oRESTParameter;
    private SQLITEController oSQLITEController;

    PUTRESTMethodRESTParameterHandler(int RESTMethodId, int RESTParameterId, RESTParameterModel oRESTParameter)
    {
        oRESTMethod = new RESTMethodModel();
        oRESTMethod.setRESTMethodId(RESTMethodId);
        this.oRESTParameter = oRESTParameter;
        this.oRESTParameter.setRESTParameterId(RESTParameterId);
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

    public RESTParameterModel putRESTParameter()
    {
        //TODO add authentication if needed

        return oSQLITEController.putRESTParameter(oRESTParameter);
    }

}