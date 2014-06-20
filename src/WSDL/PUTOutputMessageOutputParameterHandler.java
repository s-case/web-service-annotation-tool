package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class PUTOutputMessageOutputParameterHandler
{

    private OutputMessageModel oOutputMessage;
    private OutputParameterModel oOutputParameter;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;

    PUTOutputMessageOutputParameterHandler(int outputMessageId, int outputParameterId, OutputParameterModel oOutputParameter, UriInfo applicationUri)
    {
        oOutputMessage = new OutputMessageModel();
        oOutputMessage.setOutputMessageId(outputMessageId);
        this.oOutputParameter = oOutputParameter;
        this.oOutputParameter.setOutputParameterId(outputParameterId);
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

    public OutputParameterModel putOutputParameter()
    {
        //TODO add authentication if needed

        return createHypermediaURIs(oSQLITEController.putOutputParameter( oOutputParameter));
    }

    public OutputParameterModel createHypermediaURIs(OutputParameterModel oOutputParameter)
    {
        //add the sibling hypermedia links PUT, GET, DELETE

        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Update again OutputParameter","PUT","Sibling"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"GET the updated OutputParameter","GET","Sibling"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"DELETE the updated OutputParameter","DELETE","Sibling"));


        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = String.format("%s/%s","multiOutputParameter",oApplicationUri.getPath());

        oOutputParameter.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"OutputParameter"),"Create a new OutputParameter for this OutputParameter", "POST", "Child"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s/%s",oApplicationUri.getBaseUri(),oRelativePath,"OutputParameter"),"GET all the OutputParameter of this OutputParameter", "GET", "Child"));

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new OutputParameter","POST","Parent"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all OutputParameter of this OutputMessage","GET","Parent"));

        return oOutputParameter;
    }

}

