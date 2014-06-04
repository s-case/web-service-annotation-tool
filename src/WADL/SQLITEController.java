package WADL;

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
			Integer accountId = (Integer) hibernateSession.save(oAccount);
			
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
			Integer RESTServiceId = (Integer) hibernateSession.save(oRESTService);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			//returh the <accountModelName> with updated <accountModelName>Id
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
 }