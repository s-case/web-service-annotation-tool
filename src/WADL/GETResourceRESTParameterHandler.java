package WADL;


public class GETResourceRESTParameterHandler
{
    private SQLITEController oSQLITEController;
    private RESTParameterModel oRESTParameter;

    GETResourceRESTParameterHandler(int RESTParameterId)
    {
        oRESTParameter = new RESTParameterModel();
        oRESTParameter.setRESTParameterId(RESTParameterId);
        oSQLITEController = new SQLITEController();
    }

    public RESTParameterModel getRESTParameter()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return oSQLITEController.getRESTParameter( oRESTParameter);
    }

}