package WADL;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

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
		return SetOfClasses;
	}
	
	@Override
	public Set<Object> getSingletons()
	{
		return new HashSet<Object>(); //empty since we do not create any singletons
	}
}