package WADL;


public class Link
{
	String uri;
	String rel;
	String httpVerb;
	
	Link(String uri, String rel, String httpVerb)
	{
		this.uri = uri;
		this.rel = rel;
		this.httpVerb = httpVerb;
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
}