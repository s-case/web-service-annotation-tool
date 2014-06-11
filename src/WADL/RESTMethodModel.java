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
@Table(name="RESTMethod")
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
	private String methodDescription;
	
	@Id
	@GeneratedValue
	@Column(name = "RESTMethodId")
	private int RESTMethodId;
	
	@Column(name = "methodIdentifier")
	private String methodIdentifier;
	
    @ElementCollection(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @CollectionTable(name="RESTMethodMethodKeywords", joinColumns=@JoinColumn(name="RESTMethodId"))
	@Column(name = "methodKeywords")
	private List<String> methodKeywords;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="oRESTMethod")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@XmlTransient
	private Set<RESTParameterModel> setOfRESTParameter = new HashSet<RESTParameterModel>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="resourceId")
	@XmlTransient
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

	public List<String> getMethodKeywords()
	{
		return methodKeywords;
	}
	
	public void setMethodKeywords(List<String> methodKeywords)
	{
		this.methodKeywords = methodKeywords;
	}

    public Set<RESTParameterModel> getSetOfRESTParameter()
    {
        return this.setOfRESTParameter;
    }

    public void setSetOfRESTParameter( Set<RESTParameterModel> setOfRESTParameter)
    {
        this.setOfRESTParameter = setOfRESTParameter;
    }

	public ResourceModel getResource()
	{
		return oResource;
	}
	
	public void setResource( ResourceModel oResource)
	{
		this.oResource = oResource;
	}
	
}