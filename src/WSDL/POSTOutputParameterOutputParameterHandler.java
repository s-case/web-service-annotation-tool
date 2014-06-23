package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class POSTOutputParameterOutputParameterHandler
{
    private OutputParameterModel oSourceOutputParameter;
    private OutputParameterModel oOutputParameter;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    POSTOutputParameterOutputParameterHandler(int sourceOutputParameterId, OutputParameterModel oOutputParameter, UriInfo applicationUri)
    {
        oSourceOutputParameter = new OutputParameterModel();
        oSourceOutputParameter.setOutputParameterId(sourceOutputParameterId);
        this.oOutputParameter = oOutputParameter;
        oOutputParameter.setOutputParameter( this.oSourceOutputParameter);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public void setOutputParameter(OutputParameterModel oSourceOutputParameter)
    {
        this.oSourceOutputParameter = oSourceOutputParameter;
    }

    public OutputParameterModel getOutputParameter()
    {
        return this.oSourceOutputParameter;
    }

    public OutputParameterModel postOutputParameter()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.postOutputParameter( oOutputParameter));
    }

    public OutputParameterModel createHypermediaURIs(OutputParameterModel oOutputParameter)
    {
        //add the sibling hypermedia links POST and GET list

        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of OutputParameter","GET","Sibling"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new OutputParameter","POST","Sibling"));


        //add the child hypermedia links GET, PUT, DELETE

        oOutputParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oOutputParameter.getOutputParameterId()),"GET created OutputParameter", "GET", "Child"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oOutputParameter.getOutputParameterId()),"Update created OutputParameter", "PUT", "Child"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oOutputParameter.getOutputParameterId()),"DELETE created OutputParameter", "DELETE", "Child"));

        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath().replaceAll("multiOutputParameter/","multiOutputParameter/");
        
        oSourceOutputParameter = oSQLITEController.getOutputParameter(oSourceOutputParameter);
        if(oSourceOutputParameter.getOutputParameter() != null)
        {
            oRelativePath = oRelativePath.replaceAll(String.format("OutputParameter/[0-9]*/OutputParameter"),String.format("OutputParameter/%d/OutputParameter/%d/OutputParameter",oSourceOutputParameter.getOutputParameter().getOutputParameterId() ,oSourceOutputParameter.getOutputParameterId()));
        }
        
        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update","PUT","Parent"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read","GET","Parent"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete","DELETE","Parent"));

        return oOutputParameter;
    }
}