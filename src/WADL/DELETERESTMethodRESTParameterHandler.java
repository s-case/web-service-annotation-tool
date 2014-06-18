package WADL;

import javax.ws.rs.core.UriInfo;

public class DELETERESTMethodRESTParameterHandler
{

    private SQLITEController oSQLITEController;
    private RESTParameterModel oRESTParameter;
    private UriInfo		 oApplicationUri;

    DELETERESTMethodRESTParameterHandler(int RESTParameterId, UriInfo applicationUri)
    {
        oRESTParameter = new RESTParameterModel();
        oRESTParameter.setRESTParameterId(RESTParameterId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public RESTParameterModel deleteRESTParameter()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.deleteRESTParameter( oRESTParameter));
    }
    
    public RESTParameterModel createHypermediaURIs(RESTParameterModel oRESTParameter)
    {
        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new RESTParameter","POST","Parent"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all RESTParameter of this RESTMethod","GET","Parent"));

        return oRESTParameter;
    }
}

