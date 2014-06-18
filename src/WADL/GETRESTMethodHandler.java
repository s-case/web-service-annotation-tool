package WADL;

import javax.ws.rs.core.UriInfo;


public class GETRESTMethodHandler
{
    private SQLITEController oSQLITEController;
    private RESTMethodModel oRESTMethod;
    private UriInfo		 oApplicationUri;

    GETRESTMethodHandler(int RESTMethodId, UriInfo applicationUri)
    {
        oRESTMethod = new RESTMethodModel();
        oRESTMethod.setRESTMethodId(RESTMethodId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public RESTMethodModel getRESTMethod()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.getRESTMethod( oRESTMethod));
    }

    public RESTMethodModel createHypermediaURIs(RESTMethodModel oRESTMethod)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update  RESTMethod","PUT","Sibling"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the again RESTMethod","GET","Sibling"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the RESTMethod","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = String.format("%s/%s","multiRESTParameter",oApplicationUri.getPath());

        oRESTMethod.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"RESTParameter"),"Create a new RESTParameter for this RESTMethod", "POST", "Child"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"RESTParameter"),"GET all the RESTParameter of this RESTMethod", "GET", "Child"));

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new RESTMethod","POST","Parent"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read allRESTMethod of this Resource","GET","Parent"));

        return oRESTMethod;
    }
}