package WADL;

public class GETRESTMethodRESTParameterHandler
{
    private SQLITEController oSQLITEController;
    private RESTParameterModel oRESTParameter;

    GETRESTMethodRESTParameterHandler(int RESTParameterId)
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