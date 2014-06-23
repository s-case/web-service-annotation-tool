package WSDL;

import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class GETInputParameterInputParameterListHandler
{

    private SQLITEController oSQLITEController;
    private InputParameterModel oSourceInputParameter;
    private UriInfo		 oApplicationUri;

    GETInputParameterInputParameterListHandler(int sourceInputParameterId, UriInfo applicationUri)
    {
        oSourceInputParameter = new InputParameterModel();
        oSourceInputParameter.setInputParameterId(sourceInputParameterId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public InputParameterModel getInputParameterList()
    {
        //TODO add authentication if needed

        //get the <resourceName>List from the database
        oSourceInputParameter = oSQLITEController.getInputParameterInputParameterList( oSourceInputParameter);
        return createHypermediaURIs(oSourceInputParameter);
    }


    public InputParameterModel createHypermediaURIs(InputParameterModel oSourceInputParameter)
    {
        //create an empty <resourceModel> and populate its linklists with hypermedia links
    	InputParameterModel oInputParameter = new InputParameterModel();

        //add the sibling hypermedia links POST and GET list

        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of InputParameter","GET","Sibling"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new InputParameter","POST","Sibling"));


        //add the children hypermedia links GET
        Iterator<InputParameterModel> setIterator = oSourceInputParameter.getSetOfInputParameter().iterator();

        while(setIterator.hasNext())
        {
        	InputParameterModel oNextInputParameter = new InputParameterModel();
            oNextInputParameter = setIterator.next();
            oInputParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextInputParameter.getInputParameterId()),String.format("%s",oNextInputParameter.getName()), "GET", "Child"));
        }

        String oRelativePath;
        //add the parent hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath().replaceAll("multiInputParameter/","multiInputParameter/");

        oSourceInputParameter = oSQLITEController.getInputParameter(oSourceInputParameter);
        if(oSourceInputParameter.getInputParameter() != null)
        {
            oRelativePath = oRelativePath.replaceAll(String.format("InputParameter/[0-9]*/InputParameter"),String.format("InputParameter/%d/InputParameter/%d/InputParameter",oSourceInputParameter.getInputParameter().getInputParameterId() ,oSourceInputParameter.getInputParameterId()));
        }

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update","PUT","Parent"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read","GET","Parent"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete","DELETE","Parent"));

        return oInputParameter;
    }

//TODO Extension is needed to support getList on resources that are not related resource of any other.
}