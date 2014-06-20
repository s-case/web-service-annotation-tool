package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class DELETEInputParameterInputParameterHandler
{

    private SQLITEController oSQLITEController;
    private InputParameterModel oInputParameter;
    private UriInfo		 oApplicationUri;

    DELETEInputParameterInputParameterHandler(int inputParameterId, UriInfo applicationUri)
    {
        oInputParameter = new InputParameterModel();
        oInputParameter.setInputParameterId(inputParameterId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public InputParameterModel deleteInputParameter()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.deleteInputParameter( oInputParameter));
    }

    public InputParameterModel createHypermediaURIs(InputParameterModel oInputParameter)
    {
        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new InputParameter","POST","Parent"));
        oInputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all InputParameter of this InputParameter","GET","Parent"));

        return oInputParameter;
    }

}