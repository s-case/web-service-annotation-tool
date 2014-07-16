package WADL;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.UriInfo;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import Utilities.PersistenceUtil;
import WSDL.InputMessageModel;
import WSDL.InputParameterModel;
import WSDL.OutputMessageModel;
import WSDL.OutputParameterModel;
import WSDL.SOAPOperationModel;
import WSDL.SOAPServiceModel;

public class GETSearchListHandler
{

    private SQLITEController oSQLITEController;
    private UriInfo		 oApplicationUri;
    private String searchKeyword;
    private String searchRESTService;
    private String searchResource;
    private String searchRESTMethod;
    private String searchRESTParameter;
    private String searchSOAPService;
    private String searchSOAPOperation;
    private String searchSOAPParameter;
    private SearchModel oSearch;

    GETSearchListHandler(String searchKeyword, String searchRESTService, String searchResource, String searchRESTMethod, String searchRESTParameter, String searchSOAPService, String searchSOAPOperation, String searchSOAPParameter, UriInfo applicationUri)
    {
    	this.searchKeyword = searchKeyword;
    	this.searchRESTService = searchRESTService;
    	this.searchResource = searchResource;
    	this.searchRESTMethod = searchRESTMethod;
    	this.searchRESTParameter = searchRESTParameter;
    	this.searchSOAPService = searchSOAPService;
    	this.searchSOAPOperation = searchSOAPOperation;
    	this.searchSOAPParameter = searchSOAPParameter;
        oSQLITEController = new SQLITEController();
        oApplicationUri = applicationUri;
        this.oSearch = new SearchModel();
    }

    public SearchModel getSearchList()
    {
        //TODO add authentication if needed

    	//start searching for the keywords
    	searchDatabase();
    
    	return oSearch;
    }
    
    public void searchDatabase()
    {    	
    	if(searchRESTService != null && searchRESTService.equalsIgnoreCase("true"))
    	{
    		addRESTServiceLinks();
    	}
    	
    	if(searchResource != null && searchResource.equalsIgnoreCase("true"))
    	{
    		addResourceLinks();
    	}
    	
    	if(searchRESTMethod != null && searchRESTMethod.equalsIgnoreCase("true"))
    	{
    		addRESTMethodLinks();
    	}
    	
    	if(searchRESTParameter != null && searchRESTParameter.equalsIgnoreCase("true"))
    	{
    		addRESTParameterLinks();
    	}
    	
    	if(searchSOAPService != null && searchSOAPService.equalsIgnoreCase("true"))
    	{
    		addSOAPServiceLinks();
    	}
    	
    	if(searchSOAPOperation !=null && searchSOAPOperation.equalsIgnoreCase("true"))
    	{
    		addSOAPOperationLinks();
    	}
    	
    	if(searchSOAPParameter != null && searchSOAPParameter.equalsIgnoreCase("true"))
    	{
    		addSOAPParameterLinks();
    	}
    }

    public void addRESTServiceLinks()
    {
    	FullTextEntityManager oFullTextEntityManager = PersistenceUtil.getFullTextEntityManager();
    	PersistenceUtil.beginEntityManagerTransaction();

    	QueryBuilder oQueryBuilder = oFullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(RESTServiceModel.class).get();
    	org.apache.lucene.search.Query oLuceneQuery = oQueryBuilder.keyword().onFields("wsProvider", "wsName", "wsDescription", "wsKeywords").matching(searchKeyword).createQuery();
    	// wrap Lucene query in a javax.persistence.Query
    	javax.persistence.Query oJpaQuery = oFullTextEntityManager.createFullTextQuery(oLuceneQuery, RESTServiceModel.class);

    	// execute search
    	List<RESTServiceModel> RESTServiceList =(List<RESTServiceModel>) oJpaQuery.getResultList();

    	Iterator<RESTServiceModel> iteratorOfRESTServiceList = RESTServiceList.iterator();
    	
    	while(iteratorOfRESTServiceList.hasNext())
    	{
    		RESTServiceModel oRESTService = iteratorOfRESTServiceList.next();
    		oSearch.getLinkList().add(new Link(String.format("%saccount/%d/RESTService/%d",oApplicationUri.getBaseUri(),oRESTService.getAccount().getAccountId() ,oRESTService.getRESTServiceId()),oRESTService.getBaseUri(),"GET","RESTService"));
    	}
    	
    	PersistenceUtil.endEntityManagerTransaction();    	
    }
    
    public void addResourceLinks()
    {
    	FullTextEntityManager oFullTextEntityManager = PersistenceUtil.getFullTextEntityManager();
    	PersistenceUtil.beginEntityManagerTransaction();

    	QueryBuilder oQueryBuilder = oFullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(ResourceModel.class).get();
    	org.apache.lucene.search.Query oLuceneQuery = oQueryBuilder.keyword().onFields("resourceName", "resourceDescription","resourceKeywords").matching(searchKeyword).createQuery();
    	// wrap Lucene query in a javax.persistence.Query
    	javax.persistence.Query oJpaQuery = oFullTextEntityManager.createFullTextQuery(oLuceneQuery, ResourceModel.class);

    	// execute search
    	List<ResourceModel> resourceList =(List<ResourceModel>) oJpaQuery.getResultList();

    	Iterator<ResourceModel> iteratorOfResourceList = resourceList.iterator();
    	
    	while(iteratorOfResourceList.hasNext())
    	{
    		ResourceModel oResource = iteratorOfResourceList.next();
    		oSearch.getLinkList().add(new Link(String.format("%saccount/%d/RESTService/%d/resource/%d",oApplicationUri.getBaseUri(),oResource.getRESTService().getAccount().getAccountId(),oResource.getRESTService().getRESTServiceId(),oResource.getResourceId()),oResource.getRelativeUri(),"GET","resource"));
    	}
    	
    	PersistenceUtil.endEntityManagerTransaction();
    }
    
    public void addRESTMethodLinks()
    {
    	FullTextEntityManager oFullTextEntityManager = PersistenceUtil.getFullTextEntityManager();
    	PersistenceUtil.beginEntityManagerTransaction();

    	QueryBuilder oQueryBuilder = oFullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(RESTMethodModel.class).get();
    	org.apache.lucene.search.Query oLuceneQuery = oQueryBuilder.keyword().onFields("methodDescription", "methodIdentifier","methodKeywords").matching(searchKeyword).createQuery();
    	// wrap Lucene query in a javax.persistence.Query
    	javax.persistence.Query oJpaQuery = oFullTextEntityManager.createFullTextQuery(oLuceneQuery, RESTMethodModel.class);

    	// execute search
    	List<RESTMethodModel> RESTMethodList =(List<RESTMethodModel>) oJpaQuery.getResultList();

    	Iterator<RESTMethodModel> iteratorOfRESTMethodList = RESTMethodList.iterator();
    	
    	while(iteratorOfRESTMethodList.hasNext())
    	{
    		RESTMethodModel oRESTMethod = iteratorOfRESTMethodList.next();
    		oSearch.getLinkList().add(new Link(String.format("%saccount/%d/RESTService/%d/resource/%d/RESTMethod/%d",oApplicationUri.getBaseUri(),oRESTMethod.getResource().getRESTService().getAccount().getAccountId(),oRESTMethod.getResource().getRESTService().getRESTServiceId(),oRESTMethod.getResource().getResourceId(),oRESTMethod.getRESTMethodId()),oRESTMethod.getMethodIdentifier(),"GET","RESTMethod"));
    	}
    	
    	PersistenceUtil.endEntityManagerTransaction();
    }
    
    public void addRESTParameterLinks()
    {
    	FullTextEntityManager oFullTextEntityManager = PersistenceUtil.getFullTextEntityManager();
    	PersistenceUtil.beginEntityManagerTransaction();

    	QueryBuilder oQueryBuilder = oFullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(RESTParameterModel.class).get();
    	org.apache.lucene.search.Query oLuceneQuery = oQueryBuilder.keyword().onFields("parameterName", "description","parameterValueOption").matching(searchKeyword).createQuery();
    	// wrap Lucene query in a javax.persistence.Query
    	javax.persistence.Query oJpaQuery = oFullTextEntityManager.createFullTextQuery(oLuceneQuery, RESTParameterModel.class);

    	// execute search
    	List<RESTParameterModel> RESTParameterList =(List<RESTParameterModel>) oJpaQuery.getResultList();

    	Iterator<RESTParameterModel> iteratorOfRESTParameterList = RESTParameterList.iterator();
    	
    	while(iteratorOfRESTParameterList.hasNext())
    	{
    		RESTParameterModel oRESTParameter = iteratorOfRESTParameterList.next();
    		if(oRESTParameter.getResource() == null) //if it is a RESTMethod parameter
    		{
    			oSearch.getLinkList().add(new Link(String.format("%smultiRESTParameter/account/%d/RESTService/%d/resource/%d/RESTMethod/%d/RESTParameter/%d",oApplicationUri.getBaseUri(),oRESTParameter.getRESTMethod().getResource().getRESTService().getAccount().getAccountId(),oRESTParameter.getRESTMethod().getResource().getRESTService().getRESTServiceId(),oRESTParameter.getRESTMethod().getResource().getResourceId(),oRESTParameter.getRESTMethod().getRESTMethodId(),oRESTParameter.getRESTParameterId()),oRESTParameter.getParameterName(),"GET","RESTParameter"));
    		}
    		else //it is a resource parameter
    		{
    			oSearch.getLinkList().add(new Link(String.format("%smultiRESTParameter/account/%d/RESTService/%d/resource/%d/RESTParameter/%d",oApplicationUri.getBaseUri(),oRESTParameter.getResource().getRESTService().getAccount().getAccountId(),oRESTParameter.getResource().getRESTService().getRESTServiceId(),oRESTParameter.getResource().getResourceId(),oRESTParameter.getRESTParameterId()),oRESTParameter.getParameterName(),"GET","RESTParameter"));
    		}
    	}
    	
    	PersistenceUtil.endEntityManagerTransaction();
    }
    
    public void addSOAPServiceLinks()
    {
    	FullTextEntityManager oFullTextEntityManager = PersistenceUtil.getFullTextEntityManager();
    	PersistenceUtil.beginEntityManagerTransaction();

    	QueryBuilder oQueryBuilder = oFullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(SOAPServiceModel.class).get();
    	org.apache.lucene.search.Query oLuceneQuery = oQueryBuilder.keyword().onFields("name", "description","keyword").matching(searchKeyword).createQuery();
    	// wrap Lucene query in a javax.persistence.Query
    	javax.persistence.Query oJpaQuery = oFullTextEntityManager.createFullTextQuery(oLuceneQuery, SOAPServiceModel.class);

    	// execute search
    	List<SOAPServiceModel> SOAPServiceList =(List<SOAPServiceModel>) oJpaQuery.getResultList();

    	Iterator<SOAPServiceModel> iteratorOfSOAPServiceList = SOAPServiceList.iterator();
    	
    	while(iteratorOfSOAPServiceList.hasNext())
    	{
    		SOAPServiceModel oSOAPService = iteratorOfSOAPServiceList.next();
    		oSearch.getLinkList().add(new Link(String.format("%saccount/%d/SOAPService/%d",oApplicationUri.getBaseUri(),oSOAPService.getAccount().getAccountId(),oSOAPService.getSOAPServiceId()),oSOAPService.getName(),"GET","SOAPService"));
    	}
    	
    	PersistenceUtil.endEntityManagerTransaction();
    }
    
    public void addSOAPOperationLinks()
    {
    	FullTextEntityManager oFullTextEntityManager = PersistenceUtil.getFullTextEntityManager();
    	PersistenceUtil.beginEntityManagerTransaction();

    	QueryBuilder oQueryBuilder = oFullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(SOAPOperationModel.class).get();
    	org.apache.lucene.search.Query oLuceneQuery = oQueryBuilder.keyword().onFields("name", "description","keyword").matching(searchKeyword).createQuery();
    	// wrap Lucene query in a javax.persistence.Query
    	javax.persistence.Query oJpaQuery = oFullTextEntityManager.createFullTextQuery(oLuceneQuery, SOAPOperationModel.class);

    	// execute search
    	List<SOAPOperationModel> SOAPOperationList =(List<SOAPOperationModel>) oJpaQuery.getResultList();

    	Iterator<SOAPOperationModel> iteratorOfSOAPOperationList = SOAPOperationList.iterator();
    	
    	while(iteratorOfSOAPOperationList.hasNext())
    	{
    		SOAPOperationModel oSOAPOperation = iteratorOfSOAPOperationList.next();
    		oSearch.getLinkList().add(new Link(String.format("%saccount/%d/SOAPService/%d/SOAPOperation/%d",oApplicationUri.getBaseUri(),oSOAPOperation.getSOAPService().getAccount().getAccountId(),oSOAPOperation.getSOAPService().getSOAPServiceId(),oSOAPOperation.getSOAPOperationId()),oSOAPOperation.getName(),"GET","SOAPOperation"));
    	}
    	
    	PersistenceUtil.endEntityManagerTransaction();
    }

    public void addSOAPParameterLinks()
    {
    	addInputParameterLinks();
    	addOutputParameterLinks();
    }
    
    public void addInputParameterLinks()
    {
    	FullTextEntityManager oFullTextEntityManager = PersistenceUtil.getFullTextEntityManager();
    	PersistenceUtil.beginEntityManagerTransaction();

    	QueryBuilder oQueryBuilder = oFullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(InputParameterModel.class).get();
    	org.apache.lucene.search.Query oLuceneQuery = oQueryBuilder.keyword().onFields("name","keyword").matching(searchKeyword).createQuery();
    	// wrap Lucene query in a javax.persistence.Query
    	javax.persistence.Query oJpaQuery = oFullTextEntityManager.createFullTextQuery(oLuceneQuery, InputParameterModel.class);

    	// execute search
    	List<InputParameterModel> InputParameterList =(List<InputParameterModel>) oJpaQuery.getResultList();

    	Iterator<InputParameterModel> iteratorOfInputParameterList = InputParameterList.iterator();
    	
    	while(iteratorOfInputParameterList.hasNext())
    	{
    		InputParameterModel oInputParameter = iteratorOfInputParameterList.next();
    		
    		if(oInputParameter.getInputMessage() != null) //if this is an input message parameter
    		{
    			oSearch.getLinkList().add(new Link(String.format("%smultiInputParameter/account/%d/SOAPService/%d/SOAPOperation/%d/InputMessage/%d/InputParameter/%d",oApplicationUri.getBaseUri(),oInputParameter.getInputMessage().getSOAPOperation().getSOAPService().getAccount().getAccountId(),oInputParameter.getInputMessage().getSOAPOperation().getSOAPService().getSOAPServiceId(),oInputParameter.getInputMessage().getSOAPOperation().getSOAPOperationId(),oInputParameter.getInputMessage().getInputMessageId(),oInputParameter.getInputParameterId()),oInputParameter.getName(),"GET","InputParameter"));
    		}
    		else//else if this is an input parameter, input parameter
    		{
    			InputMessageModel oParentInputMessage = getParentInputMessage(oInputParameter);
    			oSearch.getLinkList().add(new Link(String.format("%smultiInputParameter/account/%d/SOAPService/%d/SOAPOperation/%d/InputMessage/%d/InputParameter/%d/InputParameter/%d",oApplicationUri.getBaseUri(),oParentInputMessage.getSOAPOperation().getSOAPService().getAccount().getAccountId(), oParentInputMessage.getSOAPOperation().getSOAPService().getSOAPServiceId(),oParentInputMessage.getSOAPOperation().getSOAPOperationId(),oParentInputMessage.getInputMessageId(),oInputParameter.getInputParameter().getInputParameterId(),oInputParameter.getInputParameterId()),oInputParameter.getName(),"GET","InputParameter"));
    		}
    	}
    	
    	PersistenceUtil.endEntityManagerTransaction();
    }
    
    public void addOutputParameterLinks()
    {
    	FullTextEntityManager oFullTextEntityManager = PersistenceUtil.getFullTextEntityManager();
    	PersistenceUtil.beginEntityManagerTransaction();

    	QueryBuilder oQueryBuilder = oFullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(OutputParameterModel.class).get();
    	org.apache.lucene.search.Query oLuceneQuery = oQueryBuilder.keyword().onFields("name","keyword").matching(searchKeyword).createQuery();
    	// wrap Lucene query in a javax.persistence.Query
    	javax.persistence.Query oJpaQuery = oFullTextEntityManager.createFullTextQuery(oLuceneQuery, OutputParameterModel.class);

    	// execute search
    	List<OutputParameterModel> OutputParameterList =(List<OutputParameterModel>) oJpaQuery.getResultList();

    	Iterator<OutputParameterModel> iteratorOfOutputParameterList = OutputParameterList.iterator();
    	
    	while(iteratorOfOutputParameterList.hasNext())
    	{
    		OutputParameterModel oOutputParameter = iteratorOfOutputParameterList.next();
    		
    		if(oOutputParameter.getOutputMessage() != null) //if this is an input message parameter
    		{
    			oSearch.getLinkList().add(new Link(String.format("%smultiOutputParameter/account/%d/SOAPService/%d/SOAPOperation/%d/OutputMessage/%d/OutputParameter/%d",oApplicationUri.getBaseUri(),oOutputParameter.getOutputMessage().getSOAPOperation().getSOAPService().getAccount().getAccountId(),oOutputParameter.getOutputMessage().getSOAPOperation().getSOAPService().getSOAPServiceId(),oOutputParameter.getOutputMessage().getSOAPOperation().getSOAPOperationId(),oOutputParameter.getOutputMessage().getOutputMessageId(),oOutputParameter.getOutputParameterId()),oOutputParameter.getName(),"GET","OutputParameter"));
    		}
    		else//else if this is an input parameter, input parameter
    		{
    			OutputMessageModel oParentOutputMessage = getParentOutputMessage(oOutputParameter);
    			oSearch.getLinkList().add(new Link(String.format("%smultiOutputParameter/account/%d/SOAPService/%d/SOAPOperation/%d/OutputMessage/%d/OutputParameter/%d/OutputParameter/%d",oApplicationUri.getBaseUri(),oParentOutputMessage.getSOAPOperation().getSOAPService().getAccount().getAccountId(), oParentOutputMessage.getSOAPOperation().getSOAPService().getSOAPServiceId(),oParentOutputMessage.getSOAPOperation().getSOAPOperationId(),oParentOutputMessage.getOutputMessageId(),oOutputParameter.getOutputParameter().getOutputParameterId(),oOutputParameter.getOutputParameterId()),oOutputParameter.getName(),"GET","OutputParameter"));
    		}
    	}
    	
    	PersistenceUtil.endEntityManagerTransaction();
    }
    
    public InputMessageModel getParentInputMessage(InputParameterModel oInputParameter)
    {
    	while(oInputParameter.getInputMessage() == null) //traverse upwards the hierarchy until the parent input message is reached
    	{
    		oInputParameter = oInputParameter.getInputParameter();
    	}
    	return oInputParameter.getInputMessage();
    }
    
    public OutputMessageModel getParentOutputMessage(OutputParameterModel oOutputParameter)
    {
    	while(oOutputParameter.getOutputMessage() == null) //traverse upwards the hierarchy until the parent input message is reached
    	{
    		oOutputParameter = oOutputParameter.getOutputParameter();
    	}
    	return oOutputParameter.getOutputMessage();
    }
}