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


@XmlRootElement
@Entity
@Table(name="resource")
public class ResourceModel
{
	//properties
//	@XmlElement(name="link")
	@Transient
	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties
//	@XmlElement
	@Id
	@GeneratedValue
	@Column(name = "resourceId")
	private int resourceId;
	
//	@XmlElement
	@Column(name = "relativeUri")
	private String relativeUri;
	
//	@XmlElement
	@Column(name = "resourceName")
	private String resourceName;
	
//	@XmlElement
	@Column(name = "resourceDescription")
	private String resourceDescription;
	
//	@XmlElement
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="resourceresourceKeywords", joinColumns=@JoinColumn(name="resourceId"))
    @ForeignKey(name = "fk_resource_resourceKeywords")
	@Column(name = "resourceKeywords")
	private Set<String> resourceKeywords  = new HashSet<String>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="oResource")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Set<RESTMethodModel> setOfRESTMethod = new HashSet<RESTMethodModel>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="oResource")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Set<RESTParameterModel> setOfRESTParameter = new HashSet<RESTParameterModel>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="RESTServiceId")
	@ForeignKey(name = "fk_restservice_resource")
	private RESTServiceModel oRESTService;
	
	//operations
	//place holder for marshalModel operation

    public ResourceModel marshalModel(List<Link> linkList)
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
	
	public String getRelativeUri()
	{
		return relativeUri;
	}
	
	public void setRelativeUri(String relativeUri)
	{
		this.relativeUri = relativeUri;
	}

	public int getResourceId()
	{
		return resourceId;
	}
	
	public void setResourceId(int resourceId)
	{
		this.resourceId = resourceId;
	}

	public String getResourceName()
	{
		return resourceName;
	}
	
	public void setResourceName(String resourceName)
	{
		this.resourceName = resourceName;
	}

	public String getResourceDescription()
	{
		return resourceDescription;
	}
	
	public void setResourceDescription(String resourceDescription)
	{
		this.resourceDescription = resourceDescription;
	}

	public Set<String> getResourceKeywords()
	{
		return resourceKeywords;
	}
	
	public void setResourceKeywords(Set<String> resourceKeywords)
	{
		this.resourceKeywords = resourceKeywords;
	}
	
    public void deleteAllCollections(Session hibernateSession)
    {
        Query query = hibernateSession.createSQLQuery(String.format("DELETE FROM %s where %sId = %d","resourceresourceKeywords".toLowerCase(),"resource",this.getResourceId()));
        query.executeUpdate();

        Iterator<RESTMethodModel> setOfRESTMethodIterator = setOfRESTMethod.iterator();
        while(setOfRESTMethodIterator.hasNext())
        {
        	setOfRESTMethodIterator.next().deleteAllCollections(hibernateSession);
        }
        
        Iterator<RESTParameterModel> setOfRESTParameterIterator = setOfRESTParameter.iterator();
        while(setOfRESTParameterIterator.hasNext())
        {
        	setOfRESTParameterIterator.next().deleteAllCollections(hibernateSession);
        }
        
    }
	
	@XmlTransient
    public Set<RESTMethodModel> getSetOfRESTMethod()
    {
        return this.setOfRESTMethod;
    }
    
    public void setSetOfRESTMethod( Set<RESTMethodModel> setOfRESTMethod)
    {
        this.setOfRESTMethod = setOfRESTMethod;
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
	public RESTServiceModel getRESTService()
	{
		return oRESTService;
	}
	
	public void setRESTService( RESTServiceModel oRESTService)
	{
		this.oRESTService = oRESTService;
	}
	
}