package WADL;


public class PUTRESTServiceHandler
{

    private AccountModel oAccount;
    private RESTServiceModel oRESTService;
    private SQLITEController oSQLITEController;

    PUTRESTServiceHandler(Integer accountId, Integer RESTServiceId, RESTServiceModel oRESTService)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        this.oRESTService = oRESTService;
        this.oRESTService.setRESTServiceId(RESTServiceId);
        oRESTService.setAccount( this.oAccount);
        oSQLITEController = new SQLITEController();
    }


    public void setAccount(AccountModel oAccount)
    {
        this.oAccount = oAccount;
    }

    public AccountModel getAccount()
    {
        return this.oAccount;
    }

    public RESTServiceModel putRESTService()
    {
        //TODO add authentication if needed

        return oSQLITEController.putRESTService(oRESTService);
    }

}
