package WADL;

import javax.ws.rs.core.UriInfo;

public class GETRESTServiceHandler
{
    private SQLITEController oSQLITEController;
    private RESTServiceModel oRESTService;
    private UriInfo		 oApplicationUri;

    GETRESTServiceHandler(Integer RESTServiceId, UriInfo applicationUri)
    {
        oRESTService = new RESTServiceModel();
        oRESTService.setRESTServiceId(RESTServiceId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public RESTServiceModel getRESTService()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.getRESTService( oRESTService));
    }
    
    public RESTServiceModel createHypermediaURIs(RESTServiceModel oRESTService)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update  RESTService","PUT","Sibling"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the again RESTService","GET","Sibling"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the RESTService","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        oRESTService.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"resource"),"Create a new resource for this RESTService", "POST", "Child"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"resource"),"GET all the resource of this RESTService", "GET", "Child"));


        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new RESTService","POST","Parent"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all RESTService of this Account","GET","Parent"));

        return oRESTService;
    }

}