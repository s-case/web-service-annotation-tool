package WADL;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import WSDL.InputMessageController;
import WSDL.InputParameterController;
import WSDL.OutputMessageController;
import WSDL.OutputParameterController;
import WSDL.SOAPOperationController;
import WSDL.SOAPServiceController;
import WSDL.WSDLParseController;

@ApplicationPath("/api/")
public class JAXRSServicePublisher extends Application
{	
	public JAXRSServicePublisher() {	}
	
	@Override
	public Set<Class<?>> getClasses()
	{
		HashSet<Class<?>> SetOfClasses = new HashSet<Class<?>>(); 
		SetOfClasses.add(AccountController.class);  // add all the javaResourceControllers that way
		SetOfClasses.add(RESTServiceController.class);
		SetOfClasses.add(ResourceController.class);
		SetOfClasses.add(RESTMethodController.class);
		SetOfClasses.add(RESTParameterController.class);
		SetOfClasses.add(InputMessageController.class);
		SetOfClasses.add(InputParameterController.class);
		SetOfClasses.add(OutputMessageController.class);
		SetOfClasses.add(OutputParameterController.class);
		SetOfClasses.add(SOAPOperationController.class);
		SetOfClasses.add(SOAPServiceController.class);
		SetOfClasses.add(WADLParseController.class);
		SetOfClasses.add(WSDLParseController.class);

		return SetOfClasses;
	}
	
	@Override
	public Set<Object> getSingletons()
	{
		return new HashSet<Object>(); //empty since we do not create any singletons
	}
}