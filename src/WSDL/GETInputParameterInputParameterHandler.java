package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class GETInputParameterInputParameterHandler
{
    private SQLITEController oSQLITEController;
    private InputParameterModel oInputParameter;
    private UriInfo		 oApplicationUri;

    GETInputParameterInputParameterHandler(int inputParameterId, UriInfo applicationUri)
    {
        oInputParameter = new InputParameterModel();
        oInputParameter.setInputParameterId(inputParameterId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public InputParameterModel getInputParameter()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.getInputParameter( oInputParameter));
    }

    public InputParameterModel createHypermediaURIs(InputParameterModel oInputParameter)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update  InputParameter","PUT","Sibling"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET again the InputParameter","GET","Sibling"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the InputParameter","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath().replaceAll("multiInputParameter/","multiInputParameter/");

        oRelativePath = oRelativePath.replaceAll(String.format("InputParameter/(.*)/InputParameter/%d",oInputParameter.getInputParameterId()),String.format("InputParameter/%d",oInputParameter.getInputParameterId()));

        oInputParameter.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"InputParameter"),"Create a new InputParameter for this InputParameter", "POST", "Child"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"InputParameter"),"GET all the InputParameter of this InputParameter", "GET", "Child"));

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new InputParameter","POST","Parent"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all InputParameter of this InputParameter","GET","Parent"));

        return oInputParameter;
    }

}