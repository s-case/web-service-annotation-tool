package WADL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.Session;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@XmlRootElement
@Entity
@Table(name="account")
public class AccountModel
{
	//properties

	@Transient
	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties

	@Id
	@GeneratedValue
	@Column(name = "accountId")
	private int accountId;

	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

	@OneToMany(fetch = FetchType.EAGER, mappedBy="oAccount",orphanRemoval=true)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Set<RESTServiceModel> setOfRESTService= new HashSet<RESTServiceModel>();
	
	//operations
	//place holder for marshalModel operation

    public AccountModel marshalModel(List<Link> linkList)
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
	
	public int getAccountId()
	{
		return accountId;
	}
	
	public void setAccountId(int accountId)
	{
		this.accountId = accountId;
	}

	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
    public void deleteAllCollections(Session hibernateSession)
    {
        Iterator<RESTServiceModel> RESTServiceIterator = setOfRESTService.iterator();
        while(RESTServiceIterator.hasNext())
        {
        	RESTServiceIterator.next().deleteAllCollections(hibernateSession);
        }
    }
	
	@XmlTransient
	public Set<RESTServiceModel> getSetOfRESTService()
	{
		return this.setOfRESTService;
    }
	
	
	public void setSetOfRESTService( Set<RESTServiceModel> setOfRESTService)
	{
        this.setOfRESTService = setOfRESTService;
	}

}

