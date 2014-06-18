package WADL;

import javax.ws.rs.core.UriInfo;

public class PUTRESTMethodRESTParameterHandler
{

    private RESTMethodModel oRESTMethod;
    private RESTParameterModel oRESTParameter;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;
    
    PUTRESTMethodRESTParameterHandler(int RESTMethodId, int RESTParameterId, RESTParameterModel oRESTParameter, UriInfo applicationUri)
    {
        oRESTMethod = new RESTMethodModel();
        oRESTMethod.setRESTMethodId(RESTMethodId);
        this.oRESTParameter = oRESTParameter;
        this.oRESTParameter.setRESTParameterId(RESTParameterId);
        oRESTParameter.setRESTMethod( this.oRESTMethod);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public void setRESTMethod(RESTMethodModel oRESTMethod)
    {
        this.oRESTMethod = oRESTMethod;
    }

    public RESTMethodModel getRESTMethod()
    {
        return this.oRESTMethod;
    }

    public RESTParameterModel putRESTParameter()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.putRESTParameter( oRESTParameter));
    }

    public RESTParameterModel createHypermediaURIs(RESTParameterModel oRESTParameter)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update again RESTParameter","PUT","Sibling"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the updated RESTParameter","GET","Sibling"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the updated RESTParameter","DELETE","Sibling"));

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new RESTParameter","POST","Parent"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all RESTParameter of this RESTMethod","GET","Parent"));

        return oRESTParameter;
    }
}