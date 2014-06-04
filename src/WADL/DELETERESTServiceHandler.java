package WADL;

public class DELETERESTServiceHandler
{

    private SQLITEController oSQLITEController;
    private RESTServiceModel oRESTService;

    DELETERESTServiceHandler(Integer RESTServiceId)
    {
        oRESTService = new RESTServiceModel();
        oRESTService.setRESTServiceId(RESTServiceId);
        oSQLITEController = new SQLITEController();
    }

    public void deleteRESTService()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        oSQLITEController.deleteRESTService( oRESTService);
    }
}