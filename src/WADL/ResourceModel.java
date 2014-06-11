package WADL;

import java.util.ArrayList;
import java.util.HashSet;
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

import org.hibernate.annotations.Cascade;

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
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @CollectionTable(name="resourceResourceDescription", joinColumns=@JoinColumn(name="resourceId"))
	@Column(name = "resourceDescription")
	private List<String> resourceKeywords;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="oResource")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@XmlTransient
	private Set<RESTMethodModel> setOfRESTMethod = new HashSet<RESTMethodModel>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="oResource")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@XmlTransient
	private Set<RESTParameterModel> setOfRESTParameter = new HashSet<RESTParameterModel>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="RESTServiceId")
	@XmlTransient
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

	public List<String> getResourceKeywords()
	{
		return resourceKeywords;
	}
	
	public void setResourceKeywords(List<String> resourceKeywords)
	{
		this.resourceKeywords = resourceKeywords;
	}

    public Set<RESTMethodModel> getSetOfRESTMethod()
    {
        return this.setOfRESTMethod;
    }

    public void setSetOfRESTMethodModel( Set<RESTMethodModel> setOfRESTMethod)
    {
        this.setOfRESTMethod = setOfRESTMethod;
    }

	public Set<RESTParameterModel> getSetOfRESTParameter()
    {
		return this.setOfRESTParameter;
    }

	public void setSetOfRESTParameter( Set<RESTParameterModel> setOfRESTParameter)
	{
		this.setOfRESTParameter = setOfRESTParameter;
	}
    
	public RESTServiceModel getRESTService()
	{
		return oRESTService;
	}
	
	public void setRESTService( RESTServiceModel oRESTService)
	{
		this.oRESTService = oRESTService;
	}
	
}