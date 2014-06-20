package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class PUTInputMessageInputParameterHandler
{

    private InputMessageModel oInputMessage;
    private InputParameterModel oInputParameter;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    PUTInputMessageInputParameterHandler(int inputMessageId,int inputParameterId, InputParameterModel oInputParameter, UriInfo applicationUri)
    {
        oInputMessage = new InputMessageModel();
        oInputMessage.setInputMessageId(inputMessageId);
        this.oInputParameter = oInputParameter;
        this.oInputParameter.setInputParameterId(inputParameterId);
        oInputParameter.setInputMessage( this.oInputMessage);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public void setInputMessage(InputMessageModel oInputMessage)
    {
        this.oInputMessage = oInputMessage;
    }

    public InputMessageModel getInputMessage()
    {
        return this.oInputMessage;
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

        oRelativePath = String.format("%s/%s","multiInputParameter",oApplicationUri.getPath());

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