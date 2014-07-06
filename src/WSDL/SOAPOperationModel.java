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

import WADL.Link;

@XmlRootElement
@Entity
@Table(name="SOAPOperation")
public class SOAPOperationModel
{
	//properties

	@Transient
	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties

	@Id
	@GeneratedValue
	@Column(name = "SOAPOperationId")
	private int SOAPOperationId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description",columnDefinition="TEXT")
	private String description;
	
	@Column(name = "ontologyConcept")
	private String ontologyConcept;
	
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="SOAPOperationKeyword", joinColumns=@JoinColumn(name="SOAPOperationId"))
    @ForeignKey(name = "fk_SOAPOperation_Keyword")
	@Column(name = "keyword")
	private Set<String> keyword  = new HashSet<String>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="oSOAPOperation",orphanRemoval=true)
    @OnDelete(action=OnDeleteAction.CASCADE)
	private Set<InputMessageModel> setOfInputMessage = new HashSet<InputMessageModel>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="oSOAPOperation",orphanRemoval=true)
    @OnDelete(action=OnDeleteAction.CASCADE)
	private Set<OutputMessageModel> setOfOutputMessage = new HashSet<OutputMessageModel>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SOAPServiceId")
    @ForeignKey(name = "fk_SOAPService_SOAPOperation")
	private SOAPServiceModel oSOAPService;
	
	//operations
	//place holder for marshalModel operation

    public SOAPOperationModel marshalModel(List<Link> linkList)
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
	
	public int getSOAPOperationId()
	{
		return SOAPOperationId;
	}
	
	public void setSOAPOperationId(int SOAPOperationId)
	{
		this.SOAPOperationId = SOAPOperationId;
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
        Query query = hibernateSession.createSQLQuery(String.format("DELETE FROM %s where %sId = %d","SOAPOperationKeyword".toLowerCase(),"SOAPOperation",this.getSOAPOperationId()));
        query.executeUpdate();

        Iterator<InputMessageModel> inputMessageIterator = setOfInputMessage.iterator();
        while(inputMessageIterator.hasNext())
        {
            inputMessageIterator.next().deleteAllCollections(hibernateSession);
        }
        
        Iterator<OutputMessageModel> outputMessageIterator = setOfOutputMessage.iterator();
        while(outputMessageIterator.hasNext())
        {
            outputMessageIterator.next().deleteAllCollections(hibernateSession);
        }
    }
	
    @XmlTransient
    public Set<InputMessageModel> getSetOfInputMessage()
    {
        return this.setOfInputMessage;
    }

    public void setSetOfInputMessage( Set<InputMessageModel> setOfInputMessage)
    {
        this.setOfInputMessage = setOfInputMessage;
    }
    
    @XmlTransient
    public Set<OutputMessageModel> getSetOfOutputMessage()
    {
        return this.setOfOutputMessage;
    }

    public void setSetOfOutputMessage( Set<OutputMessageModel> setOfOutputMessage)
    {
        this.setOfOutputMessage= setOfOutputMessage;
    }

    @XmlTransient
	public SOAPServiceModel getSOAPService()
	{
		return oSOAPService;
	}
	
	public void setSOAPService( SOAPServiceModel oSOAPService)
	{
		this.oSOAPService = oSOAPService;
	}
	
}



