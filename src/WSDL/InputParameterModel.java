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
@Table(name="inputParameter")
public class InputParameterModel
{
	//properties

	@Transient
	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties

	@Id
	@GeneratedValue
	@Column(name = "inputParameterId")
	private int inputParameterId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "ontologyConcept")
	private String ontologyConcept;
	
	@Column(name = "type")
	private String type;
	
	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="inputParameterKeyword", joinColumns=@JoinColumn(name="inputParameterId"))
    @ForeignKey(name = "fk_inputParameter_keyword")
	@Column(name = "keyword")
	private Set<String> keyword;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="oInputParameter",orphanRemoval=true)
    @OnDelete(action=OnDeleteAction.CASCADE)
	private Set<InputParameterModel> setOfInputParameter = new HashSet<InputParameterModel>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="inputMessageId")
    @ForeignKey(name = "fk_inputMessage_inputParameter")
	private InputMessageModel oInputMessage;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="sourceInputParameterId")
    @ForeignKey(name = "fk_inputParameter_inputParameter")
	private InputParameterModel oInputParameter;
	
	//operations
	//place holder for marshalModel operation

    public InputParameterModel marshalModel(List<Link> linkList)
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
	
	public int getInputParameterId()
	{
		return inputParameterId;
	}
	
	public void setInputParameterId(int inputParameterId)
	{
		this.inputParameterId = inputParameterId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getOntologyConcept()
	{
		return ontologyConcept;
	}
	
	public void setOntologyConcept(String ontologyConcept)
	{
		this.ontologyConcept = ontologyConcept;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
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
        Query query = hibernateSession.createSQLQuery(String.format("DELETE FROM %s where %sId = %d","inputParameterKeyword".toLowerCase(),"<resourceName>",this.getInputParameterId()));
        query.executeUpdate();

        Iterator<InputParameterModel> inputParameterIterator = setOfInputParameter.iterator();
        while(inputParameterIterator.hasNext())
        {
            inputParameterIterator.next().deleteAllCollections(hibernateSession);
        }
    }
	
    @XmlTransient
    public Set<InputParameterModel> getSetOfInputParameter()
    {
        return this.setOfInputParameter;
    }

    public void setSetOfInputParameter( Set<InputParameterModel> setOfInputParameter)
    {
        this.setOfInputParameter = setOfInputParameter;
    }

    @XmlTransient
 	public InputMessageModel getInputMessage()
 	{
 		return oInputMessage;
 	}
 	
 	public void setInputMessage( InputMessageModel oInputMessage)
 	{
 		this.oInputMessage = oInputMessage;
 	}
 	
    @XmlTransient
 	public InputParameterModel getInputParameter()
 	{
 		return oInputParameter;
 	}
 	
 	public void setInputParameter( InputParameterModel oInputParameter)
 	{
 		this.oInputParameter = oInputParameter;
 	}
	
}