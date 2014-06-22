package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class POSTInputParameterInputParameterHandler
{
    private InputParameterModel oSourceInputParameter;
    private InputParameterModel oInputParameter;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    POSTInputParameterInputParameterHandler(int sourceInputParameterId, InputParameterModel oInputParameter, UriInfo applicationUri)
    {
        oSourceInputParameter = new InputParameterModel();
        oSourceInputParameter.setInputParameterId(sourceInputParameterId);
        this.oInputParameter = oInputParameter;
        oInputParameter.setInputParameter( this.oSourceInputParameter);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public void setInputParameter(InputParameterModel oSourceInputParameter)
    {
        this.oSourceInputParameter = oSourceInputParameter;
    }

    public InputParameterModel getInputParameter()
    {
        return this.oSourceInputParameter;
    }

    public InputParameterModel postInputParameter()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.postInputParameter( oInputParameter));
    }

    public InputParameterModel createHypermediaURIs(InputParameterModel oInputParameter)
    {
        //add the sibling hypermedia links POST and GET list
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of InputParameter","GET","Sibling"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new InputParameter","POST","Sibling"));

        //add the child hypermedia links GET, PUT, DELETE

        oInputParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oInputParameter.getInputParameterId()),"GET created InputParameter", "GET", "Child"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oInputParameter.getInputParameterId()),"Update created InputParameter", "PUT", "Child"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oInputParameter.getInputParameterId()),"DELETE created InputParameter", "DELETE", "Child"));


        String oRelativePath;
        //add the parent hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath().replaceAll("multiInputParameter/","multiInputParameter/");

        oSourceInputParameter = oSQLITEController.getInputParameter(oSourceInputParameter);
        if(oSourceInputParameter.getInputParameter() != null)
        {
            oRelativePath = oRelativePath.replaceAll(String.format("/InputParameter/(.*)/InputParameter"),String.format("/InputParameter/%d/InputParameter/%d/InputParameter",oSourceInputParameter.getInputParameter().getInputParameterId() ,oSourceInputParameter.getInputParameterId()));
        }

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update","PUT","Parent"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read","GET","Parent"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete","DELETE","Parent"));

        return oInputParameter;
    }
}