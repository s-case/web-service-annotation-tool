package WSDL;

import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class GETInputMessageListHandler
{

    private SQLITEController oSQLITEController;
    private SOAPOperationModel oSOAPOperation;
    private UriInfo		 oApplicationUri;

    GETInputMessageListHandler(int SOAPOperationId, UriInfo applicationUri)
    {
        oSOAPOperation = new SOAPOperationModel();
        oSOAPOperation.setSOAPOperationId(SOAPOperationId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public InputMessageModel getInputMessageList()
    {
        //TODO add authentication if needed

        //get the <resourceName>List from the database
        oSOAPOperation = oSQLITEController.getInputMessageList( oSOAPOperation);
        return createHypermediaURIs(oSOAPOperation);
    }


    public InputMessageModel createHypermediaURIs(SOAPOperationModel oSOAPOperation)
    {
        //create an empty <resourceModel> and populate its linklists with hypermedia links
    	InputMessageModel oInputMessage = new InputMessageModel();

        //add the sibling hypermedia links POST and GET list

        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of InputMessage","GET","Sibling"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new InputMessage","POST","Sibling"));


        //add the children hypermedia links GET
        Iterator<InputMessageModel> setIterator = oSOAPOperation.getSetOfInputMessage().iterator();

        while(setIterator.hasNext())
        {
        	InputMessageModel oNextInputMessage = new InputMessageModel();
            oNextInputMessage = setIterator.next();
            oInputMessage.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextInputMessage.getInputMessageId()),String.format("%s",oNextInputMessage.getName()), "GET", "Child"));
        }

        String oRelativePath;
        //add the parent hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update SOAPOperation","PUT","Parent"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read SOAPOperation","GET","Parent"));
        oInputMessage.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete SOAPOperation","DELETE","Parent"));

        return oInputMessage;
    }

//TODO Extension is needed to support getList on resources that are not related resource of any other.
}