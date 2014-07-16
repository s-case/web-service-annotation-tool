package WADL;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchModel
{
	//properties

	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties

	//operations
	//place holder for marshalModel operation

    public SearchModel marshalModel(List<Link> linkList)
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
}