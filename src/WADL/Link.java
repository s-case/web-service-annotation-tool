package WADL;


public class Link
{
	String uri;
	String rel;
	String httpVerb;
	String type;
	
	public Link(String uri, String rel, String httpVerb, String type)
	{
		this.uri = uri;
		this.rel = rel;
		this.httpVerb = httpVerb;
		this.type = type;
	}
	
	public void setUri( String uri)
	{
		this.uri = uri;
	}
	
	public String getUri()
	{
		return this.uri;
	}
	
	public void setRel( String rel)
	{
		this.rel = rel;
	}
	
	public String getRel()
	{
		return this.rel;
	}
	
	public void setHttpVerb(String httpVerb)
	{
		this.httpVerb = httpVerb;
	}
	
	public String getHttpVerb()
	{
		return this.httpVerb;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
}