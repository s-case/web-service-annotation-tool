package WSDL;

import javax.ws.rs.core.UriInfo;

import WADL.Link;
import WADL.SQLITEController;

public class DELETEOutputMessageOutputParameterHandler
{

    private SQLITEController oSQLITEController;
    private OutputParameterModel oOutputParameter;
    private UriInfo		 oApplicationUri;

    DELETEOutputMessageOutputParameterHandler(int outputParameterId, UriInfo applicationUri)
    {
        oOutputParameter = new OutputParameterModel();
        oOutputParameter.setOutputParameterId(outputParameterId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public OutputParameterModel deleteOutputParameter()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        return createHypermediaURIs(oSQLITEController.deleteOutputParameter( oOutputParameter));
    }

    public OutputParameterModel createHypermediaURIs(OutputParameterModel oOutputParameter)
    {

        //add the parent's hypermedia links POST, GETL
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Create new OutputParameter","POST","Parent"));
        oOutputParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read all OutputParameter of this OutputMessage","GET","Parent"));

        return oOutputParameter;
    }

}