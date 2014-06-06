package WADL;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Utilities.HibernateUtil;



 public class SQLITEController
 {
	public SQLITEController(){}
	
	//placeholder for the authenticatedUser operation
	 public AccountModel authenticatedUser(AccountModel oAccount)
	 {
			//create a new session and begin the transaction
		    Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//create the query in HQL language
			String strQuery = String.format("FROM AccountModel WHERE (AccountModel.username = %s AND AccountModel.password = %s)", oAccount.getUsername() , oAccount.getPassword());
			Query  hibernateQuery = hibernateSession.createQuery(strQuery);
			
			//retrieve the unique result, if there is a result at all
			oAccount = (AccountModel) hibernateQuery.uniqueResult();
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			//return the <AuthenticationModel> of the authenticated user, or null if authentication failed
			return oAccount;
	 }
	//placeholder for the hibernateActivities, one for every HTTPVerbActivityHandler

	 public AccountModel postAccount(AccountModel oAccount)
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
	 
	 public AccountModel getAccount(AccountModel oAccount)
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
	 
	 public AccountModel putAccount(AccountModel oAccount)
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
	 
	 public void deleteAccount(AccountModel oAccount)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database 
			hibernateSession.delete(oAccount);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	 }
	 
	 public RESTServiceModel postRESTService(RESTServiceModel oRESTService)
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
	 
	 public RESTServiceModel getRESTService(RESTServiceModel oRESTService)
	 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oRESTService = (RESTServiceModel) hibernateSession.get(RESTServiceModel.class, oRESTService.getRESTServiceId());
			Hibernate.initialize(oRESTService.getWsKeywords());
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oRESTService;
	 }
	 
	 public void deleteRESTService(RESTServiceModel oRESTService)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database 
			hibernateSession.delete(oRESTService);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	 }
	 
	 public RESTServiceModel putRESTService(RESTServiceModel oRESTService)
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
	 
	 public ResourceModel postResource(ResourceModel oResource)
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
	 
	 public ResourceModel getResource(ResourceModel oResource)
	 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oResource = (ResourceModel) hibernateSession.get(ResourceModel.class, oResource.getResourceId());
			Hibernate.initialize(oResource.getResourceKeywords());
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oResource;
	 }
	 
	 public ResourceModel putResource(ResourceModel oResource)
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
	 
	 public void deleteResource(ResourceModel oResource)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database 
			hibernateSession.delete(oResource);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	 }
	 
	 public RESTMethodModel postRESTMethod(RESTMethodModel oRESTMethod)
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
	 
	 public RESTMethodModel getRESTMethod(RESTMethodModel oRESTMethod)
	 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oRESTMethod = (RESTMethodModel) hibernateSession.get(RESTMethodModel.class, oRESTMethod.getRESTMethodId());
			Hibernate.initialize(oRESTMethod.getMethodKeywords());

			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oRESTMethod;
	 }
	 
	 public RESTMethodModel putRESTMethod(RESTMethodModel oRESTMethod)
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
	 
	 public void deleteRESTMethod(RESTMethodModel oRESTMethod)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database 
			hibernateSession.delete(oRESTMethod);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	 }
	 
	 public RESTParameterModel postRESTParameter(RESTParameterModel oRESTParameter)
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
	 
	 public RESTParameterModel getRESTParameter(RESTParameterModel oRESTParameter)
	 {
	 		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();
			
			//find the  <ResourceName> in the database 
			oRESTParameter = (RESTParameterModel) hibernateSession.get(RESTParameterModel.class, oRESTParameter.getRESTParameterId());
			Hibernate.initialize(oRESTParameter.getParameterValueOption());

			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oRESTParameter;
	 }
	 
	 public RESTParameterModel putRESTParameter(RESTParameterModel oRESTParameter)
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
	 
	 
	 public void deleteRESTParameter(RESTParameterModel oRESTParameter)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database 
			hibernateSession.delete(oRESTParameter);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	 }
 }