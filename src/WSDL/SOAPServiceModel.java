package WSDL;

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

import WADL.AccountModel;
import WADL.Link;




@XmlRootElement
@Entity
@Table(name="soapservice")
public class SOAPServiceModel
{
	//properties

	@Transient
	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties

	@Id
	@GeneratedValue
	@Column(name = "SOAPServiceId")
	private int SOAPServiceId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "ontologyConcept")
	private String ontologyConcept;
	
    @ElementCollection
    @CollectionTable(name="soapservicekeyword", joinColumns=@JoinColumn(name="SOAPServiceId"))
    @ForeignKey(name = "fk_soapservice_keyword")
	@Column(name = "keyword")
	private Set<String> keyword;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="oSOAPService",orphanRemoval=true)
    @OnDelete(action=OnDeleteAction.CASCADE)
	private Set<SOAPOperationModel> setOfSOAPOperation = new HashSet<SOAPOperationModel>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="accountId")
    @ForeignKey(name = "fk_account_soapservice")
	private AccountModel oAccount;
	
	//operations
	//place holder for marshalModel operation

   public SOAPServiceModel marshalModel(List<Link> linkList)
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
	
	public int getSOAPServiceId()
	{
		return SOAPServiceId;
	}
	
	public void setSOAPServiceId(int SOAPServiceId)
	{
		this.SOAPServiceId = SOAPServiceId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getOntologyConcept()
	{
		return ontologyConcept;
	}
	
	public void setOntologyConcept(String ontologyConcept)
	{
		this.ontologyConcept = ontologyConcept;
	}
	
	public Set<String> getKeyword()
	{
		return keyword;
	}
	
	public void setKeyword(Set<String> keyword)
	{
		this.keyword = keyword;
	}

   public void deleteAllCollections(Session hibernateSession)
   {
       Query query = hibernateSession.createSQLQuery(String.format("DELETE FROM %s where %sId = %d","SOAPServiceKeyword".toLowerCase(),"soapservice",this.getSOAPServiceId()));
       query.executeUpdate();

       Iterator<SOAPOperationModel> SOAPOperationIterator = setOfSOAPOperation.iterator();
       while(SOAPOperationIterator.hasNext())
       {
    	   SOAPOperationIterator.next().deleteAllCollections(hibernateSession);
       }
   }
	
   @XmlTransient
   public Set<SOAPOperationModel> getSetOfSOAPOperation()
   {
       return this.setOfSOAPOperation;
   }

   public void setSetOfSOAPOperation( Set<SOAPOperationModel> setOfSOAPOperation)
   {
       this.setOfSOAPOperation = setOfSOAPOperation;
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

