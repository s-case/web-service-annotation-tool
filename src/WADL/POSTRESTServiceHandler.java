package WADL;


public class POSTRESTServiceHandler
{
    private AccountModel oAccount;
    private RESTServiceModel oRESTService;
    private SQLITEController oSQLITEController;

    POSTRESTServiceHandler(Integer accountId, RESTServiceModel oRESTService)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        this.oRESTService = oRESTService;
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

    public RESTServiceModel postRESTService()
    {
        //TODO add authentication if needed

        return oSQLITEController.postRESTService(oRESTService);
    }
}