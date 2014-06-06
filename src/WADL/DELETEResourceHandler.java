package WADL;

public class DELETEResourceHandler
{

    private SQLITEController oSQLITEController;
    private ResourceModel oResource;

    DELETEResourceHandler(int resourceId)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        oSQLITEController = new SQLITEController();
    }

    public void deleteResource()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        oSQLITEController.deleteResource( oResource);
    }
}

