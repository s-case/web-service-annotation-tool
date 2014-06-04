package WADL;


public class Link
{
	String uri;
	String rel;
	
	Link(String uri, String rel)
	{
		this.uri = uri;
		this.rel = rel;
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
}