package WADL;

import javax.ws.rs.core.UriInfo;


public class GETResourceRESTParameterHandler
{
    private SQLITEController oSQLITEController;
    private RESTParameterModel oRESTParameter;
    private UriInfo		 oApplicationUri;

    GETResourceRESTParameterHandler(int RESTParameterId, UriInfo applicationUri)
    {
        oRESTParameter = new RESTParameterModel();
        oRESTParameter.setRESTParameterId(RESTParameterId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public RESTParameterModel getRESTParameter()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.getRESTParameter( oRESTParameter));
    }
    
    public RESTParameterModel createHypermediaURIs(RESTParameterModel oRESTParameter)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update  RESTParameter","PUT","Sibling"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the again RESTParameter","GET","Sibling"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the RESTParameter","DELETE","Sibling"));


        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new RESTParameter","POST","Parent"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all RESTParameter of this Resource","GET","Parent"));

        return oRESTParameter;
    }

}