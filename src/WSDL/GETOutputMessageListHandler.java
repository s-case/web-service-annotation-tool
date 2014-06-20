package WSDL;

import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class GETOutputMessageListHandler
{

    private SQLITEController oSQLITEController;
    private SOAPOperationModel oSOAPOperation;
    private UriInfo		 oApplicationUri;

    GETOutputMessageListHandler(int SOAPOperationId, UriInfo applicationUri)
    {
        oSOAPOperation = new SOAPOperationModel();
        oSOAPOperation.setSOAPOperationId(SOAPOperationId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public OutputMessageModel getOutputMessageList()
    {
        //TODO add authentication if needed

        //get the <resourceName>List from the database
        oSOAPOperation = oSQLITEController.getOutputMessageList( oSOAPOperation);
        return createHypermediaURIs(oSOAPOperation);
    }


    public OutputMessageModel createHypermediaURIs(SOAPOperationModel oSOAPOperation)
    {
        //create an empty <resourceModel> and populate its linklists with hypermedia links
    	OutputMessageModel oOutputMessage = new OutputMessageModel();

        //add the sibling hypermedia links POST and GET list

        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of OutputMessage","GET","Sibling"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new OutputMessage","POST","Sibling"));


        //add the children hypermedia links GET
        Iterator<OutputMessageModel> setIterator = oSOAPOperation.getSetOfOutputMessage().iterator();

        while(setIterator.hasNext())
        {
        	OutputMessageModel oNextOutputMessage = new OutputMessageModel();
            oNextOutputMessage = setIterator.next();
            oOutputMessage.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextOutputMessage.getOutputMessageId()),String.format("%s",oNextOutputMessage.getName()), "GET", "Child"));
        }

        String oRelativePath;
        //add the parent hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update SOAPOperation","PUT","Parent"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read SOAPOperation","GET","Parent"));
        oOutputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete SOAPOperation","DELETE","Parent"));

        return oOutputMessage;
    }

//TODO Extension is needed to support getList on resources that are not related resource of any other.
}