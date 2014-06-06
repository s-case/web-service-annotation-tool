package WADL;

public class POSTResourceHandler
{
    private RESTServiceModel oRESTService;
    private ResourceModel oResource;
    private SQLITEController oSQLITEController;

    POSTResourceHandler(int RESTServiceId, ResourceModel oResource)
    {
        oRESTService = new RESTServiceModel();
        oRESTService.setRESTServiceId(RESTServiceId);
        this.oResource = oResource;
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

    public ResourceModel postResource()
    {
        //TODO add authentication if needed

        return oSQLITEController.postResource(oResource);
    }
}