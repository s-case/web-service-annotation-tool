package WADL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import Utilities.SetStringFieldBridge;

@XmlRootElement
@Entity
@Table(name="RESTMethod")
@Indexed
public class RESTMethodModel
{
	//properties
//	@XmlElement(name="link")
	@Transient
	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties
//	@XmlElement
	@Column(name = "HTTPVerb")
	private String HTTPVerb;
	
	@Column(name = "methodDescription")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String methodDescription;
	
	@Id
	@GeneratedValue
	@Column(name = "RESTMethodId")
	private int RESTMethodId;
	
	@Column(name = "methodIdentifier")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String methodIdentifier;
	
	@Column(name = "searchOntology")
	private String searchOntology;
	
	@Column(name = "searchConcept")
	private String searchConcept;
	
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="RESTMethodMethodKeywords", joinColumns=@JoinColumn(name="RESTMethodId"))
    @ForeignKey(name = "fk_restmethod_methodkeywords")
	@Column(name = "methodKeywords")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@FieldBridge(impl=SetStringFieldBridge.class)
	private Set<String> methodKeywords = new HashSet<String>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="oRESTMethod")
	@OnDelete(action=OnDeleteAction.CASCADE)
	@IndexedEmbedded
	private Set<RESTParameterModel> setOfRESTParameter = new HashSet<RESTParameterModel>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="resourceId")
	@ForeignKey(name = "fk_resource_restmethod")
	private ResourceModel oResource;
	
	//operations
	//place holder for marshalModel operation

    public RESTMethodModel marshalModel(List<Link> linkList)
    {
        this.linkList = linkList;
        return this;
    }
	//place holder for setLinkList operation

    public void setLinkList(List<Link> linkList)
    {
        this.linkList = linkList;
    }
	//place holder for getLinkList operation

    public List<Link> getLinkList()
    {
        return linkList;
    }

	//place holder for all setters and getters of properties
	
	public String getHTTPVerb()
	{
		return HTTPVerb;
	}
	
	public void setHTTPVerb(String HTTPVerb)
	{
		this.HTTPVerb = HTTPVerb;
	}

	public String getMethodDescription()
	{
		return methodDescription;
	}
	
	public void setMethodDescription(String methodDescription)
	{
		this.methodDescription = methodDescription;
	}

	public int getRESTMethodId()
	{
		return RESTMethodId;
	}
	
	public void setRESTMethodId(int RESTMethodId)
	{
		this.RESTMethodId = RESTMethodId;
	}

	public String getMethodIdentifier()
	{
		return methodIdentifier;
	}
	
	public void setMethodIdentifier(String methodIdentifier)
	{
		this.methodIdentifier = methodIdentifier;
	}

	public void setSearchOntology(String searchOntology)
	{
		this.searchOntology = searchOntology;
	}
	
	public String getSearchOntology()
	{
		return searchOntology;
	}
	
	public void setSearchConcept(String searchConcept)
	{
		this.searchConcept = searchConcept;
	}
	
	public String getSearchConcept()
	{
		return searchConcept;
	}
	
	public Set<String> getMethodKeywords()
	{
		return methodKeywords;
	}
	
	public void setMethodKeywords(Set<String> methodKeywords)
	{
		this.methodKeywords = methodKeywords;
	}
	
    public void deleteAllCollections(Session hibernateSession)
    {
        Query query = hibernateSession.createSQLQuery(String.format("DELETE FROM %s where %sId = %d","RESTMethodmethodKeywords".toLowerCase(),"RESTMethod",this.getRESTMethodId()));
        query.executeUpdate();

        Iterator<RESTParameterModel> RESTParameterIterator = setOfRESTParameter.iterator();
        while(RESTParameterIterator.hasNext())
        {
            RESTParameterIterator.next().deleteAllCollections(hibernateSession);
        }
    }
	
	@XmlTransient
    public Set<RESTParameterModel> getSetOfRESTParameter()
    {
        return this.setOfRESTParameter;
    }
    
    public void setSetOfRESTParameter( Set<RESTParameterModel> setOfRESTParameter)
    {
        this.setOfRESTParameter = setOfRESTParameter;
    }
    @XmlTransient
	public ResourceModel getResource()
	{
		return oResource;
	}
	
	public void setResource( ResourceModel oResource)
	{
		this.oResource = oResource;
	}
	
}