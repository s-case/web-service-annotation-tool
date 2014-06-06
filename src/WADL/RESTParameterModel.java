package WADL;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement
@Entity
@Table(name="RESTParameter")
public class RESTParameterModel
{
	//properties
//	@XmlElement(name="link")
	@Transient
	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties
//	@XmlElement
	@Id
	@GeneratedValue
	@Column(name = "RESTParameterId")
	private int RESTParameterId;
	
//	@XmlElement
	@Column(name = "parameterName")
	private String parameterName;
	
//	@XmlElement
	@Column(name = "parameterStyle")
	private String parameterStyle;
	
//	@XmlElement
	@Column(name = "parameterDefault")
	private String parameterDefault;
	
//	@XmlElement
	@Column(name = "parameterRequired")
	private Boolean parameterRequired;
	
//	@XmlElement
    @ElementCollection
    @CollectionTable(name="RESTParameterParameterValueOption", joinColumns=@JoinColumn(name="RESTParameterId"))
	@Column(name = "parameterValueOption")
	private List<String> parameterValueOption;
	
//	@XmlElement
	@Column(name = "parameterMediaType")
	private String parameterMediaType;

	@ManyToOne
	@JoinColumn(name="resourceId")
	@XmlTransient
	private ResourceModel oResource;
	
	@ManyToOne
	@JoinColumn(name="RESTMethodId")
	@XmlTransient
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
	
	public Boolean getParameterRequired()
	{
		return parameterRequired;
	}
	
	public void setParameterRequired(Boolean parameterRequired)
	{
		this.parameterRequired = parameterRequired;
	}
	
	public List<String> getParameterValueOption()
	{
		return parameterValueOption;
	}
	
	public void setParameterValueOption(List<String> parameterValueOption)
	{
		this.parameterValueOption = parameterValueOption;
	}
	
	public String getParameterMediaType()
	{
		return parameterMediaType;
	}
	
	public void setParameterMediaType(String parameterMediaType)
	{
		this.parameterMediaType = parameterMediaType;
	}

	public ResourceModel getResource()
	{
		return oResource;
	}
	
	public void setResource( ResourceModel oResource)
	{
		this.oResource = oResource;
	}
	
	public RESTMethodModel getRESTMethod()
	{
		return oRESTMethod;
	}
	
	public void setRESTMethod( RESTMethodModel oRESTMethod)
	{
		this.oRESTMethod = oRESTMethod;
	}
	
}