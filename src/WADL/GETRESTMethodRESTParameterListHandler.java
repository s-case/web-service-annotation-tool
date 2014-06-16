package WADL;

import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

public class GETRESTMethodRESTParameterListHandler
{

    private SQLITEController oSQLITEController;
    private RESTMethodModel oRESTMethod;
    private UriInfo		 oApplicationUri;
    
    GETRESTMethodRESTParameterListHandler(int RESTMethodId, UriInfo applicationUri)
    {
        oRESTMethod = new RESTMethodModel();
        oRESTMethod.setRESTMethodId(RESTMethodId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public RESTParameterModel getRESTParameterList()
    {
        //TODO add authentication if needed

        //get the <resourceName>List from the database
        oRESTMethod = oSQLITEController.getRESTMethodRESTParameterList( oRESTMethod);
        return createHypermediaURIs(oRESTMethod);
    }

    public RESTParameterModel createHypermediaURIs(RESTMethodModel oRESTMethod)
    {
        RESTParameterModel oRESTParameter = new RESTParameterModel();
        Iterator<RESTParameterModel> setIterator = oRESTMethod.getSetOfRESTParameter().iterator();

        while(setIterator.hasNext())
        {
            RESTParameterModel oNextRESTParameter = new RESTParameterModel();
            oNextRESTParameter = setIterator.next();
            oRESTParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextRESTParameter.getRESTParameterId()),String.format("%s",oNextRESTParameter.getParameterName()), "GET"));
        }

        return oRESTParameter;
    }
}