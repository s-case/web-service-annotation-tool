package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class POSTInputMessageInputParameterHandler
{
    private InputMessageModel oInputMessage;
    private InputParameterModel oInputParameter;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    POSTInputMessageInputParameterHandler(int inputMessageId, InputParameterModel oInputParameter, UriInfo applicationUri)
    {
        oInputMessage = new InputMessageModel();
        oInputMessage.setInputMessageId(inputMessageId);
        this.oInputParameter = oInputParameter;
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
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath().replaceAll("multiInputParameter/","");

        
        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update InputMessage","PUT","Parent"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read InputMessage","GET","Parent"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete InputMessage","DELETE","Parent"));

        return oInputParameter;
    }
}

