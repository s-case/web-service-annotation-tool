package WADL;


public class GETResourceHandler
{
    private SQLITEController oSQLITEController;
    private ResourceModel oResource;

    GETResourceHandler(int resourceId)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        oSQLITEController = new SQLITEController();
    }

    public ResourceModel getResource()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return oSQLITEController.getResource( oResource);
    }

}