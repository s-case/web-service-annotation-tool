package WADL;

public class DELETEResourceRESTParameterHandler
{

    private SQLITEController oSQLITEController;
    private RESTParameterModel oRESTParameter;

    DELETEResourceRESTParameterHandler(int RESTParameterId)
    {
        oRESTParameter = new RESTParameterModel();
        oRESTParameter.setRESTParameterId(RESTParameterId);
        oSQLITEController = new SQLITEController();
    }

    public void deleteRESTParameter()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        oSQLITEController.deleteRESTParameter( oRESTParameter);
    }
}

