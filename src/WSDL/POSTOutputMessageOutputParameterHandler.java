package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class POSTOutputMessageOutputParameterHandler
{
    private OutputMessageModel oOutputMessage;
    private OutputParameterModel oOutputParameter;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    POSTOutputMessageOutputParameterHandler(int outputMessageId, OutputParameterModel oOutputParameter, UriInfo applicationUri)
    {
        oOutputMessage = new OutputMessageModel();
        oOutputMessage.setOutputMessageId(outputMessageId);
        this.oOutputParameter = oOutputParameter;
        oOutputParameter.setOutputMessage( this.oOutputMessage);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public void setOutputMessage(OutputMessageModel oOutputMessage)
    {
        this.oOutputMessage = oOutputMessage;
    }

    public OutputMessageModel getOutputMessage()
    {
        return this.oOutputMessage;
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

        oRelativePath = String.format("%s/%s","multiOutputParameter",oApplicationUri.getPath());

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update OutputMessage","PUT","Parent"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read OutputMessage","GET","Parent"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete OutputMessage","DELETE","Parent"));

        return oOutputParameter;
    }
}

