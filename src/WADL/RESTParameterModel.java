package WADL;

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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.ForeignKey;


@XmlRootElement
@Entity
@Table(name="RESTParameter")
public class RESTParameterModel
{
	//properties
	@Transient
	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties

	@Id
	@GeneratedValue
	@Column(name = "RESTParameterId")
	private int RESTParameterId;
	
	@Column(name = "parameterName")
	private String parameterName;
	
	@Column(name = "parameterStyle")
	private String parameterStyle;
	
	@Column(name = "parameterDefault")
	private String parameterDefault;
	
	@Column(name = "parameterRequired")
	private String parameterRequired;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "searchOntology")
	private String searchOntology;
	
	@Column(name = "searchConcept")
	private String searchConcept;
	
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="restparameterparametervalueoption", joinColumns=@JoinColumn(name="RESTParameterId"))
    @ForeignKey(name = "fk_restparameter_parameterValueOption")
	@Column(name = "parameterValueOption")
	private Set<String> parameterValueOption = new HashSet<String>();
	
	@Column(name = "parameterType")
	private String parameterType;
	
	@Column(name = "parameterDirection")
	private String parameterDirection;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="resourceId")
	@ForeignKey(name = "fk_resource_restparameter")
	private ResourceModel oResource;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="RESTMethodId")
	@ForeignKey(name = "fk_restmethod_restparameter")
	private RESTMethodModel oRESTMethod;
	
	//operations
	//place holder for marshalModel operation

    public RESTParameterModel marshalModel(List<Link> linkList)
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
	
	public int getRESTParameterId()
	{
		return RESTParameterId;
	}
	
	public void setRESTParameterId(int RESTParameterId)
	{
		this.RESTParameterId = RESTParameterId;
	}
	
	public String getParameterName()
	{
		return parameterName;
	}
	
	public void setParameterName(String parameterName)
	{
		this.parameterName = parameterName;
	}
	
	public String getParameterStyle()
	{
		return parameterStyle;
	}
	
	public void setParameterStyle(String parameterStyle)
	{
		this.parameterStyle = parameterStyle;
	}
	
	public String getParameterDefault()
	{
		return parameterDefault;
	}
	
	public void setParameterDefault(String parameterDefault)
	{
		this.parameterDefault = parameterDefault;
	}
	
	public String getParameterRequired()
	{
		return parameterRequired;
	}
	
	public void setParameterRequired(String parameterRequired)
	{
		this.parameterRequired = parameterRequired;
	}
	
	public Set<String> getParameterValueOption()
	{
		return parameterValueOption;
	}
	
	public void setParameterValueOption(Set<String> parameterValueOption)
	{
		this.parameterValueOption = parameterValueOption;
	}
	
	public String getParameterType()
	{
		return parameterType;
	}
	
	public void setParameterType(String parameterType)
	{
		this.parameterType = parameterType;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getParameterDirection()
	{
		return this.parameterDirection;
	}
	
	public void setParameterDirection(String parameterDirection)
	{
		this.parameterDirection = parameterDirection;
	}
	
	public void setSearchOntology(String searchOntology)
	{
		this.searchOntology = searchOntology;
	}
	
	public String getSearchOntology()
	{
		return searchOntology;
	}
	
	public void setSearchConcept(String searchConcept)
	{
		this.searchConcept = searchConcept;
	}
	
	public String getSearchConcept()
	{
		return searchConcept;
	}
	
    public void deleteAllCollections(Session hibernateSession)
    {
        Query query = hibernateSession.createSQLQuery(String.format("DELETE FROM %s where %sId = %d","RESTParameterparameterValueOption".toLowerCase(),"RESTParameter",this.getRESTParameterId()));
        query.executeUpdate();
    }
	
	@XmlTransient
	public ResourceModel getResource()
	{
		return oResource;
	}
	
	public void setResource( ResourceModel oResource)
	{
		this.oResource = oResource;
	}
	@XmlTransient
	public RESTMethodModel getRESTMethod()
	{
		return oRESTMethod;
	}
	
	public void setRESTMethod( RESTMethodModel oRESTMethod)
	{
		this.oRESTMethod = oRESTMethod;
	}
	
}