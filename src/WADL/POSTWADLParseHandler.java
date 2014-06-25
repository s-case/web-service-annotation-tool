package WADL;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class POSTWADLParseHandler
{
    private AccountModel oAccount;
    private RESTServiceModel oRESTService;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;
    private String wadlName;

    POSTWADLParseHandler(int accountId,UriInfo applicationUri, String wadlName)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        this.oRESTService = new RESTServiceModel();
        oRESTService.setAccount( this.oAccount);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
        this.wadlName = wadlName;
    }

    public void setAccount(AccountModel oAccount)
    {
        this.oAccount = oAccount;
    }

    public AccountModel getAccount()
    {
        return this.oAccount;
    }

    public RESTServiceModel postWADLParse()
    {
        //TODO add authentication if needed

    	
    	parseWADLApplication();
    	
        return createHypermediaURIs();
    }

    public RESTServiceModel createHypermediaURIs()
    {
        //add the sibling hypermedia links POST and GET list
    	oRESTService.getLinkList().clear();
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new RESTService by parsing a WADL file","POST","Sibling"));

        String oRelativePath;
        oRelativePath = oApplicationUri.getPath();
        oRelativePath = oRelativePath.replaceAll(String.format("/algoRESTService/WADLParse"),String.format("/RESTService/%d",oRESTService.getRESTServiceId()));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath),"Get the created RESTService","GET","Child"));

        //add the parent hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex).lastIndexOf("/");
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update Account","PUT","Parent"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read Account","GET","Parent"));
        oRESTService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete Account","DELETE","Parent"));

        return oRESTService;
    }
    
    public void parseWADLApplication()
    {
    	try
    	{
    		//build the JDOM object
    		Document wadlDocument = (Document) (new SAXBuilder()).build(new File(String.format("webapps/wsAnnotationTool/WEB-INF/WADLFiles/%s.xml",wadlName)));
    		
    		//get root element (application node in a WADL file)
    		Element WADLApplicationNode = wadlDocument.getRootElement();
    		
    		//parse application documentation
        	List<Element> listOfApplicationDocs = WADLApplicationNode.getChildren();
        	Iterator<Element> iteratorOfApplicationDocs = listOfApplicationDocs.iterator();
        	
        	//for every documentation element of this rest method
        	while(iteratorOfApplicationDocs.hasNext())
        	{
    			Element oNextApplicationDocElement = iteratorOfApplicationDocs.next();
    			if(oNextApplicationDocElement.getName().equalsIgnoreCase("doc") && oNextApplicationDocElement.getAttribute("title") != null && !oNextApplicationDocElement.getAttributeValue("title").isEmpty())
    			{
    				oRESTService.getWsKeywords().add(oNextApplicationDocElement.getAttributeValue("title"));
    			}
        	}
    		
    		//parse all resources nodes
    		parseEntryPoints(WADLApplicationNode);
    	}
    	catch (IOException io) 
    	{
    		System.out.println(io.getMessage());
    		throw new WebApplicationException(Response.Status.NOT_FOUND);
    	} 
    	catch (JDOMException jdomex) 
    	{
    		System.out.println(jdomex.getMessage());
    		throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
    
    public void parseEntryPoints(Element WADLApplicationNode)
    {    	
		//get "resources" node
		List<Element> listOfEntryPoints = WADLApplicationNode.getChildren();
		
		//for every set of resources create a RESTService
		Iterator<Element> iteratorOfEntryPoints = listOfEntryPoints.iterator();
		
		while( iteratorOfEntryPoints.hasNext() )
		{
			// get next entry point
			Element oNextEntryPoint = iteratorOfEntryPoints.next();
			if(oNextEntryPoint.getName().equalsIgnoreCase("resources"))
			{
				//parse the base URI of this service
				oRESTService.setBaseUri(oNextEntryPoint.getAttributeValue("base"));
			
				//create new RESTService with this base URI
			
				POSTRESTServiceHandler oPOSTRESTServiceHandler = new POSTRESTServiceHandler(oAccount.getAccountId(), oRESTService, oApplicationUri);
				oRESTService = oPOSTRESTServiceHandler.postRESTService();
			
				//parse all "resource" nodes
				parseResourcesOfEntryPoints(oNextEntryPoint);
			}
		}
    }
    
    public void parseResourcesOfEntryPoints(Element oEntryPoint)
    {
    	
    	List<Element> listResourcesOfEntryPoint = oEntryPoint.getChildren();
    	Iterator<Element> iteratorOfResources = listResourcesOfEntryPoint.iterator();
    	
		//and iteratively parse them 
		while( iteratorOfResources.hasNext())
		{
			Element oNextResourceElement = iteratorOfResources.next();
			
			if(oNextResourceElement.getName().equalsIgnoreCase("resource"))
			{
				ResourceModel oResource = new ResourceModel();
				oResource.setRelativeUri(oNextResourceElement.getAttributeValue("path"));
				//create new resource
				POSTResourceHandler oPOSTResourceHandler = new POSTResourceHandler(oRESTService.getRESTServiceId(), oResource, oApplicationUri);
				oResource = oPOSTResourceHandler.postResource();
			
				//parse resource's parameters
				parseResourceParameters(oResource,oNextResourceElement);
			
				//parse resource's methods
				parseResourceMethods(oResource,oNextResourceElement);
			}
		}
    }
    
    public void parseResourceParameters(ResourceModel oResource, Element oResourceElement)
    {    	
    	List<Element> listOfResourceParameters = oResourceElement.getChildren();
    	Iterator<Element> iteratorOfResourceParameters = listOfResourceParameters.iterator();
    	
    	//for each resource parameter
    	while(iteratorOfResourceParameters.hasNext())
    	{
    		//parse the resource parameter
    		Element oNextParameterElement = iteratorOfResourceParameters.next();
    		
			if(oNextParameterElement.getName().equalsIgnoreCase("param"))
			{
				RESTParameterModel oRESTParameter = new RESTParameterModel();
    		
				oRESTParameter.setParameterName(oNextParameterElement.getAttributeValue("name"));
				oRESTParameter.setParameterStyle(oNextParameterElement.getAttributeValue("style"));
				oRESTParameter.setParameterType(oNextParameterElement.getAttributeValue("type"));
				oRESTParameter.setParameterRequired(oNextParameterElement.getAttributeValue("required"));
				oRESTParameter.setDescription(oNextParameterElement.getChildText("doc"));
				oRESTParameter.setParameterDefault(oNextParameterElement.getAttributeValue("default"));

				//parse parameter value options
				parseParameterValueOptions(oRESTParameter, oNextParameterElement);
				
				//create the new parameter
				POSTResourceRESTParameterHandler oPOSTResourceRESTParameterHandler = new POSTResourceRESTParameterHandler(oResource.getResourceId(),oRESTParameter,  oApplicationUri);
				oPOSTResourceRESTParameterHandler.postRESTParameter();
			}
    	}
    }
    
    public void parseResourceMethods(ResourceModel oResource, Element oResourceElement)
    {
    	List<Element> listOfResourceMethods = oResourceElement.getChildren();
    	Iterator<Element> iteratorOfResourceMethods = listOfResourceMethods.iterator();
    	
    	//for each resource method
    	while(iteratorOfResourceMethods.hasNext())
    	{
    		Element oNextMethodElement = iteratorOfResourceMethods.next();
    		
			if(oNextMethodElement.getName().equalsIgnoreCase("method"))
			{
				RESTMethodModel oRESTMethod = new RESTMethodModel();
				oRESTMethod.setMethodIdentifier(oNextMethodElement.getAttributeValue("id"));
				oRESTMethod.setHTTPVerb(oNextMethodElement.getAttributeValue("name"));
    		
				//parse any doc elements that describe this method
				parseMethodDocumentation(oRESTMethod,oNextMethodElement);
    		
				//create RESTMethod
				POSTRESTMethodHandler oPOSTRESTMethodHandler = new POSTRESTMethodHandler(oResource.getResourceId(), oRESTMethod, oApplicationUri);
				oRESTMethod = oPOSTRESTMethodHandler.postRESTMethod();
    		
				//parse any method parameters
				parseMethodParameters(oRESTMethod, oNextMethodElement);
			}
    	}
    }
    
    public void parseMethodDocumentation(RESTMethodModel oRESTMethod, Element oMethodElement)
    {	
    	List<Element> listOfMethodDocs = oMethodElement.getChildren();
    	Iterator<Element> iteratorOfMethodDocs = listOfMethodDocs.iterator();
    	
    	//for every documentation element of this rest method
    	while(iteratorOfMethodDocs.hasNext())
    	{
			Element oNextMethodDocElement = iteratorOfMethodDocs.next();
			if(oNextMethodDocElement.getName().equalsIgnoreCase("doc") && oNextMethodDocElement.getAttribute("title") != null && !oNextMethodDocElement.getAttributeValue("title").isEmpty())
			{
				oRESTMethod.getMethodKeywords().add(oNextMethodDocElement.getAttributeValue("title"));
			}
    	}
    }
    
    public void parseMethodParameters(RESTMethodModel oRESTMethod, Element oMethodElement)
    {
    	
    	List<Element> listOfResourceMethodNodes = oMethodElement.getChildren();
    	Iterator<Element> iteratorOfResourceMethodNodes = listOfResourceMethodNodes.iterator();
    	
    	while( iteratorOfResourceMethodNodes.hasNext() )
    	{
    		Element oNextResourceMethodNode = iteratorOfResourceMethodNodes.next();
    		
    		
    		if(oNextResourceMethodNode.getName().equalsIgnoreCase("request"))
    		{
    			parseMethodRequestParameters(oRESTMethod,  oNextResourceMethodNode);
    		}
    		else if(oNextResourceMethodNode.getName().equalsIgnoreCase("response"))
    		{
    			parseMethodResponseParameters(oRESTMethod, oNextResourceMethodNode);
    		}
    		
    	}
    }
    	
    public void parseMethodRequestParameters(RESTMethodModel oRESTMethod, Element oNextRequestMethodNode)
    {
    		
        	
    	List<Element> listOfMethodRequestParameters = oNextRequestMethodNode.getChildren();
    	Iterator<Element> iteratorOfMethodRequestParameters = listOfMethodRequestParameters.iterator();
    	
    	//for each method parameter
    	while( iteratorOfMethodRequestParameters.hasNext())
    	{
    		Element oNextMethodRequestParameter = iteratorOfMethodRequestParameters.next();
    		
			if(oNextMethodRequestParameter.getName().equalsIgnoreCase("param"))
			{
				RESTParameterModel oRESTParameter = new RESTParameterModel();
    		
				oRESTParameter.setParameterName(oNextMethodRequestParameter.getAttributeValue("name"));
				oRESTParameter.setParameterStyle(oNextMethodRequestParameter.getAttributeValue("style"));
				oRESTParameter.setParameterType(oNextMethodRequestParameter.getAttributeValue("type"));
				oRESTParameter.setParameterRequired(oNextMethodRequestParameter.getAttributeValue("required"));
				oRESTParameter.setDescription(oNextMethodRequestParameter.getChildText("doc"));
				oRESTParameter.setParameterDefault(oNextMethodRequestParameter.getAttributeValue("default"));
				oRESTParameter.setParameterDirection("request");
				
				//parse parameter value options
				parseParameterValueOptions(oRESTParameter, oNextMethodRequestParameter);
				
				//create the new parameter
				POSTRESTMethodRESTParameterHandler oPOSTRESTMethodRESTParameterHandler = new POSTRESTMethodRESTParameterHandler(oRESTMethod.getRESTMethodId(),  oRESTParameter, oApplicationUri);
				oPOSTRESTMethodRESTParameterHandler.postRESTParameter();
			}
    	}
    }
    
    public void parseMethodResponseParameters(RESTMethodModel oRESTMethod, Element oNextResponseMethodNode)
    {	
    	List<Element> listOfMethodResponseParameters = oNextResponseMethodNode.getChildren();
    	Iterator<Element> iteratorOfMethodResponseParameters = listOfMethodResponseParameters.iterator();
	
    	//for each method parameter
    	while( iteratorOfMethodResponseParameters.hasNext())
    	{
    		Element oNextMethodResponseParameter = iteratorOfMethodResponseParameters.next();
		
    		if(oNextMethodResponseParameter.getName().equalsIgnoreCase("param"))
    		{
    			RESTParameterModel oRESTParameter = new RESTParameterModel();
		
    			oRESTParameter.setParameterName(oNextMethodResponseParameter.getAttributeValue("name"));
    			oRESTParameter.setParameterStyle(oNextMethodResponseParameter.getAttributeValue("style"));
    			oRESTParameter.setParameterType(oNextMethodResponseParameter.getAttributeValue("type"));
    			oRESTParameter.setParameterRequired(oNextMethodResponseParameter.getAttributeValue("required"));
    			oRESTParameter.setDescription(oNextMethodResponseParameter.getChildText("doc"));
    			oRESTParameter.setParameterDefault(oNextMethodResponseParameter.getAttributeValue("default"));
    			oRESTParameter.setParameterDirection("response");
    			
				//parse parameter value options
				parseParameterValueOptions(oRESTParameter, oNextMethodResponseParameter);
    			
    			//create the new parameter
    			POSTRESTMethodRESTParameterHandler oPOSTRESTMethodRESTParameterHandler = new POSTRESTMethodRESTParameterHandler(oRESTMethod.getRESTMethodId(),  oRESTParameter, oApplicationUri);
    			oPOSTRESTMethodRESTParameterHandler.postRESTParameter();
    		}
    	}
    }
    
    public void parseParameterValueOptions(RESTParameterModel oRESTParameter, Element oMethodParameter)
    {
    	List<Element> listOfParameterValueOptions = oMethodParameter.getChildren();
    	Iterator<Element> iteratorOfParameterValueOptions = listOfParameterValueOptions.iterator();
    	
    	//for each parameter value options
    	while(iteratorOfParameterValueOptions.hasNext())
    	{
    		Element oNextParameterValueOption = iteratorOfParameterValueOptions.next();
    		
    		oRESTParameter.getParameterValueOption().add(oNextParameterValueOption.getAttributeValue("value"));
    	}
    }
    	
}