package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class PUTInputParameterInputParameterHandler
{

    private InputParameterModel oSourceInputParameter;
    private InputParameterModel oInputParameter;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    PUTInputParameterInputParameterHandler(int sourceInputParameterId, int inputParameterId, InputParameterModel oInputParameter, UriInfo applicationUri)
    {
        oSourceInputParameter = new InputParameterModel();
        oSourceInputParameter.setInputParameterId(sourceInputParameterId);
        this.oInputParameter = oInputParameter;
        this.oInputParameter.setInputParameterId(inputParameterId);
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

    public InputParameterModel putInputParameter()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.putInputParameter( oInputParameter));
    }

    public InputParameterModel createHypermediaURIs(InputParameterModel oInputParameter)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update again InputParameter","PUT","Sibling"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the updated InputParameter","GET","Sibling"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the updated InputParameter","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL


        oRelativePath = oApplicationUri.getPath().replaceAll("multiInputParameter/","multiInputParameter/");

        oRelativePath = oRelativePath.replaceAll(String.format("InputParameter/[0-9]*/InputParameter/%d",oInputParameter.getInputParameterId()),String.format("InputParameter/%d",oInputParameter.getInputParameterId()));

        oInputParameter.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"<targetResourceName>"),"Create a new InputParameter for this InputParameter", "POST", "Child"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"<targetResourceName>"),"GET all the InputParameter of this InputParameter", "GET", "Child"));

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new InputParameter","POST","Parent"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all InputParameter of this InputParameter","GET","Parent"));

        return oInputParameter;
    }

}