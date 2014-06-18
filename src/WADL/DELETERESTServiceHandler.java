package WADL;

import javax.ws.rs.core.UriInfo;

public class DELETERESTServiceHandler
{

    private SQLITEController oSQLITEController;
    private RESTServiceModel oRESTService;
    private UriInfo		 oApplicationUri;

    DELETERESTServiceHandler(Integer RESTServiceId, UriInfo applicationUri)
    {
        oRESTService = new RESTServiceModel();
        oRESTService.setRESTServiceId(RESTServiceId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public RESTServiceModel deleteRESTService()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.deleteRESTService( oRESTService));
    }
    
    public RESTServiceModel createHypermediaURIs(RESTServiceModel oRESTService)
    {
        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new RESTService","POST","Parent"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all RESTService of this Account","GET","Parent"));

        return oRESTService;
    }
}