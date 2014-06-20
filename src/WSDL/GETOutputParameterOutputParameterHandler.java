package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class GETOutputParameterOutputParameterHandler
{
    private SQLITEController oSQLITEController;
    private OutputParameterModel oOutputParameter;
    private UriInfo		 oApplicationUri;

    GETOutputParameterOutputParameterHandler(int outputParameterId, UriInfo applicationUri)
    {
        oOutputParameter = new OutputParameterModel();
        oOutputParameter.setOutputParameterId(outputParameterId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public OutputParameterModel getOutputParameter()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.getOutputParameter( oOutputParameter));
    }

    public OutputParameterModel createHypermediaURIs(OutputParameterModel oOutputParameter)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update  OutputParameter","PUT","Sibling"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET again the OutputParameter","GET","Sibling"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the OutputParameter","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath().replaceAll("multiOutputParameter/","multiOutputParameter/");

        oRelativePath = oRelativePath.replaceAll(String.format("OutputParameter/(.*)/OutputParameter/%d",oOutputParameter.getOutputParameterId()),String.format("OutputParameter/%d",oOutputParameter.getOutputParameterId()));

        oOutputParameter.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"OutputParameter"),"Create a new OutputParameter for this OutputParameter", "POST", "Child"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"OutputParameter"),"GET all the OutputParameter of this OutputParameter", "GET", "Child"));

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new OutputParameter","POST","Parent"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all OutputParameter of this OutputParameter","GET","Parent"));

        return oOutputParameter;
    }

}