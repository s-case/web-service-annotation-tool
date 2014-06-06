package WADL;

public class DELETERESTMethodHandler
{

    private SQLITEController oSQLITEController;
    private RESTMethodModel oRESTMethod;

    DELETERESTMethodHandler(int RESTMethodId)
    {
        oRESTMethod = new RESTMethodModel();
        oRESTMethod.setRESTMethodId(RESTMethodId);
        oSQLITEController = new SQLITEController();
    }

    public void deleteRESTMethod()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        oSQLITEController.deleteRESTMethod( oRESTMethod);
    }
}

