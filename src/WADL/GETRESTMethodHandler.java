package WADL;


public class GETRESTMethodHandler
{
    private SQLITEController oSQLITEController;
    private RESTMethodModel oRESTMethod;

    GETRESTMethodHandler(int RESTMethodId)
    {
        oRESTMethod = new RESTMethodModel();
        oRESTMethod.setRESTMethodId(RESTMethodId);
        oSQLITEController = new SQLITEController();
    }

    public RESTMethodModel getRESTMethod()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return oSQLITEController.getRESTMethod( oRESTMethod);
    }

}