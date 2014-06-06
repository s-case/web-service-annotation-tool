package WADL;

public class PUTResourceHandler
{

    private RESTServiceModel oRESTService;
    private ResourceModel oResource;
    private SQLITEController oSQLITEController;

    PUTResourceHandler(int RESTServiceId, int resourceId, ResourceModel oResource)
    {
        oRESTService = new RESTServiceModel();
        oRESTService.setRESTServiceId(RESTServiceId);
        this.oResource = oResource;
        this.oResource.setResourceId(resourceId);
        oResource.setRESTService( this.oRESTService);
        oSQLITEController = new SQLITEController();
    }

    public void setRESTService(RESTServiceModel oRESTService)
    {
        this.oRESTService = oRESTService;
    }

    public RESTServiceModel getRESTService()
    {
        return this.oRESTService;
    }

    public ResourceModel putResource()
    {
        //TODO add authentication if needed

        return oSQLITEController.putResource(oResource);
    }

}