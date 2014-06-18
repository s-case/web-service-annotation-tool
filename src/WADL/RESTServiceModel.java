package WADL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@XmlRootElement
@Entity
@Table(name="RESTService")
public class RESTServiceModel
{
	//properties
	@Transient
	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties

	@Id
	@GeneratedValue
	@Column(name = "RESTServiceId")
	private Integer RESTServiceId;
	
	@Column(name = "wsProvider")
	private String wsProvider;
	
	@Column(name = "baseUri")
	private String baseUri;
	
	@Column(name = "wsName")
	private String wsName;
	
	@Column(name = "wsDescription")
	private String wsDescription;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="RESTServiceWsKeywords", joinColumns=@JoinColumn(name="RESTServiceId"))
	@ForeignKey(name = "fk_restservice_wskeywords")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@Column(name="wsKeywords")
	private Set<String> wsKeywords; 
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="oRESTService")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Set<ResourceModel> setOfResource = new HashSet<ResourceModel>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="accountId")
	@ForeignKey(name = "fk_account_restservice")
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


	public Set<String> getWsKeywords()
	{
		return wsKeywords;
	}
	
	public void setWsKeywords(Set<String> wsKeywords)
	{
		this.wsKeywords = wsKeywords;
	}
	
    public void deleteAllCollections(Session hibernateSession)
    {

        Query query = hibernateSession.createSQLQuery(String.format("DELETE FROM %s where %sId = %d","RESTServicewsKeywords".toLowerCase(),"RESTService",this.getRESTServiceId()));
        query.executeUpdate();
    	
        Iterator<ResourceModel> resourceIterator = setOfResource.iterator();
        while(resourceIterator.hasNext())
        {
        	resourceIterator.next().deleteAllCollections(hibernateSession);
        }
    }
	
	@XmlTransient
    public Set<ResourceModel> getSetOfResource()
    {
        return this.setOfResource;
    }
    
    public void setSetOfResource( Set<ResourceModel> setOfResource)
    {
        this.setOfResource = setOfResource;
    }
    @XmlTransient
	public AccountModel getAccount()
	{
		return oAccount;
	}
	
	public void setAccount( AccountModel oAccount)
	{
		this.oAccount = oAccount;
	}

}

