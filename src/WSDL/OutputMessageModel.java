package WSDL;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.Session;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import WADL.Link;

@XmlRootElement
@Entity
@Table(name="OutputMessage")
@Indexed
public class OutputMessageModel
{
	//properties

	@Transient
	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties

	@Id
	@GeneratedValue
	@Column(name = "outputMessageId")
	private int outputMessageId;
	
	@Column(name = "name")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="oOutputMessage",orphanRemoval=true)
    @OnDelete(action=OnDeleteAction.CASCADE)
	@IndexedEmbedded
	private Set<OutputParameterModel> setOfOutputParameter = new HashSet<OutputParameterModel>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SOAPOperationId")
    @ForeignKey(name = "fk_SOAPOperation_OutputMessage")
	private SOAPOperationModel oSOAPOperation;
	
	//operations
	//place holder for marshalModel operation

    public OutputMessageModel marshalModel(List<Link> linkList)
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
	
	public int getOutputMessageId()
	{
		return outputMessageId;
	}
	
	public void setOutputMessageId(int outputMessageId)
	{
		this.outputMessageId = outputMessageId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

    public void deleteAllCollections(Session hibernateSession)
    {
        Iterator<OutputParameterModel> outputParameterIterator = setOfOutputParameter.iterator();
        while(outputParameterIterator.hasNext())
        {
            outputParameterIterator.next().deleteAllCollections(hibernateSession);
        }
    }
	

    @XmlTransient
    public Set<OutputParameterModel> getSetOfOutputParameter()
    {
        return this.setOfOutputParameter;
    }

    public void setSetOfOutputParameter( Set<OutputParameterModel> setOfOutputParameter)
    {
        this.setOfOutputParameter = setOfOutputParameter;
    }

    @XmlTransient
	public SOAPOperationModel getSOAPOperation()
	{
		return oSOAPOperation;
	}
	
	public void setSOAPOperation( SOAPOperationModel oSOAPOperation)
	{
		this.oSOAPOperation = oSOAPOperation;
	}
	
}