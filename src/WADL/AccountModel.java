package WADL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.*;
import javax.persistence.*;

@XmlRootElement
@Entity
@Table(name="account")
public class AccountModel
{
	//properties
//	@XmlElement(name="link")
	@Transient
	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties
//	@XmlElement
	@Id
	@GeneratedValue
	@Column(name = "accountId")
	private int accountId;

//	@XmlElement
	@Column(name = "username")
	private String username;
	
//	@XmlElement
	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy="oAccount")
	@XmlTransient
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

	public Set<RESTServiceModel> getSetOfRESTServiceModel()
	{
		return this.setOfRESTService;
    }

	public void setSetOfRESTService( Set<RESTServiceModel> setOfRESTService)
	{
        this.setOfRESTService = setOfRESTService;
	}

}

