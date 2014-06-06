package WADL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.*;

@XmlRootElement
@Entity
@Table(name="RESTServiceModel")
public class RESTServiceModel
{
	//properties
//	@XmlElement(name="link")
	@Transient
	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties
//	@XmlElement
	@Id
	@GeneratedValue
	@Column(name = "RESTServiceId")
	private Integer RESTServiceId;
	
//	@XmlElement
	@Column(name = "wsProvider")
	private String wsProvider;
	
//	@XmlElement
	@Column(name = "baseUri")
	private String baseUri;
	
//	@XmlElement
	@Column(name = "wsName")
	private String wsName;
	
//	@XmlElement
	@Column(name = "wsDescription")
	private String wsDescription;
	
//	@XmlElement
	@ElementCollection
	@CollectionTable(name="RESTServiceWsKeywords", joinColumns=@JoinColumn(name="RESTServiceId"))
	@Column(name="wsKeywords")
	private List<String> wsKeywords; //TODO UNCOMMENT THIS ONE
	
	@OneToMany(mappedBy="oRESTService")
	@XmlTransient
	private Set<ResourceModel> setOfResource = new HashSet<ResourceModel>();
	
	@ManyToOne
	@JoinColumn(name="accountId")
	@XmlTransient
	private AccountModel oAccount;

	//operations
	//place holder for marshalModel operation

 public RESTServiceModel marshalModel(List<Link> linkList)
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
	
	public Integer getRESTServiceId()
	{
		return RESTServiceId;
	}
	
	public void setRESTServiceId(Integer RESTServiceId)
	{
		this.RESTServiceId = RESTServiceId;
	}

	public String getWsProvider()
	{
		return wsProvider;
	}
	
	public void setWsProvider(String wsProvider)
	{
		this.wsProvider = wsProvider;
	}

	public String getBaseUri()
	{
		return baseUri;
	}
	
	public void setBaseUri(String baseUri)
	{
		this.baseUri = baseUri;
	}

	public String getWsName()
	{
		return wsName;
	}
	
	public void setWsName(String wsName)
	{
		this.wsName = wsName;
	}

	public String getWsDescription()
	{
		return wsDescription;
	}
	
	public void setWsDescription(String wsDescription)
	{
		this.wsDescription = wsDescription;
	}


	public List<String> getWsKeywords()
	{
		return wsKeywords;
	}
	
	public void setWsKeywords(List<String> wsKeywords)
	{
		this.wsKeywords = wsKeywords;
	}

    public Set<ResourceModel> getSetOfResource()
    {
        return this.setOfResource;
    }

    public void setSetOfResource( Set<ResourceModel> setOfResource)
    {
        this.setOfResource = setOfResource;
    }

	
	public AccountModel getAccount()
	{
		return oAccount;
	}
	
	public void setAccount( AccountModel oAccount)
	{
		this.oAccount = oAccount;
	}

}

