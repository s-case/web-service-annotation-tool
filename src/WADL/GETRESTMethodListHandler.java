package WADL;

import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

public class GETRESTMethodListHandler
{

    private SQLITEController oSQLITEController;
    private ResourceModel oResource;
    private UriInfo		 oApplicationUri;
    
    GETRESTMethodListHandler(int resourceId, UriInfo applicationUri)
    {
        oResource = new ResourceModel();
        oResource.setResourceId(resourceId);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
    }

    public RESTMethodModel getRESTMethodList()
    {
        //TODO add authentication if needed

        //get the <resourceName>List from the database
        oResource = oSQLITEController.getRESTMethodList( oResource);
        return createHypermediaURIs(oResource);
    }

    public RESTMethodModel createHypermediaURIs(ResourceModel oResource)
    {
        //create an empty <resourceModel> and populate its linklists with hypermedia links
    	RESTMethodModel oRESTMethod = new RESTMethodModel();

        //add the sibling hypermedia links POST and GET list

        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"List of RESTMethod","GET","Sibling"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new RESTMethod","POST","Sibling"));


        //add the children hypermedia links GET
        Iterator<RESTMethodModel> setIterator = oResource.getSetOfRESTMethod().iterator();

        while(setIterator.hasNext())
        {
        	RESTMethodModel oNextRESTMethod = new RESTMethodModel();
            oNextRESTMethod = setIterator.next();
            oRESTMethod.getLinkList().add(new Link(String.format("%s%s/%d",oApplicationUri.getBaseUri(),oApplicationUri.getPath(),oNextRESTMethod.getRESTMethodId()),String.format("%s",oNextRESTMethod.getMethodIdentifier()), "GET", "Child"));
        }

        String oRelativePath;
        //add the child hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update Resource","PUT","Parent"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read Resource","GET","Parent"));
        oRESTMethod.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete Resource","DELETE","Parent"));
        return oRESTMethod;
    }
}