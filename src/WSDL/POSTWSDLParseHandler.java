package WSDL;

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

import WADL.AccountModel;
import WADL.Link;
import WADL.SQLITEController;


public class POSTWSDLParseHandler
{
    private AccountModel oAccount;
    private SOAPServiceModel oSOAPService;
    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;
    private String wsdlName;
    private Element WSDLTypesSchema;
    private Element WSDLApplicationNode;

    POSTWSDLParseHandler(int accountId,UriInfo applicationUri, String wsdlName)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        this.oSOAPService = new SOAPServiceModel();
        oSOAPService.setAccount( this.oAccount);
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
        this.wsdlName = wsdlName;
    }

    public void setAccount(AccountModel oAccount)
    {
        this.oAccount = oAccount;
    }

    public AccountModel getAccount()
    {
        return this.oAccount;
    }

    public SOAPServiceModel postWSDLParse()
    {
        //TODO add authentication if needed

    	
    	parseWSDLApplication();
    	
        return createHypermediaURIs();
    }

    public SOAPServiceModel createHypermediaURIs()
    {
        //add the sibling hypermedia links POST and GET list
    	oSOAPService.getLinkList().clear();
    	oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oApplicationUri.getPath()),"Create new SOAPService by parsing a WSDL file","POST","Sibling"));

        String oRelativePath;
        oRelativePath = oApplicationUri.getPath();
        oRelativePath = oRelativePath.replaceAll(String.format("/algoSOAPService/WSDLParse"),String.format("/SOAPService/%d",oSOAPService.getSOAPServiceId()));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath),"Get the created SOAPService","GET","Child"));

        //add the parent hypermedia links POST, GETL

        oRelativePath = oApplicationUri.getPath();

        //add the parent's hypermedia links PUT, GET DELETE
        //find last index of "/" in order to cut off to get the parent URI appropriately
        int iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).lastIndexOf("/");
        iLastSlashIndex = String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex).lastIndexOf("/");
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Update Account","PUT","Parent"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Read Account","GET","Parent"));
        oSOAPService.getLinkList().add(new Link(String.format("%s%s",oApplicationUri.getBaseUri(),oRelativePath).substring(0, iLastSlashIndex),"Delete Account","DELETE","Parent"));

        return oSOAPService;
    }
    
    public void parseWSDLApplication()
    {
    	try
    	{
    		//build the JDOM object
    		Document wsdlDocument = (Document) (new SAXBuilder()).build(new File(String.format("webapps/wsAnnotationTool/WEB-INF/WSDLFiles/%s.xml",wsdlName)));
    		
    		//get root element (application node in a WSDL file)
    		WSDLApplicationNode = wsdlDocument.getRootElement();
    		
    		//initialize types schema element
    		initializeSchemaTypes(WSDLApplicationNode);
    		
    		//parse services
    		parseSOAPServices(WSDLApplicationNode);
    		
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
    
    public void initializeSchemaTypes(Element WSDLApplicationNode)
    {
    	List<Element> listOfWSDLApplicationNodes = WSDLApplicationNode.getChildren();
    	Iterator<Element> iteratorOfWSDLApplicationNodes = listOfWSDLApplicationNodes.iterator();
    	
    	while(iteratorOfWSDLApplicationNodes.hasNext())
    	{
    		Element nextApplicationNode = iteratorOfWSDLApplicationNodes.next();
    		
    		//if it is the types element
    		if(nextApplicationNode.getName().equalsIgnoreCase("types"))
    		{
    			List<Element> listOfTypesElement = nextApplicationNode.getChildren();
    			Iterator<Element> iteratorOfTypesElement = listOfTypesElement.iterator();
    			
    			while(iteratorOfTypesElement.hasNext())
    			{
    				Element nextTypesElement = iteratorOfTypesElement.next();
    				if(nextTypesElement.getName().equalsIgnoreCase("schema"))
    				{
    					WSDLTypesSchema = nextTypesElement;
    				}
    			}
    		}
    	}
    	
    	//check if the types element is initialized
    	if( WSDLTypesSchema == null)
    	{
    		throw new WebApplicationException(Response.Status.BAD_REQUEST);
    	}
    }
    
    public void parseSOAPServices(Element WSDLApplicationNode)
    {
    	List<Element> listOfApplicationNodes = WSDLApplicationNode.getChildren();
    	Iterator<Element> iteratorOfApplicationNodes = listOfApplicationNodes.iterator();
    	
    	while(iteratorOfApplicationNodes.hasNext())
    	{
    		Element nextServiceElement = iteratorOfApplicationNodes.next();
    		if(nextServiceElement.getName().equalsIgnoreCase("service"))
    		{
    			//parse SOAPService name
    			oSOAPService = new SOAPServiceModel();
    			oSOAPService.setName(nextServiceElement.getAttributeValue("name"));
    			oSOAPService.setDescription(parseChildDocumentation(nextServiceElement));
    			oSOAPService.setAccount(oAccount);
    			
    			POSTSOAPServiceHandler oPOSTSOAPServiceHandler = new POSTSOAPServiceHandler(oAccount.getAccountId(), oSOAPService, oApplicationUri);
    			oSOAPService = oPOSTSOAPServiceHandler.postSOAPService();
    			
    			//parse SOAP service ports
    			parseSOAPServicePorts(nextServiceElement,oSOAPService);
    		}
    	}
    }
    
    public String parseChildDocumentation(Element parentElement)
    {
    	List<Element> listOfChildDocumentationNodes = parentElement.getChildren();
    	Iterator<Element> iteratorOfChildDocumentationNodes = listOfChildDocumentationNodes.iterator();
    	
    	while(iteratorOfChildDocumentationNodes.hasNext())
    	{
    		Element nextDocumentationNode = iteratorOfChildDocumentationNodes.next();
    		
    		if(nextDocumentationNode.getName().equalsIgnoreCase("documentation"))
    		{
    			return nextDocumentationNode.getText();
    		}
    	}
    	
    	return null;
    }
    
    public void parseSOAPServicePorts(Element SOAPServiceElement, SOAPServiceModel oSOAPService)
    {
    	List<Element> listOfServicePorts = SOAPServiceElement.getChildren();
    	Iterator<Element> iteratorOfServicePorts = listOfServicePorts.iterator();
    	
    	while(iteratorOfServicePorts.hasNext())
    	{
    		Element nextPortElement = iteratorOfServicePorts.next();
    		if( nextPortElement.getName().equalsIgnoreCase("port"))
    		{    	
    			//parse port binding by name
    			parsePortBindingByName( removeNameSpace(nextPortElement.getAttributeValue("binding")),oSOAPService);
    		}
    	}
    }
    
    public void parsePortBindingByName(String bindingName, SOAPServiceModel oSOAPService)
    {
    	List<Element> listOfWSDLBindings = WSDLApplicationNode.getChildren();
    	Iterator<Element> iteratorOfWSDLBindings = listOfWSDLBindings.iterator();
    	
    	while(iteratorOfWSDLBindings.hasNext())
    	{
    		Element nextBindingElement = iteratorOfWSDLBindings.next();
    		if(nextBindingElement.getName().equalsIgnoreCase("binding"))
    		{
    			if(nextBindingElement.getAttributeValue("name").equalsIgnoreCase(bindingName))
    			{
    				parsePortTypeByName(removeNameSpace(nextBindingElement.getAttributeValue("type")),oSOAPService);
    			}
    		}
    	}
    }
    
    public void parsePortTypeByName(String portTypeName, SOAPServiceModel oSOAPService)
    {
    	List<Element> listOfPortTypes = WSDLApplicationNode.getChildren();
    	Iterator<Element> iteratorOfPortTypes = listOfPortTypes.iterator();
    	
    	while(iteratorOfPortTypes.hasNext())
    	{
    		Element nextPortTypeElement = iteratorOfPortTypes.next();
    		if(nextPortTypeElement.getName().equalsIgnoreCase("portType"))
    		{
    			if(nextPortTypeElement.getAttributeValue("name").equalsIgnoreCase(portTypeName))
    			{
    				parseSOAPServiceOperations(oSOAPService, nextPortTypeElement);
    			}
    		}
    	}
    }
    
    public void parseSOAPServiceOperations(SOAPServiceModel oSOAPService, Element portTypeElement)
    {
    	List<Element> listOfSOAPOperations = portTypeElement.getChildren();
    	Iterator<Element> iteratorOfSOAPOperations = listOfSOAPOperations.iterator();
    	
    	while(iteratorOfSOAPOperations.hasNext())
    	{
    		Element nextSOAPOperationElement = iteratorOfSOAPOperations.next();
    		
    		SOAPOperationModel oSOAPOperation = new SOAPOperationModel();
    		oSOAPOperation.setName(nextSOAPOperationElement.getAttributeValue("name"));
    		oSOAPOperation.setDescription(parseChildDocumentation(nextSOAPOperationElement));
    		
    		POSTSOAPOperationHandler oPOSTSOAPOperationHandler = new POSTSOAPOperationHandler(oSOAPService.getSOAPServiceId(), oSOAPOperation, oApplicationUri);
    		oSOAPOperation = oPOSTSOAPOperationHandler.postSOAPOperation();
    		
    		//parse inputs
    		parseSOAPOperationInput(oSOAPOperation, nextSOAPOperationElement);
    		
    		//parse outputs
    		parseSOAPOperationOutput(oSOAPOperation, nextSOAPOperationElement);
    	}
    }
    
    public void parseSOAPOperationInput(SOAPOperationModel oSOAPOperation, Element SOAPOperationElement)
    {
    	List<Element> listOfOperationInputs = SOAPOperationElement.getChildren();
    	Iterator<Element> iteratorOfOperationInputs = listOfOperationInputs.iterator();
    	
    	while(iteratorOfOperationInputs.hasNext())
    	{
    		Element nextOperationInputElement = iteratorOfOperationInputs.next();
    		
    		if(nextOperationInputElement.getName().equalsIgnoreCase("input"))
    		{
    			parseInputMessage(removeNameSpace(nextOperationInputElement.getAttributeValue("message")),oSOAPOperation);
    		}
    	}
    }
    
    public void parseSOAPOperationOutput(SOAPOperationModel oSOAPOperation, Element SOAPOperationElement)
    {
    	List<Element> listOfOperationOutputs = SOAPOperationElement.getChildren();
    	Iterator<Element> iteratorOfOperationOutputs = listOfOperationOutputs.iterator();
    	
    	while(iteratorOfOperationOutputs.hasNext())
    	{
    		Element nextOperationOutputElement = iteratorOfOperationOutputs.next();
    		
    		if(nextOperationOutputElement.getName().equalsIgnoreCase("output"))
    		{
    			parseOutputMessage(removeNameSpace(nextOperationOutputElement.getAttributeValue("message")),oSOAPOperation);
    		}
    	}
    }
    
    public void parseInputMessage(String inputMessageName, SOAPOperationModel oSOAPOperation)
    {
    	List<Element> listOfMessages = WSDLApplicationNode.getChildren();
    	Iterator<Element> iteratorOfMessages = listOfMessages.iterator();
    	
    	while(iteratorOfMessages.hasNext())
    	{
    		Element nextMessageElement = iteratorOfMessages.next();
    		
    		if(nextMessageElement.getName().equalsIgnoreCase("message"))
    		{
    			if(nextMessageElement.getAttributeValue("name").equalsIgnoreCase(inputMessageName))
    			{
    				InputMessageModel oInputMessage = new InputMessageModel();
    				oInputMessage.setName(nextMessageElement.getAttributeValue("name"));
    				
    				POSTInputMessageHandler oPOSTInputMessageHandler = new POSTInputMessageHandler(oSOAPOperation.getSOAPOperationId(), oInputMessage, oApplicationUri);
    				oInputMessage = oPOSTInputMessageHandler.postInputMessage();
    				
    				//parse message parts
    				parseInputMessageParts(nextMessageElement, oInputMessage);
    			}
    		}
    	}
    }
    
    public void parseOutputMessage(String outputMessageName, SOAPOperationModel oSOAPOperation)
    {
    	List<Element> listOfMessages = WSDLApplicationNode.getChildren();
    	Iterator<Element> iteratorOfMessages = listOfMessages.iterator();
    	
    	while(iteratorOfMessages.hasNext())
    	{
    		Element nextMessageElement = iteratorOfMessages.next();
    		
    		if(nextMessageElement.getName().equalsIgnoreCase("message"))
    		{
    			if(nextMessageElement.getAttributeValue("name").equalsIgnoreCase(outputMessageName))
    			{
    				OutputMessageModel oOutputMessage = new OutputMessageModel();
    				oOutputMessage.setName(nextMessageElement.getAttributeValue("name"));
    				
    				POSTOutputMessageHandler oPOSTOutputMessageHandler = new POSTOutputMessageHandler(oSOAPOperation.getSOAPOperationId(), oOutputMessage, oApplicationUri);
    				oOutputMessage = oPOSTOutputMessageHandler.postOutputMessage();
    				
    				//parse message parts
    				parseOutputMessageParts(nextMessageElement, oOutputMessage);
    			}
    		}
    	}
    }
    
    public void parseInputMessageParts(Element messageElement, InputMessageModel oInputMessage)
    {
    	List<Element> listOfMessageParameters = messageElement.getChildren();
    	Iterator<Element> iteratorOfMessageParameters = listOfMessageParameters.iterator();
    	
    	while(iteratorOfMessageParameters.hasNext())
    	{
    		Element nextMessageParameter = iteratorOfMessageParameters.next();
    		
    		if( nextMessageParameter.getName().equalsIgnoreCase("part"))
    		{
    			if(!isPartTypeDefined(nextMessageParameter))
    				parseInputMessageParameter(removeNameSpace(nextMessageParameter.getAttributeValue("element")),oInputMessage);
    			else
    			{
    				if(isPrimitiveType(nextMessageParameter.getAttributeValue("type")))
    				{
    	    			InputParameterModel oInputParameter = new InputParameterModel();
    	    			oInputParameter.setName(nextMessageParameter.getAttributeValue("name"));
    	    			oInputParameter.setType(removeNameSpace(nextMessageParameter.getAttributeValue("type")));
    	    			
    	    			POSTInputMessageInputParameterHandler oPOSTInputMessageInputParameterHandler = new POSTInputMessageInputParameterHandler(oInputMessage.getInputMessageId(),  oInputParameter, oApplicationUri);
    	    			oInputParameter = oPOSTInputMessageInputParameterHandler.postInputParameter();
    				}
    				else
    				{
    					parseInputMessageParameter(removeNameSpace(nextMessageParameter.getAttributeValue("type")),oInputMessage);
    				}
    			}
    		}
    	}
    }
    
    public void parseOutputMessageParts(Element messageElement, OutputMessageModel oOutputMessage)
    {
    	List<Element> listOfMessageParameters = messageElement.getChildren();
    	Iterator<Element> iteratorOfMessageParameters = listOfMessageParameters.iterator();
    	
    	while(iteratorOfMessageParameters.hasNext())
    	{
    		Element nextMessageParameter = iteratorOfMessageParameters.next();
    		
    		if( nextMessageParameter.getName().equalsIgnoreCase("part"))
    		{
    			if(!isPartTypeDefined(nextMessageParameter))
    				parseOutputMessageParameter(removeNameSpace(nextMessageParameter.getAttributeValue("element")),oOutputMessage);
    			else
    			{
    				if(isPrimitiveType(nextMessageParameter.getAttributeValue("type")))
    				{
    	    			OutputParameterModel oOutputParameter = new OutputParameterModel();
    	    			oOutputParameter.setName(nextMessageParameter.getAttributeValue("name"));
    	    			oOutputParameter.setType(removeNameSpace(nextMessageParameter.getAttributeValue("type")));
    	    			
    	    			POSTOutputMessageOutputParameterHandler oPOSTOutputMessageOutputParameterHandler = new POSTOutputMessageOutputParameterHandler(oOutputMessage.getOutputMessageId(),  oOutputParameter, oApplicationUri);
    	    			oOutputParameter = oPOSTOutputMessageOutputParameterHandler.postOutputParameter();
    				}
    				else
    				{
    					parseOutputMessageParameter(removeNameSpace(nextMessageParameter.getAttributeValue("type")),oOutputMessage);
    				}
    			}   	
    		}
    	}
    }
    
    public void parseInputMessageParameter(String parameterName, InputMessageModel oInputMessage)
    {
    	
    	
    	List<Element> listOfTypes = WSDLTypesSchema.getChildren();
    	Iterator<Element> iteratorOfTypes = listOfTypes.iterator();
    	
    	while(iteratorOfTypes.hasNext())
    	{
    		Element nextType = iteratorOfTypes.next();
    		
    		if( !(nextType.getName().equalsIgnoreCase("element") || nextType.getName().equalsIgnoreCase("complexType")))
    		{
    			continue;
    		}
    		
    		if(nextType.getAttributeValue("name").equalsIgnoreCase(parameterName) && (nextType.getName().equalsIgnoreCase("element") || nextType.getName().equalsIgnoreCase("complexType")))
    		{
    			InputParameterModel oInputParameter = new InputParameterModel();
    			oInputParameter.setName(nextType.getAttributeValue("name"));
    			oInputParameter.setType("complex");
    			
    			POSTInputMessageInputParameterHandler oPOSTInputMessageInputParameterHandler = new POSTInputMessageInputParameterHandler(oInputMessage.getInputMessageId(),  oInputParameter, oApplicationUri);
    			oInputParameter = oPOSTInputMessageInputParameterHandler.postInputParameter();
    			
    			Element nextComplexType = nextType;
    			if((nextType.getName().equalsIgnoreCase("element")))
    			{
    		    	List<Element> listOfElementComplexTypes = nextComplexType.getChildren();
    		    	Iterator<Element> iteratorOfElementComplexTypes = listOfElementComplexTypes.iterator();
    		    	
    		    	while(iteratorOfElementComplexTypes.hasNext())
    		    	{
    		    		Element nextElementComplexType = iteratorOfElementComplexTypes.next();
    		    		if( nextElementComplexType.getName().equalsIgnoreCase("complexType"))
    		    		{
    		    			nextComplexType = nextElementComplexType;
    		    			break;
    		    		}
    		    	}
    			}
    			
    			parseInputMessageParameterComplexTypes(nextComplexType, oInputParameter);
    		}
    	}
    }
    
    public void parseOutputMessageParameter(String parameterName, OutputMessageModel oOutputMessage)
    {
    	List<Element> listOfTypes = WSDLTypesSchema.getChildren();
    	Iterator<Element> iteratorOfTypes = listOfTypes.iterator();
    	
    	while(iteratorOfTypes.hasNext())
    	{
    		Element nextType = iteratorOfTypes.next();
    		
    		if( !(nextType.getName().equalsIgnoreCase("element") || nextType.getName().equalsIgnoreCase("complexType")))
    		{
    			continue;
    		}
    		
    		if(nextType.getAttributeValue("name").equalsIgnoreCase(parameterName) && (nextType.getName().equalsIgnoreCase("element") || nextType.getName().equalsIgnoreCase("complexType")))
    		{
    			OutputParameterModel oOutputParameter = new OutputParameterModel();
    			oOutputParameter.setName(nextType.getAttributeValue("name"));
    			oOutputParameter.setType("complex");
    			
    			POSTOutputMessageOutputParameterHandler oPOSTOutputMessageOutputParameterHandler = new POSTOutputMessageOutputParameterHandler(oOutputMessage.getOutputMessageId(),  oOutputParameter, oApplicationUri);
    			oOutputParameter = oPOSTOutputMessageOutputParameterHandler.postOutputParameter();
    			
    			Element nextComplexType = nextType;
    			if((nextType.getName().equalsIgnoreCase("element")))
    			{
    		    	List<Element> listOfElementComplexTypes = nextComplexType.getChildren();
    		    	Iterator<Element> iteratorOfElementComplexTypes = listOfElementComplexTypes.iterator();
    		    	
    		    	while(iteratorOfElementComplexTypes.hasNext())
    		    	{
    		    		Element nextElementComplexType = iteratorOfElementComplexTypes.next();
    		    		if( nextElementComplexType.getName().equalsIgnoreCase("complexType"))
    		    		{
    		    			nextComplexType = nextElementComplexType;
    		    			break;
    		    		}
    		    	}
    			}
    			
    			parseOutputMessageParameterComplexTypes(nextComplexType, oOutputParameter);
    		}
    	}
    }
    
    public void parseInputMessageParameterComplexTypes(Element complexType, InputParameterModel oInputParameter)
    {
    	List<Element> listOfComplexTypeSequences = complexType.getChildren();
    	Iterator<Element> iteratorOfComplexTypeSequences = listOfComplexTypeSequences.iterator();
    		
    	while(iteratorOfComplexTypeSequences.hasNext())
    	{
    		Element nextComplexTypeSequence = iteratorOfComplexTypeSequences.next();
    			
    		List<Element> listOfSequenceElements = nextComplexTypeSequence.getChildren();
    		Iterator<Element> iteratorOfSequenceElements = listOfSequenceElements.iterator();
    			
   			while(iteratorOfSequenceElements.hasNext())
    		{
   				Element nextSequenceElement = iteratorOfSequenceElements.next();
   				
       			InputParameterModel oInputParameterParameter = new InputParameterModel();
       			if( isTypeDefined(nextSequenceElement))
       			{
       				oInputParameterParameter.setName(nextSequenceElement.getAttributeValue("name"));
       				oInputParameterParameter.setType(removeNameSpace(nextSequenceElement.getAttributeValue("type")));
       			}
       			else //else it is referenced
       			{
       				oInputParameterParameter.setName(removeNameSpace(nextSequenceElement.getAttributeValue("ref")));
       				oInputParameterParameter.setType(removeNameSpace(nextSequenceElement.getAttributeValue("ref")));
       			}
	    		//if it is not self reference
	    		if(!oInputParameter.getType().equalsIgnoreCase(oInputParameterParameter.getType()))
	    		{
	    			POSTInputParameterInputParameterHandler oPOSTInputParameterInputParameterHandler = new POSTInputParameterInputParameterHandler(oInputParameter.getInputParameterId(),  oInputParameterParameter, oApplicationUri);
	    			oInputParameterParameter = oPOSTInputParameterInputParameterHandler.postInputParameter();
	    		}
	    		else
	    			continue;
        			
       			//if it is not primitive type keep parsing
       			if(!isPrimitiveType(oInputParameterParameter.getType()))
       			{
       		    	List<Element> listOfTypes = WSDLTypesSchema.getChildren();
       		    	Iterator<Element> iteratorOfTypes = listOfTypes.iterator();
        		    	
       		    	while(iteratorOfTypes.hasNext())
       		    	{
       		    		Element nextType = iteratorOfTypes.next();
        		    	
       		    		if( !(nextType.getName().equalsIgnoreCase("element") || nextType.getName().equalsIgnoreCase("complexType")))
       		    		{
       		    			continue;
       		    		}
       		    		
       		    		if( nextType.getAttributeValue("name").equalsIgnoreCase(oInputParameterParameter.getType()) && 
       		    				(nextType.getName().equalsIgnoreCase("element") || nextType.getName().equalsIgnoreCase("complexType")) )
       		    		{
       		    			Element nextComplexType = nextType;
       		    			if((nextType.getName().equalsIgnoreCase("element")))
       		    			{
       		    		    	List<Element> listOfElementComplexTypes = nextComplexType.getChildren();
       		    		    	Iterator<Element> iteratorOfElementComplexTypes = listOfElementComplexTypes.iterator();
       		    		    	
       		    		    	while(iteratorOfElementComplexTypes.hasNext())
       		    		    	{
       		    		    		Element nextElementComplexType = iteratorOfElementComplexTypes.next();
       		    		    		if( nextElementComplexType.getName().equalsIgnoreCase("complexType"))
       		    		    		{
       		    		    			nextComplexType = nextElementComplexType;
       		    		    			break;
       		    		    		}
       		    		    	}
       		    			}	
       		    			parseInputMessageParameterComplexTypes(nextComplexType, oInputParameterParameter);
       		    		}
       		    	}
       			}
   			}
   		}
    }
    
    public void parseOutputMessageParameterComplexTypes(Element complexType, OutputParameterModel oOutputParameter)
    {
    	List<Element> listOfComplexTypeSequences = complexType.getChildren();
    	Iterator<Element> iteratorOfComplexTypeSequences = listOfComplexTypeSequences.iterator();
    		
    	while(iteratorOfComplexTypeSequences.hasNext())
    	{
    		Element nextComplexTypeSequence = iteratorOfComplexTypeSequences.next();
    			
    		List<Element> listOfSequenceElements = nextComplexTypeSequence.getChildren();
    		Iterator<Element> iteratorOfSequenceElements = listOfSequenceElements.iterator();
    			
   			while(iteratorOfSequenceElements.hasNext())
    		{
   				Element nextSequenceElement = iteratorOfSequenceElements.next();
   				
       			OutputParameterModel oOutputParameterParameter = new OutputParameterModel();
       			if( isTypeDefined(nextSequenceElement))
       			{
       				oOutputParameterParameter.setName(nextSequenceElement.getAttributeValue("name"));
       				oOutputParameterParameter.setType(removeNameSpace(nextSequenceElement.getAttributeValue("type")));
       			}
       			else //else it is referenced
       			{
       				oOutputParameterParameter.setName(removeNameSpace(nextSequenceElement.getAttributeValue("ref")));
       				oOutputParameterParameter.setType(removeNameSpace(nextSequenceElement.getAttributeValue("ref")));
       			}
        			
	    		//if it is not self reference
	    		if(!oOutputParameter.getType().equalsIgnoreCase(oOutputParameterParameter.getType()))
	    		{
	    			POSTOutputParameterOutputParameterHandler oPOSTOutputParameterOutputParameterHandler = new POSTOutputParameterOutputParameterHandler(oOutputParameter.getOutputParameterId(),  oOutputParameterParameter, oApplicationUri);
	    			oOutputParameterParameter = oPOSTOutputParameterOutputParameterHandler.postOutputParameter();
	    		}
	    		else
	    			continue;
        			
       			//if it is not primitive type keep parsing
       			if(!isPrimitiveType(oOutputParameterParameter.getType()))
       			{
       		    	List<Element> listOfTypes = WSDLTypesSchema.getChildren();
       		    	Iterator<Element> iteratorOfTypes = listOfTypes.iterator();
        		    	
       		    	while(iteratorOfTypes.hasNext())
       		    	{
       		    		Element nextType = iteratorOfTypes.next();
        		    	
       		    		if( !(nextType.getName().equalsIgnoreCase("element") || nextType.getName().equalsIgnoreCase("complexType")))
       		    		{
       		    			continue;
       		    		}
       		    		
       		    		if( nextType.getAttributeValue("name").equalsIgnoreCase(oOutputParameterParameter.getType()) && 
       		    				(nextType.getName().equalsIgnoreCase("element") || nextType.getName().equalsIgnoreCase("complexType")) )
       		    		{
       		    			Element nextComplexType = nextType;
       		    			if((nextType.getName().equalsIgnoreCase("element")))
       		    			{
       		    		    	List<Element> listOfElementComplexTypes = nextComplexType.getChildren();
       		    		    	Iterator<Element> iteratorOfElementComplexTypes = listOfElementComplexTypes.iterator();
       		    		    	
       		    		    	while(iteratorOfElementComplexTypes.hasNext())
       		    		    	{
       		    		    		Element nextElementComplexType = iteratorOfElementComplexTypes.next();
       		    		    		if( nextElementComplexType.getName().equalsIgnoreCase("complexType"))
       		    		    		{
       		    		    			nextComplexType = nextElementComplexType;
       		    		    			break;
       		    		    		}
       		    		    	}
       		    			}
       		    			parseOutputMessageParameterComplexTypes(nextComplexType, oOutputParameterParameter);
       		    		}
       		    	}
       			}
   			}
   		}
    }
    
    public Boolean isTypeDefined(Element typeElement)
    {
    	if(typeElement.getAttributeValue("name") != null)
    		return true;
    	else
    		return false;
    }
    
    public String removeNameSpace(String attributeString)
    {	
    		return attributeString.substring(attributeString.lastIndexOf(":")+ 1);
    }
    
    public Boolean isPrimitiveType(String parameterType)
    {
    	if(parameterType == null)
    	{
    		System.out.println("Null attribute string detected!!");
    		return true;
    	}
    	
    	if(parameterType.contains(String.format("%s:",WSDLTypesSchema.getNamespacePrefix())))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    public Boolean isPartTypeDefined(Element partElement)
    {
    	if(partElement.getAttributeValue("element") == null)
    		return true;
    	else 
    		return false;
    }
}

