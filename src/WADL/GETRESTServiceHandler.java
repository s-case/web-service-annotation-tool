package WADL;

public class GETRESTServiceHandler
{
    private SQLITEController oSQLITEController;
    private RESTServiceModel oRESTService;

    GETRESTServiceHandler(Integer RESTServiceId)
    {
        oRESTService = new RESTServiceModel();
        oRESTService.setRESTServiceId(RESTServiceId);
        oSQLITEController = new SQLITEController();
    }

    public RESTServiceModel getRESTService()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return oSQLITEController.getRESTService( oRESTService);
    }

}