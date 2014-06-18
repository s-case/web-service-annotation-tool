package WADL;

import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

public class GETResourceRESTParameterListHandler
{

    private SQLITEController oSQLITEController;
    private ResourceModel oResource;
    private UriInfo		 oApplicationUri;
    
    GETResourceRESTParameterListHandler(int resourceId, UriInfo applicationUri)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public RESTParameterModel getRESTParameterList()
    {
        //TODO add authentication if needed

        //get the <resourceName>List from the database
        oResource = oSQLITEController.getResourceRESTParameterList( oResource);
        return createHypermediaURIs(oResource);
    }

    public RESTParameterModel createHypermediaURIs(ResourceModel oResource)
    {
        //create an empty <resourceModel> and populate its linklists with hypermedia links
    	RESTParameterModel oRESTParameter = new RESTParameterModel();

        //add the sibling hypermedia links POST and GET list

        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of RESTParameter","GET","Sibling"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new RESTParameter","POST","Sibling"));


        //add the children hypermedia links GET
        Iterator<RESTParameterModel> setIterator = oResource.getSetOfRESTParameter().iterator();

        while(setIterator.hasNext())
        {
        	RESTParameterModel oNextRESTParameter = new RESTParameterModel();
            oNextRESTParameter = setIterator.next();
            oRESTParameter.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextRESTParameter.getRESTParameterId()),String.format("%s",oNextRESTParameter.getParameterName()), "GET", "Child"));
        }

        String oRelativePath;
        //add the child hypermedia links POST, GETL


        oRelativePath = oApplicationUri.getPath().replaceAll("multiRESTParameter/","");
        
        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update Resource","PUT","Parent"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read Resource","GET","Parent"));
        oRESTParameter.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete Resource","DELETE","Parent"));
        return oRESTParameter;
    }
    
}