package WADL;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("/")
public class AccountController
{
	private AccountModel oAccount; //resourceModel object
	private POSTAccountHandler oPOSTAccountHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETAccountHandler oGETAccountHandler;
	private PUTAccountHandler oPUTAccountHandler;
	private DELETEAccountHandler oDELETEAccountHandler;
	
	
	public AccountController() {}
	
	//placeholder to add any HTTPActivity() template operation
	 
	 @Path("/account")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public AccountModel postAccount (AccountModel oAccount )
	 {
		//create a new post<resourceName>Handler
//		oPOSTAccountHandler = new POSTAccountHandler(oAccount);
//		return oPOSTAccountHandler.postAccount();
		 
		 //SCAFFOLDING 
		 return oAccount;
		 //END OF SCAFFOLDING
	 }
	 
	//GET for individual resource

	@Path("/account/{accountId}")
	@GET
	@Produces("application/json")
	//TODO CHANGE AccountModel to AccountRepresentation
	public AccountModel getAccount(@PathParam("accountId") int accountId)
	{
		//create a new get<resourceName>Handler
//		oGETAccountHandler = new GETAccountHandler(accountId);
//		return oGETAccountHandler.getAccount();
		
		//SCAFFOLDING
		AccountModel oAccount = new AccountModel();
		oAccount.setUsername("cz");
		oAccount.setAccountId(accountId);
		return oAccount;
		//END OF SCAFFOLDING
	}
	

	//PUT

	 @Path("/account/{accountId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public AccountModel putAccount (@PathParam("accountId") int accountId, AccountModel oAccount)
	 {
		//create a new put<resourceName>Handler
//		oPUTAccountHandler = new PUTAccountHandler(accountId, oAccount);
//		return oPUTAccountHandler.putAccount();
		 
		 //SCAFFOLDING
		 return oAccount;
		 //END OF SCAFFOLDING
	 }
	 
	 //DELETE

	@Path("/account/{accountId}")
	@DELETE

	public void deleteAccount (@PathParam("accountId") int accountId)
	{
		//create a new delete<resourceName>Handler
//		oDELETEAccountHandler = new DELETEAccountHandler(accountId);
//		oDELETEAccountHandler.deleteAccount();
		
		//SCAFFOLDING
		System.out.println("Successfully deleted account " + accountId);
		//END OF SCAFFOLDING
	}
}