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
        //create an empty <resourceModel> and populate its linklists with hypermedia links
    	RESTParameterModel oRESTParameter = new RESTParameterModel();

        //add the sibling hypermedia links POST and GET list

        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of RESTParameter","GET","Sibling"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new RESTParameter","POST","Sibling"));


        //add the children hypermedia links GET
        Iterator<RESTParameterModel> setIterator = oRESTMethod.getSetOfRESTParameter().iterator();

        while(setIterator.hasNext())
        {
        	RESTParameterModel oNextRESTParameter = new RESTParameterModel();
            oNextRESTParameter = setIterator.next();
            oRESTParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextRESTParameter.getRESTParameterId()),String.format("%s",oNextRESTParameter.getParameterName()), "GET", "Child"));
        }

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).lastIndexOf("/");
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Update RESTMethod","PUT","Parent"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Read RESTMethod","GET","Parent"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()).substring(0, iLastSlashIndex),"Delete RESTMethod","DELETE","Parent"));

        return oRESTParameter;
    }
}