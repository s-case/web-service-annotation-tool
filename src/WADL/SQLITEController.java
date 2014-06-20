package WADL;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
	 
	 public AccountModel deleteAccount(AccountModel oAccount)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database 
	        oAccount = (AccountModel) hibernateSession.get(AccountModel.class, oAccount.getAccountId());
	        oAccount.deleteAllCollections(hibernateSession);
			
			hibernateSession.delete(oAccount);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			return oAccount;
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

			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			
			return oRESTService;
	 }
	 
	 public RESTServiceModel deleteRESTService(RESTServiceModel oRESTService)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database 
	        oRESTService = (RESTServiceModel) hibernateSession.get(RESTServiceModel.class, oRESTService.getRESTServiceId());
	        oRESTService.deleteAllCollections(hibernateSession);
			hibernateSession.delete(oRESTService);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			return oRESTService;
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
	 
	 public ResourceModel deleteResource(ResourceModel oResource)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        oResource = (ResourceModel) hibernateSession.get(ResourceModel.class, oResource.getResourceId());
	        oResource.deleteAllCollections(hibernateSession);
			hibernateSession.delete(oResource);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			return oResource;
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
	 
	 public RESTMethodModel deleteRESTMethod(RESTMethodModel oRESTMethod)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database 
	        oRESTMethod = (RESTMethodModel) hibernateSession.get(RESTMethodModel.class, oRESTMethod.getRESTMethodId());
	        oRESTMethod.deleteAllCollections(hibernateSession);
			hibernateSession.delete(oRESTMethod);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			return oRESTMethod;
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
	 
	 
	 public RESTParameterModel deleteRESTParameter(RESTParameterModel oRESTParameter)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database 
	        oRESTParameter = (RESTParameterModel) hibernateSession.get(RESTParameterModel.class, oRESTParameter.getRESTParameterId());
	        oRESTParameter.deleteAllCollections(hibernateSession);
			hibernateSession.delete(oRESTParameter);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
			return oRESTParameter;
	 }
	 
	 public RESTServiceModel getResourceList(RESTServiceModel oRESTService)
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
	 
	 public AccountModel getRESTServiceList(AccountModel oAccount)
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
	 
	 public ResourceModel getRESTMethodList(ResourceModel oResource)
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
	 
	 public ResourceModel getResourceRESTParameterList(ResourceModel oResource)
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
	 
	 public RESTMethodModel getRESTMethodRESTParameterList(RESTMethodModel oRESTMethod)
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
	 
	 public InputMessageModel deleteInputMessage(InputMessageModel oInputMessage)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        //retrieve the whole resource

	        oInputMessage = (InputMessageModel) hibernateSession.get(InputMessageModel.class, oInputMessage.getInputMessageId());
	        oInputMessage.deleteAllCollections(hibernateSession);

	        hibernateSession.delete(oInputMessage);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	        return oInputMessage;
	 }
	 
	 public InputParameterModel deleteInputParameter(InputParameterModel oInputParameter)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        //retrieve the whole resource

	        oInputParameter = (InputParameterModel) hibernateSession.get(InputParameterModel.class, oInputParameter.getInputParameterId());
	        oInputParameter.deleteAllCollections(hibernateSession);

	        hibernateSession.delete(oInputParameter);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	        return oInputParameter;
	 }
	 
	 public OutputMessageModel deleteOutputMessage(OutputMessageModel oOutputMessage)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        //retrieve the whole resource

	        oOutputMessage = (OutputMessageModel) hibernateSession.get(OutputMessageModel.class, oOutputMessage.getOutputMessageId());
	        oOutputMessage.deleteAllCollections(hibernateSession);

	        hibernateSession.delete(oOutputMessage);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	        return oOutputMessage;
	 }
	 
	 public OutputParameterModel deleteOutputParameter(OutputParameterModel oOutputParameter)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        //retrieve the whole resource

	        oOutputParameter = (OutputParameterModel) hibernateSession.get(OutputParameterModel.class, oOutputParameter.getOutputParameterId());
	        oOutputParameter.deleteAllCollections(hibernateSession);

	        hibernateSession.delete(oOutputParameter);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	        return oOutputParameter;
	 }
	 
	 public SOAPOperationModel deleteSOAPOperation(SOAPOperationModel oSOAPOperation)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        //retrieve the whole resource

	        oSOAPOperation = (SOAPOperationModel) hibernateSession.get(SOAPOperationModel.class, oSOAPOperation.getSOAPOperationId());
	        oSOAPOperation.deleteAllCollections(hibernateSession);

	        hibernateSession.delete(oSOAPOperation);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	        return oSOAPOperation;
	 }
	 
	 public SOAPServiceModel deleteSOAPService(SOAPServiceModel oSOAPService)
	 {
	  		//create a new session and begin the transaction
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction hibernateTransaction = hibernateSession.beginTransaction();

			//find the  <ResourceName> in the database
	        //retrieve the whole resource

	        oSOAPService = (SOAPServiceModel) hibernateSession.get(SOAPServiceModel.class, oSOAPService.getSOAPServiceId());
	        oSOAPService.deleteAllCollections(hibernateSession);

	        hibernateSession.delete(oSOAPService);
			
			//commit and terminate the session
			hibernateTransaction.commit();
			hibernateSession.close();
	        return oSOAPService;
	 }
	 
	 public InputMessageModel getInputMessage(InputMessageModel oInputMessage)
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
	 
	 public InputParameterModel getInputParameter(InputParameterModel oInputParameter)
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
	 
	 public OutputMessageModel getOutputMessage(OutputMessageModel oOutputMessage)
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
	 
	 public OutputParameterModel getOutputParameter(OutputParameterModel oOutputParameter)
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
	 
	 public SOAPOperationModel getSOAPOperation(SOAPOperationModel oSOAPOperation)
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
	 
	 public SOAPServiceModel getSOAPService(SOAPServiceModel oSOAPService)
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
	 
	 public InputMessageModel postInputMessage(InputMessageModel oInputMessage)
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
	 
	 public InputParameterModel postInputParameter(InputParameterModel oInputParameter)
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
	 
	 public OutputMessageModel postOutputMessage(OutputMessageModel oOutputMessage)
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
	 
	 public OutputParameterModel postOutputParameter(OutputParameterModel oOutputParameter)
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
	 
	 public SOAPOperationModel postSOAPOperation(SOAPOperationModel oSOAPOperation)
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
	 
	 public SOAPServiceModel postSOAPService(SOAPServiceModel oSOAPService)
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
	 
	 public InputMessageModel putInputMessage(InputMessageModel oInputMessage)
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
	 
	 public InputParameterModel putInputParameter(InputParameterModel oInputParameter)
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
	 
	 public OutputMessageModel putOutputMessage(OutputMessageModel oOutputMessage)
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
	 
	 public OutputParameterModel putOutputParameter(OutputParameterModel oOutputParameter)
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
	 
	 public SOAPOperationModel putSOAPOperation(SOAPOperationModel oSOAPOperation)
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
	 
	 public SOAPServiceModel putSOAPService(SOAPServiceModel oSOAPService)
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
	 
	 public InputMessageModel getInputMessageInputParameterList(InputMessageModel oInputMessage)
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
	 
	 public SOAPOperationModel getInputMessageList(SOAPOperationModel oSOAPOperation)
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
	 
	 public InputParameterModel getInputParameterInputParameterList(InputParameterModel oInputParameter)
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
	 
	 public SOAPOperationModel getOutputMessageList(SOAPOperationModel oSOAPOperation)
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
	 
	 public OutputMessageModel getOutputMessageOutputParameterList(OutputMessageModel oOutputMessage)
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
	 
	 public OutputParameterModel getOutputParameterOutputParameterList(OutputParameterModel oOutputParameter)
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
	 
	 public SOAPServiceModel getSOAPOperationList(SOAPServiceModel oSOAPService)
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
	 
	 public AccountModel getSOAPServiceList(AccountModel oAccount)
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
 }