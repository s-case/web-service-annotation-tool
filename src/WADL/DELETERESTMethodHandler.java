package WADL;

import javax.ws.rs.core.UriInfo;

public class DELETERESTMethodHandler
{

    private SQLITEController oSQLITEController;
    private RESTMethodModel oRESTMethod;
    private UriInfo		 oApplicationUri;

    DELETERESTMethodHandler(int RESTMethodId, UriInfo applicationUri)
    {
        oRESTMethod = new RESTMethodModel();
        oRESTMethod.setRESTMethodId(RESTMethodId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public RESTMethodModel deleteRESTMethod()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.deleteRESTMethod( oRESTMethod));
    }
    
    public RESTMethodModel createHypermediaURIs(RESTMethodModel oRESTMethod)
    {
        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new RESTMethod","POST","Parent"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all RESTMethod of this Resource","GET","Parent"));

        return oRESTMethod;
    }
}

