package WADL;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

import Utilities.HibernateUtil;
import WSDL.InputMessageModel;
import WSDL.InputParameterModel;
import WSDL.OutputMessageModel;
import WSDL.OutputParameterModel;
import WSDL.SOAPOperationModel;
import WSDL.SOAPServiceModel;



 public class SQLITEController
 {
	public SQLITEController(){}
	
	//placeholder for the authenticatedUser operation
	 public AccountModel authenticatedUser(AccountModel oAccount)
	 {
		 try
		 {
			//create a new session and begin the transaction
		    Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//create the query in HQL language
			String strQuery = String.format("FROM AccountModel WHERE (username = '%s' AND password = '%s')", oAccount.getUsername() , oAccount.getPassword());
			Query  hibernateQuery = hibernateSession.createQuery(strQuery);
			
			oAccount = null;
			
			//retrieve the unique result, if there is a result at all
			oAccount = (AccountModel) hibernateQuery.uniqueResult();
			
			if(oAccount == null)
			{
	    		throw new WebApplicationException(Response.Status.UNAUTHORIZED);
			}
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			//return the <AuthenticationModel> of the authenticated user, or null if authentication failed
			return oAccount;
		}
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	//placeholder for the hibernateActivities, one for every HTTPVerbActivityHandler

	 public AccountModel postAccount(AccountModel oAccount)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//insert the new <ResourceName> to database 
			int accountId = (Integer) hibernateSession.save(oAccount);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			//returh the <accountModelName> with updated <accountModelName>Id
			oAccount.setAccountId(accountId);
			return oAccount;
		}
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 } 
	 
	 public AccountModel getAccount(AccountModel oAccount)
	 {
		 try
		 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oAccount = (AccountModel) hibernateSession.get(AccountModel.class, oAccount.getAccountId());

			if(oAccount == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oAccount;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public AccountModel putAccount(AccountModel oAccount)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//update the  <ResourceName> to database 
			hibernateSession.update(oAccount);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oAccount;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public AccountModel deleteAccount(AccountModel oAccount)
	 {
		 try
		 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database 
	        oAccount = (AccountModel) hibernateSession.get(AccountModel.class, oAccount.getAccountId());
	        
			if(oAccount == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
	        
	        oAccount.deleteAllCollections(hibernateSession);
			
			hibernateSession.delete(oAccount);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			return oAccount;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public RESTServiceModel postRESTService(RESTServiceModel oRESTService)
	 {
			try
			{
				//create a new session and begin the transaction
				Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
				Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
				//insert the new <ResourceName> to database
				int RESTServiceId = (Integer) hibernateSession.save(oRESTService);

				//commit and terminate the session
				hibernateTransaction.commit();
				hibernateSession.close();

			
				//return the <accountModelName> with updated <accountModelName>Id
				oRESTService.setRESTServiceId(RESTServiceId);
				return oRESTService;
			}
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 } 
	 
	 public RESTServiceModel getRESTService(RESTServiceModel oRESTService)
	 {
		 try
		 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oRESTService = (RESTServiceModel) hibernateSession.get(RESTServiceModel.class, oRESTService.getRESTServiceId());

			if(oRESTService == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oRESTService;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public RESTServiceModel deleteRESTService(RESTServiceModel oRESTService)
	 {
		 try
		 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database 
	        oRESTService = (RESTServiceModel) hibernateSession.get(RESTServiceModel.class, oRESTService.getRESTServiceId());

			if(oRESTService == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
	        
	        oRESTService.deleteAllCollections(hibernateSession);
			hibernateSession.delete(oRESTService);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			return oRESTService;
		}
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public RESTServiceModel putRESTService(RESTServiceModel oRESTService)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//update the  <ResourceName> to database 
			hibernateSession.update(oRESTService);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oRESTService;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public ResourceModel postResource(ResourceModel oResource)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//insert the new <ResourceName> to database 
			int resourceId = (Integer) hibernateSession.save(oResource);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			//return the <accountModelName> with updated <accountModelName>Id
			oResource.setResourceId(resourceId);
			return oResource;
		 }
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 } 
	 
	 public ResourceModel getResource(ResourceModel oResource)
	 {
		 try
		 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oResource = (ResourceModel) hibernateSession.get(ResourceModel.class, oResource.getResourceId());

			if(oResource == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oResource;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public ResourceModel putResource(ResourceModel oResource)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//update the  <ResourceName> to database 
			hibernateSession.update(oResource);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oResource;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public ResourceModel deleteResource(ResourceModel oResource)
	 {
		 try
		 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        oResource = (ResourceModel) hibernateSession.get(ResourceModel.class, oResource.getResourceId());
	        
			if(oResource == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
	        
	        oResource.deleteAllCollections(hibernateSession);
			hibernateSession.delete(oResource);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			return oResource;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public RESTMethodModel postRESTMethod(RESTMethodModel oRESTMethod)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//insert the new <ResourceName> to database 
			int RESTMethodId = (Integer) hibernateSession.save(oRESTMethod);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			//return the <accountModelName> with updated <accountModelName>Id
			oRESTMethod.setRESTMethodId(RESTMethodId);
			return oRESTMethod;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 } 
	 
	 public RESTMethodModel getRESTMethod(RESTMethodModel oRESTMethod)
	 {
		 try
		 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oRESTMethod = (RESTMethodModel) hibernateSession.get(RESTMethodModel.class, oRESTMethod.getRESTMethodId());

			if(oRESTMethod == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oRESTMethod;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public RESTMethodModel putRESTMethod(RESTMethodModel oRESTMethod)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//update the  <ResourceName> to database 
			hibernateSession.update(oRESTMethod);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oRESTMethod;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public RESTMethodModel deleteRESTMethod(RESTMethodModel oRESTMethod)
	 {
		 try
		 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database 
	        oRESTMethod = (RESTMethodModel) hibernateSession.get(RESTMethodModel.class, oRESTMethod.getRESTMethodId());
	        
			if(oRESTMethod == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
	        
	        oRESTMethod.deleteAllCollections(hibernateSession);
			hibernateSession.delete(oRESTMethod);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			return oRESTMethod;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public RESTParameterModel postRESTParameter(RESTParameterModel oRESTParameter)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//insert the new <ResourceName> to database 
			int RESTParameterId = (Integer) hibernateSession.save(oRESTParameter);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			//return the <accountModelName> with updated <accountModelName>Id
			oRESTParameter.setRESTParameterId(RESTParameterId);
			return oRESTParameter;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 } 
	 
	 public RESTParameterModel getRESTParameter(RESTParameterModel oRESTParameter)
	 {
		 try
		 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oRESTParameter = (RESTParameterModel) hibernateSession.get(RESTParameterModel.class, oRESTParameter.getRESTParameterId());

			if(oRESTParameter == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oRESTParameter;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public RESTParameterModel putRESTParameter(RESTParameterModel oRESTParameter)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//update the  <ResourceName> to database 
			hibernateSession.update(oRESTParameter);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oRESTParameter;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 
	 public RESTParameterModel deleteRESTParameter(RESTParameterModel oRESTParameter)
	 {
		 try
		 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database 
	        oRESTParameter = (RESTParameterModel) hibernateSession.get(RESTParameterModel.class, oRESTParameter.getRESTParameterId());

			if(oRESTParameter == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
	        
	        oRESTParameter.deleteAllCollections(hibernateSession);
			hibernateSession.delete(oRESTParameter);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			return oRESTParameter;
		 }		
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public RESTServiceModel getResourceList(RESTServiceModel oRESTService)
	 {
		 try
		 {
			 //create a new session and begin the transaction
			 Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			 Transaction hibernateTransaction = hibernateSession.beginTransaction();

			 //find the  <ResourceName> in the database
			 oRESTService = (RESTServiceModel) hibernateSession.get(RESTServiceModel.class, oRESTService.getRESTServiceId());

			 //commit and terminate the session
			 hibernateTransaction.commit();
			 hibernateSession.close();

			 return oRESTService;
		 }
		catch (HibernateException exception)
		{
			System.out.println(exception.getCause());

			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.BAD_REQUEST);
			builder.entity(String.format("%s",exception.getCause()));
			Response response = builder.build();
			throw new WebApplicationException(response);
		}
	 }
	 
	 public AccountModel getRESTServiceList(AccountModel oAccount)
	 {
		 try
		 {
			 //create a new session and begin the transaction
			 Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			 Transaction hibernateTransaction = hibernateSession.beginTransaction();

			 //find the  <ResourceName> in the database
			 oAccount = (AccountModel) hibernateSession.get(AccountModel.class, oAccount.getAccountId());

			 //commit and terminate the session
			 hibernateTransaction.commit();
			 hibernateSession.close();

			 return oAccount;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public ResourceModel getRESTMethodList(ResourceModel oResource)
	 {
		 try
		 {
			 //create a new session and begin the transaction
			 Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			 Transaction hibernateTransaction = hibernateSession.beginTransaction();

			 //find the  <ResourceName> in the database
			 oResource = (ResourceModel) hibernateSession.get(ResourceModel.class, oResource.getResourceId());

			 //commit and terminate the session
			 hibernateTransaction.commit();
			 hibernateSession.close();

			 return oResource;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public ResourceModel getResourceRESTParameterList(ResourceModel oResource)
	 {
		 try
		 {
			 //create a new session and begin the transaction
			 Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			 Transaction hibernateTransaction = hibernateSession.beginTransaction();

			 //find the  <ResourceName> in the database
			 oResource = (ResourceModel) hibernateSession.get(ResourceModel.class, oResource.getResourceId());

			 //commit and terminate the session
			 hibernateTransaction.commit();
			 hibernateSession.close();

			 return oResource;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public RESTMethodModel getRESTMethodRESTParameterList(RESTMethodModel oRESTMethod)
	 {
		 try
		 {
			 //create a new session and begin the transaction
			 Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			 Transaction hibernateTransaction = hibernateSession.beginTransaction();

			 //find the  <ResourceName> in the database
			 oRESTMethod = (RESTMethodModel) hibernateSession.get(RESTMethodModel.class, oRESTMethod.getRESTMethodId());

			 //commit and terminate the session
			 hibernateTransaction.commit();
			 hibernateSession.close();

			 return oRESTMethod;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public InputMessageModel deleteInputMessage(InputMessageModel oInputMessage)
	 {
		 try
		 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        //retrieve the whole resource

	        oInputMessage = (InputMessageModel) hibernateSession.get(InputMessageModel.class, oInputMessage.getInputMessageId());

			if(oInputMessage == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
	        
	        oInputMessage.deleteAllCollections(hibernateSession);

	        hibernateSession.delete(oInputMessage);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	        return oInputMessage;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public InputParameterModel deleteInputParameter(InputParameterModel oInputParameter)
	 {
		 try
		 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        //retrieve the whole resource

	        oInputParameter = (InputParameterModel) hibernateSession.get(InputParameterModel.class, oInputParameter.getInputParameterId());

			if(oInputParameter == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
	        
	        oInputParameter.deleteAllCollections(hibernateSession);

	        hibernateSession.delete(oInputParameter);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	        return oInputParameter;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public OutputMessageModel deleteOutputMessage(OutputMessageModel oOutputMessage)
	 {
		 try
		 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        //retrieve the whole resource

	        oOutputMessage = (OutputMessageModel) hibernateSession.get(OutputMessageModel.class, oOutputMessage.getOutputMessageId());

			if(oOutputMessage == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
	        
	        oOutputMessage.deleteAllCollections(hibernateSession);

	        hibernateSession.delete(oOutputMessage);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	        return oOutputMessage;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public OutputParameterModel deleteOutputParameter(OutputParameterModel oOutputParameter)
	 {
		 try
		 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        //retrieve the whole resource

	        oOutputParameter = (OutputParameterModel) hibernateSession.get(OutputParameterModel.class, oOutputParameter.getOutputParameterId());

			if(oOutputParameter == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
	        
	        oOutputParameter.deleteAllCollections(hibernateSession);

	        hibernateSession.delete(oOutputParameter);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	        return oOutputParameter;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public SOAPOperationModel deleteSOAPOperation(SOAPOperationModel oSOAPOperation)
	 {
		 try
		 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        //retrieve the whole resource

	        oSOAPOperation = (SOAPOperationModel) hibernateSession.get(SOAPOperationModel.class, oSOAPOperation.getSOAPOperationId());
	
			if(oSOAPOperation == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
	        
	        oSOAPOperation.deleteAllCollections(hibernateSession);

	        hibernateSession.delete(oSOAPOperation);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	        return oSOAPOperation;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public SOAPServiceModel deleteSOAPService(SOAPServiceModel oSOAPService)
	 {
		 try
		 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        //retrieve the whole resource

	        oSOAPService = (SOAPServiceModel) hibernateSession.get(SOAPServiceModel.class, oSOAPService.getSOAPServiceId());
	
			if(oSOAPService == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
	        
	        oSOAPService.deleteAllCollections(hibernateSession);

	        hibernateSession.delete(oSOAPService);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	        return oSOAPService;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public InputMessageModel getInputMessage(InputMessageModel oInputMessage)
	 {
		 try
		 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oInputMessage = (InputMessageModel) hibernateSession.get(InputMessageModel.class, oInputMessage.getInputMessageId());

			if(oInputMessage == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oInputMessage;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public InputParameterModel getInputParameter(InputParameterModel oInputParameter)
	 {
		 try
		 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oInputParameter = (InputParameterModel) hibernateSession.get(InputParameterModel.class, oInputParameter.getInputParameterId());

			if(oInputParameter == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oInputParameter;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public OutputMessageModel getOutputMessage(OutputMessageModel oOutputMessage)
	 {
		 try
		 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oOutputMessage = (OutputMessageModel) hibernateSession.get(OutputMessageModel.class, oOutputMessage.getOutputMessageId());

			if(oOutputMessage == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oOutputMessage;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public OutputParameterModel getOutputParameter(OutputParameterModel oOutputParameter)
	 {
		 try
		 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oOutputParameter = (OutputParameterModel) hibernateSession.get(OutputParameterModel.class, oOutputParameter.getOutputParameterId());

			if(oOutputParameter == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oOutputParameter;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public SOAPOperationModel getSOAPOperation(SOAPOperationModel oSOAPOperation)
	 {
		 try
		 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oSOAPOperation = (SOAPOperationModel) hibernateSession.get(SOAPOperationModel.class, oSOAPOperation.getSOAPOperationId());

			if(oSOAPOperation == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oSOAPOperation;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public SOAPServiceModel getSOAPService(SOAPServiceModel oSOAPService)
	 {
		 try
		 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oSOAPService = (SOAPServiceModel) hibernateSession.get(SOAPServiceModel.class, oSOAPService.getSOAPServiceId());

			if(oSOAPService == null)
			{
	    		throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oSOAPService;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public InputMessageModel postInputMessage(InputMessageModel oInputMessage)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//insert the new <ResourceName> to database 
			int InputMessageId = (Integer) hibernateSession.save(oInputMessage);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			//return the <accountModelName> with updated <accountModelName>Id
			oInputMessage.setInputMessageId(InputMessageId);
			return oInputMessage;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public InputParameterModel postInputParameter(InputParameterModel oInputParameter)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//insert the new <ResourceName> to database 
			int InputParameterId = (Integer) hibernateSession.save(oInputParameter);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			//return the <accountModelName> with updated <accountModelName>Id
			oInputParameter.setInputParameterId(InputParameterId);
			return oInputParameter;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public OutputMessageModel postOutputMessage(OutputMessageModel oOutputMessage)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//insert the new <ResourceName> to database 
			int OutputMessageId = (Integer) hibernateSession.save(oOutputMessage);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			//return the <accountModelName> with updated <accountModelName>Id
			oOutputMessage.setOutputMessageId(OutputMessageId);
			return oOutputMessage;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public OutputParameterModel postOutputParameter(OutputParameterModel oOutputParameter)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//insert the new <ResourceName> to database 
			int OutputParameterId = (Integer) hibernateSession.save(oOutputParameter);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			//return the <accountModelName> with updated <accountModelName>Id
			oOutputParameter.setOutputParameterId(OutputParameterId);
			return oOutputParameter;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public SOAPOperationModel postSOAPOperation(SOAPOperationModel oSOAPOperation)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//insert the new <ResourceName> to database 
			int SOAPOperationId = (Integer) hibernateSession.save(oSOAPOperation);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			//return the <accountModelName> with updated <accountModelName>Id
			oSOAPOperation.setSOAPOperationId(SOAPOperationId);
			return oSOAPOperation;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public SOAPServiceModel postSOAPService(SOAPServiceModel oSOAPService)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//insert the new <ResourceName> to database 
			int SOAPServiceId = (Integer) hibernateSession.save(oSOAPService);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			//return the <accountModelName> with updated <accountModelName>Id
			oSOAPService.setSOAPServiceId(SOAPServiceId);
			return oSOAPService;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public InputMessageModel putInputMessage(InputMessageModel oInputMessage)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//update the  <ResourceName> to database 
			hibernateSession.update(oInputMessage);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oInputMessage;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public InputParameterModel putInputParameter(InputParameterModel oInputParameter)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//update the  <ResourceName> to database 
			hibernateSession.update(oInputParameter);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oInputParameter;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public OutputMessageModel putOutputMessage(OutputMessageModel oOutputMessage)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//update the  <ResourceName> to database 
			hibernateSession.update(oOutputMessage);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oOutputMessage;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public OutputParameterModel putOutputParameter(OutputParameterModel oOutputParameter)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//update the  <ResourceName> to database 
			hibernateSession.update(oOutputParameter);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oOutputParameter;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public SOAPOperationModel putSOAPOperation(SOAPOperationModel oSOAPOperation)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//update the  <ResourceName> to database 
			hibernateSession.update(oSOAPOperation);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oSOAPOperation;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public SOAPServiceModel putSOAPService(SOAPServiceModel oSOAPService)
	 {
		 try
		 {
			//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//update the  <ResourceName> to database 
			hibernateSession.update(oSOAPService);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oSOAPService;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public InputMessageModel getInputMessageInputParameterList(InputMessageModel oInputMessage)
	 {
		 try
		 {
			 //create a new session and begin the transaction
			 Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			 Transaction hibernateTransaction = hibernateSession.beginTransaction();

			 //find the  <ResourceName> in the database
			 oInputMessage = (InputMessageModel) hibernateSession.get(InputMessageModel.class, oInputMessage.getInputMessageId());

			 //commit and terminate the session
			 hibernateTransaction.commit();
			 hibernateSession.close();

			 return oInputMessage;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public SOAPOperationModel getInputMessageList(SOAPOperationModel oSOAPOperation)
	 {
		 try
		 {
			 //create a new session and begin the transaction
			 Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			 Transaction hibernateTransaction = hibernateSession.beginTransaction();

			 //find the  <ResourceName> in the database
			 oSOAPOperation = (SOAPOperationModel) hibernateSession.get(SOAPOperationModel.class, oSOAPOperation.getSOAPOperationId());

			 //commit and terminate the session
			 hibernateTransaction.commit();
			 hibernateSession.close();

			 return oSOAPOperation;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public InputParameterModel getInputParameterInputParameterList(InputParameterModel oInputParameter)
	 {
		 try
		 {
			 //create a new session and begin the transaction
			 Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			 Transaction hibernateTransaction = hibernateSession.beginTransaction();

			 //find the  <ResourceName> in the database
			 oInputParameter = (InputParameterModel) hibernateSession.get(InputParameterModel.class, oInputParameter.getInputParameterId());

			 //commit and terminate the session
			 hibernateTransaction.commit();
			 hibernateSession.close();

			 return oInputParameter;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public SOAPOperationModel getOutputMessageList(SOAPOperationModel oSOAPOperation)
	 {
		 try
		 {
			 //create a new session and begin the transaction
			 Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			 Transaction hibernateTransaction = hibernateSession.beginTransaction();

			 //find the  <ResourceName> in the database
			 oSOAPOperation = (SOAPOperationModel) hibernateSession.get(SOAPOperationModel.class, oSOAPOperation.getSOAPOperationId());

			 //commit and terminate the session
			 hibernateTransaction.commit();
			 hibernateSession.close();

			 return oSOAPOperation;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public OutputMessageModel getOutputMessageOutputParameterList(OutputMessageModel oOutputMessage)
	 {
		 try
		 {
			 //create a new session and begin the transaction
			 Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			 Transaction hibernateTransaction = hibernateSession.beginTransaction();

			 //find the  <ResourceName> in the database
			 oOutputMessage = (OutputMessageModel) hibernateSession.get(OutputMessageModel.class, oOutputMessage.getOutputMessageId());

			 //commit and terminate the session
			 hibernateTransaction.commit();
			 hibernateSession.close();

			 return oOutputMessage;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public OutputParameterModel getOutputParameterOutputParameterList(OutputParameterModel oOutputParameter)
	 {
		 try
		 {
			 //create a new session and begin the transaction
			 Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			 Transaction hibernateTransaction = hibernateSession.beginTransaction();

			 //find the  <ResourceName> in the database
			 oOutputParameter = (OutputParameterModel) hibernateSession.get(OutputParameterModel.class, oOutputParameter.getOutputParameterId());

			 //commit and terminate the session
			 hibernateTransaction.commit();
			 hibernateSession.close();

			 return oOutputParameter;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public SOAPServiceModel getSOAPOperationList(SOAPServiceModel oSOAPService)
	 {
		 try
		 {
			 //create a new session and begin the transaction
			 Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			 Transaction hibernateTransaction = hibernateSession.beginTransaction();

			 //find the  <ResourceName> in the database
			 oSOAPService = (SOAPServiceModel) hibernateSession.get(SOAPServiceModel.class, oSOAPService.getSOAPServiceId());

			 //commit and terminate the session
			 hibernateTransaction.commit();
			 hibernateSession.close();

			 return oSOAPService;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
	 
	 public AccountModel getSOAPServiceList(AccountModel oAccount)
	 {
		 try
		 {
			 //create a new session and begin the transaction
			 Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			 Transaction hibernateTransaction = hibernateSession.beginTransaction();

			 //find the  <ResourceName> in the database
			 oAccount = (AccountModel) hibernateSession.get(AccountModel.class, oAccount.getAccountId());

			 //commit and terminate the session
			 hibernateTransaction.commit();
			 hibernateSession.close();

			 return oAccount;
		 }
			catch (HibernateException exception)
			{
				System.out.println(exception.getCause());

				ResponseBuilderImpl builder = new ResponseBuilderImpl();
				builder.status(Response.Status.BAD_REQUEST);
				builder.entity(String.format("%s",exception.getCause()));
				Response response = builder.build();
				throw new WebApplicationException(response);
			}
	 }
 }